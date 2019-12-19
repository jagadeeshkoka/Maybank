package com.maybank.service;

import java.util.List;
import java.util.Map;

import com.maybank.vo.CategoryVO;
import com.maybank.vo.MerchantVO;
import com.maybank.vo.ProductVO;

public interface ProductService {

	
	public boolean createNewProduct(ProductVO vo);
	public ProductVO searchProductByName(String productName);
	public boolean updateProduct(ProductVO vo);
	public boolean deleteProduct(int productId);
	public List<ProductVO> getAllProducts();
	public Map<Integer, String> getAllMerchants();
	public Map<Integer, String> getAllCategory();
	
	
	
}
