package com.clayfin.serviceImpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.common.util.ProductQuantityDto;
import com.clayfin.common.util.RepoHelper;
import com.clayfin.enums.OrderType;
import com.clayfin.enums.Status;
import com.clayfin.exception.AddressException;
import com.clayfin.exception.OrderException;
import com.clayfin.exception.ProductException;
import com.clayfin.exception.TransactionException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Orders;
import com.clayfin.model.Products;
import com.clayfin.model.Transaction;
import com.clayfin.model.UserProfile;
import com.clayfin.repository.OrdersRepository;
import com.clayfin.request.dto.OrderRequestDto;
import com.clayfin.service.AddressService;
import com.clayfin.service.OrdersService;
import com.clayfin.service.ProductService;
import com.clayfin.service.TransactionService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class OrderServiceImpl implements OrdersService{
	
	@Autowired
	RepoHelper helper;
	
	@Autowired
	OrdersRepository repository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	AddressService addressService;

	@Autowired
	TransactionService transactionService;

	@Override
	public Orders getOrder(Long id, HttpServletRequest request) throws UserException, OrderException {
		UserProfile profile = helper.getUserProfile(request);
		Orders order = repository.findById(id).get();
		if(!order.getUserProfile().getId().equals(profile.getId())) {
			throw new OrderException("Entered Order id is incorrect please verify again");
		}
		if(order.equals(null)) {
			throw new OrderException("No Order found by Id : "+id);
		}
		return order;
	}

	@Override
	public Status updateOrder(Long id, Orders order, HttpServletRequest request) throws UserException, OrderException {
		UserProfile profile = helper.getUserProfile(request);
		Orders oldOrder = repository.findById(id).get();
		if(!oldOrder.getUserProfile().getId().equals(profile.getId())) {
			throw new OrderException("Entered Order id is incorrect please verify again");
		}
		BeanUtils.copyProperties(order, oldOrder, helper.getNullPropertyNames(order));
		repository.save(oldOrder);
		return Status.UPDATED;
	}

	@Override
	public List<Orders> getAllOrders(HttpServletRequest request) throws UserException, OrderException {
		UserProfile profile = helper.getUserProfile(request);
		return repository.findAllByUserProfileId(profile.getId());
	}

	@Override
	public Status deleteOrder(Long id, HttpServletRequest request) throws UserException, OrderException {
		UserProfile profile = helper.getUserProfile(request);
		Orders oldOrder = repository.findById(id).get();
		if(!oldOrder.getUserProfile().getId().equals(profile.getId())) {
			throw new OrderException("Entered Order id is incorrect please verify again");
		}
		repository.deleteById(id);
		return Status.DELETED;
	}

	@Override
	public Status addOrder(OrderRequestDto dto, HttpServletRequest request)
			throws UserException, OrderException, ProductException, AddressException, TransactionException {
		UserProfile profile = helper.getUserProfile(request);
		Orders order = new Orders();  
		order.setAddress(addressService.getAddress(dto.getAddressId(),request));
		order.setOrderStatus("PLACED");
		Integer count = 0;
		Double amount = 0.0;
		for(ProductQuantityDto i : dto.getProductQuantityDtos()) {
			Products product = productService.getProducts(i.getProductId(), request);
			amount+=i.getQuantity()*product.getPrice();
			count+=i.getQuantity();
		}
		order.setTotalPrice(amount);
		order.setTotalItems(count);
		order.setProducts(dto.getProductQuantityDtos());
		Transaction transaction = new Transaction();
		if(!dto.getOrderType().equals(OrderType.COD)) {
			transaction.setAmount(amount);
			transaction.setLocation(null);
			transaction.setOrder(order);
			transaction.setStatus(Status.SUCCESS);
			transaction.setUserProfile(profile);
			transactionService.addTransaction(transaction, request);
		}
		
		order.setTransaction(transaction);
		order.setUserProfile(profile);
		repository.save(order);
		return Status.CREATED;
	}

}
