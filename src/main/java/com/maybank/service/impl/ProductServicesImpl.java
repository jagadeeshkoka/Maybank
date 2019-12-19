package com.maybank.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.maybank.dto.ProductDTO;
import com.maybank.entities.Category;
import com.maybank.entities.Merchant;
import com.maybank.entities.Product;
import com.maybank.repo.CategoryRepo;
import com.maybank.repo.MerchantRepo;
import com.maybank.repo.ProductRepo;
import com.maybank.service.ProductService;
import com.maybank.vo.CategoryVO;
import com.maybank.vo.MerchantVO;
import com.maybank.vo.ProductVO;

@Service
@Qualifier("productServices")
public class ProductServicesImpl implements ProductService {
	
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private MerchantRepo  merchantRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	
	@Override
	public Map<Integer, String> getAllCategory()
	{
		
		Map<Integer, String> list = new HashMap<>();
		
		try {
		
			List<Category> temp = categoryRepo.findAll();
			for(Category cat : temp)
			{
				
				list.put(cat.getCategorId(), cat.getCategoryTitle());
						
				
			}
			
			System.out.println("CategorySize: "+list.size());
			return list;
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		return null;
	}
	
	@Override
	public Map<Integer, String> getAllMerchants()
	{
		Map<Integer, String> result = new HashMap<>();
		try {
			
			List<Merchant> list = merchantRepo.findAll();
			for(Merchant merch : list)
			{
				
				result.put(merch.getMerchantId(), merch.getMerchantName());
				
				
			}
			
			System.out.println("Merchants List: "+result.size());
			return result;
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	
	

	@Override
	public boolean createNewProduct(ProductVO vo) {
		
		
		try {
			
			Product entity = new Product();
			entity.setCatregorId(vo.getCategory());
//			entity.setImageUrl(vo.getProductImageUrl());
			entity.setUrl(vo.getUrl());
			entity.setProductTitle(vo.getProductName());
			entity.setMerchantId(vo.getMerchant());
			entity.setProductAvailability(vo.isAvailability());
			entity.setProductMsrp(vo.getMsrp());
			entity.setProductDesc(vo.getDesc());
			entity = productRepo.saveAndFlush(entity);
			return vo.getProductId() == entity.getProductId();
		}catch (Exception e) {

			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public ProductVO searchProductByName(String productName) {
		
		try {
			
		Product product =	productRepo.getByProductTitle(productName);
		
		return ProductDTO.transferProduct(product);
			
		}catch (Exception e) {
			
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean updateProduct(ProductVO vo) {
	
		try {
			
			Optional<Product> prod  = productRepo.findById(vo.getProductId());
			if(prod.isPresent())
			{
				
			Product product = 	prod.get();
			product.setCatregorId(vo.getCategory());
			product.setMerchantId(vo.getMerchant());
//			product.setImageUrl(vo.getProductImageUrl());
			product.setUrl(vo.getUrl());
			product.setProductAvailability(vo.isAvailability());
			product.setProductMsrp(vo.getMsrp());
			product.setProductPrice(vo.getPrice());
			product.setProductTitle(vo.getProductName());
			
			productRepo.saveAndFlush(product);
				
				
				
			return true;	
			}
			
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	@Override
	public boolean deleteProduct(int productId) {

		
		try {
			
			productRepo.deleteById(productId);
			return true;
		}catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		
		

	}

	@Override
	public List<ProductVO> getAllProducts() {
		
		
		try {
			
			List<Product> list =productRepo.findAll();
			List<ProductVO> result = new ArrayList<ProductVO>();
			for(Product prod : list)
			{
				result.add(ProductDTO.transferProduct(prod));
			}
			
			return result;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

}
