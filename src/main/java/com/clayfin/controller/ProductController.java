package com.clayfin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clayfin.exception.ProductException;
import com.clayfin.exception.UserException;
import com.clayfin.model.Products;
import com.clayfin.request.dto.Base64Dto;
import com.clayfin.request.dto.LoginRequest;
import com.clayfin.response.dto.GeneralResponse;
import com.clayfin.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pet")
public class ProductController {

	@Autowired
	ProductService service;

	@PostMapping("/addProduct")
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	public ResponseEntity<GeneralResponse> addProduct(@RequestBody Products products, HttpServletRequest request)
			throws UserException, ProductException {
		var response = new GeneralResponse();
		response.setMessage("Product has been added");
		response.setData(service.addProduct(products, request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	@PostMapping("/addAllProducts")
	public ResponseEntity<GeneralResponse> addAllProducts(@RequestBody List<Products> products,
			HttpServletRequest requets) throws UserException, ProductException {
		var response = new GeneralResponse();
		response.setMessage("New Products has been added");
		response.setData(service.addAllProducts(products, requets));
		return ResponseEntity.ok(response);
	}
	
	
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	@PostMapping("/addAllProductsByString")
	public ResponseEntity<GeneralResponse> addAllProducts(@Valid @RequestBody Base64Dto dto,
			HttpServletRequest requets) throws UserException, ProductException {
		System.out.println(dto.getBase64EncodedString() +" and the size is "+dto.getNumOfItems());
		var response = new GeneralResponse();
		response.setMessage("New Products has been added");
		response.setData(service.addAllProducts(dto.getBase64EncodedString(), requets));
		return ResponseEntity.ok(response);
	}
	
	
	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<GeneralResponse> updateProduct(@PathVariable Long id, @RequestBody Products products,
			HttpServletRequest request) throws UserException, ProductException {
		var response = new GeneralResponse();
		response.setMessage("Product has been updated");
		response.setData(service.updateProduct(id, products, request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
	@GetMapping("/getProducts/{id}")
	public ResponseEntity<GeneralResponse> getProducts(@PathVariable Long id, HttpServletRequest request)
			throws UserException, ProductException {
		var response = new GeneralResponse();
		response.setMessage("Product has been found with id : "+id);
		response.setData(service.getProducts(id, request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('USER') or hasRole('MANAGER') or hasRole('ADMIN')")
	@GetMapping("/getAllProducts")
	public ResponseEntity<GeneralResponse> getAllProducts(HttpServletRequest request)
			throws UserException, ProductException {
		var response = new GeneralResponse();
		response.setMessage("All Products found ");
		response.setData(service.getAllProducts(request));
		return ResponseEntity.ok(response);
	}

	@PreAuthorize("hasRole('MANAGER') or hasRole('ADMIN')")
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<GeneralResponse> deleteProduct(@PathVariable Long id, HttpServletRequest request)
			throws UserException, ProductException {
		var response = new GeneralResponse();
		response.setMessage("Product has been Deleted by Id : "+id);
		response.setData(service.deleteProduct(id, request));
		return ResponseEntity.ok(response);
	}

}
