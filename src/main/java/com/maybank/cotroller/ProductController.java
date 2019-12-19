package com.maybank.cotroller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.maybank.service.ProductService;
import com.maybank.vo.CategoryVO;
import com.maybank.vo.MerchantVO;
import com.maybank.vo.ProductVO;

@Controller
public class ProductController {

	
	
	@Autowired
	@Qualifier("productServices")
	private ProductService services;
	
	
	@GetMapping(path = {"/products/product-services"})
	public String getProductServices()
	{
		return "welcome";
	}
	
	
	@GetMapping(path = {"/products/getAllProducts"})
	public @ResponseBody List<ProductVO> getAllProducts()
	{
		
		return services.getAllProducts();
		
	}
	
	
	@GetMapping(path = {"/products/search-product" })
	public @ResponseBody ProductVO searchProduct(@RequestParam String productName)
	{
		try {
			
			return services.searchProductByName(productName);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@PostMapping(path = {"/products/editProduct"}, consumes = { MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody ProductVO editProduct(@RequestBody ProductVO vo)
	{
		
		try {
			
			services.updateProduct(vo);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	
	
	@PostMapping(path = {"/products/addProduct"}, consumes = { MediaType.APPLICATION_JSON_VALUE} )
	public @ResponseBody String addProduct(@RequestBody ProductVO vo)
	{
		
		
		try {
			
			if(services.createNewProduct(vo))
				{
					return "Success";
				}else {
					return "fail";
				}
			
			
		}catch (Exception e) {

			e.printStackTrace();
			return e.getMessage();
		}
	
	}
	
	
	@DeleteMapping( path = {"/products/deleteProduct"})
	public @ResponseBody String deleteProduct(@RequestParam int productId)
	{
		
		try {
			
					
		return	services.deleteProduct(productId)+"";
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	return null;	
	}
	
	
	@GetMapping(path  = { "/products/getMerchants"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Map<Integer, String> getMerchants()
	{
		
		try {
	
				return services.getAllMerchants();
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@GetMapping (path  = {"/products/getCategory"}, consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Map<Integer, String> getCategories()
	{
		
		try {
			
			return	services.getAllCategory();
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}
	
	
}
