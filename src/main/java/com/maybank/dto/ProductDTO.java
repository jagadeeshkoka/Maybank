package com.maybank.dto;

import com.maybank.entities.Product;
import com.maybank.vo.ProductVO;

public class ProductDTO {

	
	public static ProductVO transferProduct(Product product)
	{
		ProductVO vo = new ProductVO();
		if(product == null ) return vo;
		vo.setProductId(product.getProductId());
		vo.setProductName(product.getProductTitle());
//		vo.setProductImageUrl(product.getImageUrl());
		vo.setUrl(product.getUrl());
//		vo.setMerchant(product.getMerchant().getMerchantName());
//		vo.setCategory(product.getCategory().getCategoryTitle());
		vo.setDesc(product.getProductDesc());
		vo.setAvailability(product.isProductAvailability());
		vo.setPrice(product.getProductPrice());
		vo.setMsrp(product.getProductMsrp());
		
		return vo;
		
	}
	
	
}
