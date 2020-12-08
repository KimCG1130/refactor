package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;


public class ProductServiceImpl12 implements ProductService {
	
	///Field

	private ProductDao productDao;
	
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	///Constructor
	public ProductServiceImpl12() { 
		System.out.println(this.getClass());
	}
	
	public void addProduct(Product product) throws Exception {
		productDao.addProduct(product);
	}

	public Product getProduct(int prodNo) throws Exception {
		return productDao.getProduct(prodNo);
	}

	public Map <String, Object> getProductList(Search search) throws Exception {
		List <Product> list = productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map <String, Object> map = new HashMap <String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount)); // wrapper class ��� ����:�⺻�ڷ�����
		//Ŭ����ȭ �Ͽ� Ŭ������ ������ ���� �ϱ� ����
		
		return map;
	}


	// ��ǰ ���� ������ ���� ����Ͻ� ���� ����
	public void updateProduct(Product product) throws Exception {
		productDao.updateProduct(product);
	}
	

	// ��ǰ ������ ���� ����Ͻ� ���� ���� 
	public void deleteProduct(int prodNo) throws Exception {
		productDao.deleteProduct(prodNo);
	}

	@Override
	public int getTotalCount(Search search) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
