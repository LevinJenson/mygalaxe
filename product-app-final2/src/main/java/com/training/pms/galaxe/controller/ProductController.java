package com.training.pms.galaxe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;
import com.training.pms.galaxe.services.ProductService;

@RestController
@RequestMapping("product")

public class ProductController {

	@Autowired
	ProductService productService;
	
	@Autowired
	Product product;

	public ProductController() {
		// TODO Auto-generated constructor stub
	}

	@Autowired
	ProductDAO productDAO;

	@GetMapping // http://localhost:9090/product
	public List<Product> getProducts() {

		return (List<Product>) productDAO.findAll();
	}

	@PostMapping // http://localhost:9090/product/ - POST - BODY
	public ResponseEntity<String> saveProduct(@RequestBody Product product) {
		// first edit
		ResponseEntity<String> responseEntity;

		if (productService.isProductExists(product.getProductId()))
		{
           responseEntity = new ResponseEntity<String>("product already exists",HttpStatus.CONFLICT);
		}
		else
		{
			String message = productService.saveProduct(product);
			if (message.equals("product saved succesfully..you are geneious")) {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.CREATED);
			} else {
				responseEntity = new ResponseEntity<String>(message, HttpStatus.NOT_ACCEPTABLE);
			}
			
		}
		return responseEntity;
	}

	@GetMapping("{productId}") // http://localhost:9090/product/198
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId) {

		ResponseEntity<Product> responseEntity;
		if (productService.isProductExists(productId)) {
			 product = productService.getProduct(productId);

			responseEntity = new ResponseEntity<Product>(product, HttpStatus.OK); // 201
		} else {
			responseEntity = new ResponseEntity<Product>(product, HttpStatus.NO_CONTENT); // 201
		}
		return responseEntity;
	}


	@DeleteMapping("{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable("productId")Integer pId) {
		ResponseEntity<String> responseEntity;
		if(productService.isProductExists(pId)){
			productService.deleteProduct(pId);
			responseEntity=new ResponseEntity<String>("Product deletion success",HttpStatus.OK);
		}
		else {
			responseEntity=new ResponseEntity<String>("Product doesn't exist.So we can't delete",HttpStatus.CONFLICT);
		}
		return responseEntity;
		
	}

	@PutMapping // http://localhost:9090/product/ - PUT - BODY
	public String updateProduct(@RequestBody Product product) {
		return "updating a single products  ::  " + product;
	}
	
	@GetMapping("searchByProductName/{productName}") // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProductsByName(@PathVariable("productName") String productName) {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProduct(productName);

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}
	
	
	@GetMapping("searchByProductPrice/{price}") // http://localhost:9090/product
	public ResponseEntity<List<Product>> getProductsByPrice(@PathVariable("price") Integer price) {
		ResponseEntity<List<Product>> responseEntity;

		List<Product> products = productService.getProductByPrice(price);

		if (products.size() == 0) {
			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.NO_CONTENT); // 201
		} else {

			responseEntity = new ResponseEntity<List<Product>>(products, HttpStatus.OK); // 200

		}
		return responseEntity;
	}
	
}
