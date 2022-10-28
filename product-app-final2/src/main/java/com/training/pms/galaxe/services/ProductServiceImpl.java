package com.training.pms.galaxe.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.pms.galaxe.aop.LoggingAspect;
import com.training.pms.galaxe.aop.SecurityAspect;
import com.training.pms.galaxe.dao.ProductDAO;
import com.training.pms.galaxe.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	LoggingAspect logging;
	
	
	@Autowired
	SecurityAspect security;
	
	public ProductServiceImpl() {
		
	}

	@Override
	public String saveProduct(Product product) {
		
		String message = null;
		if(product.getPrice()<0 | product.getQuantityInHand()<0) {
			message = "product price or QOH cannot be negative";
		}
		else
		{
			message = "product saved succesfully..you are geneious";
			productDAO.save(product);
		}
		
		return message;
		
	}

	@Override
	public String updateProduct(Product product) {
		String message = null;
		if(product.getPrice()<0 | product.getQuantityInHand()<0) {
			message = "product price or QOH cannot be negative";
		}
		else
		{
			message = "product saved succesfully..you are geneious";
			productDAO.save(product);
		}
		
		return message;
	}

	@Override
	public String deleteProduct(int productId) {
		productDAO.deleteById(productId);
		return "product deleted successfully";
	}

	@Override
	public Product getProduct(int productId) {
		Optional<Product> pr = productDAO.findById(productId);
		return pr.get();
	}

	@Override
	public List<Product> getProduct() {
		System.out.println("GET All Products Called ");
		return (List<Product>) productDAO.findAll();
	}

	@Override
	public boolean isProductExists(int productId) {
		Optional<Product> pr = productDAO.findById(productId);
		return pr.isPresent();
	}

	@Override
	public List<Product> searchProductByName(String productName) {
		// TODO Auto-generated method stub
		return null;
	}
	

	 @Override
		public List<Product> getProduct(String productName) {
			return productDAO.findByProductName(productName);
		}



		@Override
		public List<Product> getProduct(int min, int max) {
			// TODO Auto-generated method stub
			return productDAO.findByPriceBetween(min, max);
		}

		@Override
		public String checkProductInventory(int productId, int quantityRequired) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Product> getProductByPrice(int price) {
			// TODO Auto-generated method stub
			return productDAO.findByPrice(price);
		}

		
}
