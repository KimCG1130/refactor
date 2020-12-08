package com.model2.mvc.service.product;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


public interface ProductService {
	
	public void addProduct(Product product) throws Exception;//추가

	public Product getProduct(int prodNo) throws Exception;//조회

	public Map<String,Object> getProductList(Search search) throws Exception;//목록

	public void updateProduct(Product product) throws Exception;//수정
	
	public void deleteProduct(int prodNo) throws Exception;//제거

	public int getTotalCount(Search search) throws Exception ;
	
}