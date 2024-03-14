package com.clayfin.serviceImpl;

import java.util.List;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.dataformat.bindy.csv.BindyCsvDataFormat;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clayfin.common.util.RepoHelper;
import com.clayfin.enums.ERole;
import com.clayfin.enums.Status;
import com.clayfin.exception.ProductException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Products;
import com.clayfin.model.UserProfile;
import com.clayfin.repository.ProductsRepository;
import com.clayfin.request.dto.ProductBase64DTO;
import com.clayfin.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	RepoHelper  helper;
	
	@Autowired
	ProductsRepository repository;
	
	

	@Override
	public Status addProduct(Products products, HttpServletRequest request) throws UserException, ProductException {
		UserProfile profile = helper.getUserProfile(request);
		if(!profile.getRoles().contains(ERole.ROLE_ADMIN)) {
			throw new UserException("Only admin can able to add the products");
		}
		repository.save(products);
		return Status.CREATED;
	}

	@Override
	public Status addAllProducts(List<Products> products, HttpServletRequest requets)
			throws UserException, ProductException {
		UserProfile profile = helper.getUserProfile(requets);
		if(!profile.getRoles().contains(ERole.ROLE_ADMIN)) {
			throw new UserException("Only admin can able to add the products");
		}
		repository.saveAll(products);
		return Status.CREATED;
	}

	@Override
	public Status updateProduct(Long id, Products products, HttpServletRequest request)
			throws UserException, ProductException {
		UserProfile profile = helper.getUserProfile(request);
		if(!profile.getRoles().contains(ERole.ROLE_ADMIN)) {
			throw new UserException("Only admin can able to add the products");
		}
		Products oldProduct = repository.findById(id).get();
		if(oldProduct.equals(null)) {
			throw new ProductException("No Product is Found with id : "+id);
		}
		BeanUtils.copyProperties(products, oldProduct, helper.getNullPropertyNames(products));
		repository.save(oldProduct);
		return Status.UPDATED ;
	}

	@Override
	public Products getProducts(Long id, HttpServletRequest request) throws UserException, ProductException {
		Products product = repository.findById(id).get();
		if(product.equals(null)) {
			throw new ProductException("No Product is FOund with Id : "+id);
		}
		return product;
	}

	@Override
	public List<Products> getAllProducts(HttpServletRequest request) throws UserException, ProductException {
		List<Products> product = repository.findAll();
		if(product.equals(null)) {
			throw new ProductException("No Products Found");
		}
		return product;
	}

	@Override
	public Status deleteProduct(Long id, HttpServletRequest request) throws UserException, ProductException {
		UserProfile profile = helper.getUserProfile(request);
		if(!profile.getRoles().contains(ERole.ROLE_ADMIN)) {
			throw new UserException("Only admin can able to add the products");
		}
		repository.deleteById(id);
		return Status.DELETED;
	}

	@Override
	public Status addAllProducts(String productsBase64String, HttpServletRequest requets) throws UserException, ProductException {
		int count = 0;
		List<ProductBase64DTO> products = readCsv(productsBase64String);
		for(ProductBase64DTO i : products) {
			Products newProduct = new Products();
			newProduct.setDiscription(i.getDiscription());
			newProduct.setName(i.getName());
			newProduct.setPrice(i.getPrice());
			newProduct.setProductAvailability(i.getProductAvailability());
		
			repository.save(newProduct);
			count++;
		}
		System.out.println(count);
		return Status.CREATED;
	}
	
	public List<ProductBase64DTO> readCsv(String base64EncodedCsv) {
    	CamelContext context = new DefaultCamelContext();
        BindyCsvDataFormat df = new BindyCsvDataFormat(ProductBase64DTO.class);
        try {
			context.addRoutes(new RouteBuilder() {
			    @Override
			    public void configure() {
			        from("direct:csvToObject")
			                .unmarshal(df)
			                .to("mock:result");
			    }
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

        context.start();
        byte[] decodedBytes = java.util.Base64.getDecoder().decode(base64EncodedCsv);
        String decodedCsv = new String(decodedBytes);
        List<ProductBase64DTO> csvObjects = context.createProducerTemplate().requestBody("direct:csvToObject", decodedCsv, List.class);
        context.stop();
		return csvObjects;
	}

}
