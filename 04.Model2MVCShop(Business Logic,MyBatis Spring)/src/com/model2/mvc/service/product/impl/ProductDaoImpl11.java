//mybatis+DAO형식으로 수정해야함
package com.model2.mvc.service.product.impl;

import java.util.List;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

import org.apache.ibatis.session.SqlSession;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;

//==> 상품관리 DAO CRUD 구현

public class ProductDaoImpl11 implements ProductDao{
	
	private SqlSession sqlSession;
	
	  public void setSqlSession(SqlSession sqlSession) {
		  System.out.println("::"+getClass()+".setSqlSession() call"); 
		  this.sqlSession = sqlSession; 
		  }
		  
		  public ProductDaoImpl11() {
		  System.out.println("::"+getClass()+"default Constructor call"); 
		  }
		  
		   public void addProduct(Product product) throws Exception {
			   sqlSession.insert("ProductMapper.addProduct", product);
			  }
		  
		  public Product getProduct(int prodNo) throws Exception {  
			  return (Product)sqlSession.selectOne("ProductMapper.getProduct",prodNo); 
		  }
		  
		  public void updateProduct(Product product) throws Exception {  
			  sqlSession.update("ProductMapper.updateProduct", product);
			  }
		  
		  public void deleteProduct(int prodNo) throws Exception { 
		  sqlSession.delete("ProductMapper.deleteProduct",prodNo); }
		  
		   public List<Product> getProductList(Search search) throws Exception { 
			  return sqlSession.selectList("ProductMapper.getProductList",search);
		  }

		
		public int getTotalCount(Search search) throws Exception {
			return sqlSession.selectOne("ProductMapper.getTotalCount", search);
		}
}
	

			/*
			 * public void setSqlSession(SqlSession sqlSession) { this.sqlSession =
			 * sqlSession; }
			 * 
			 * ///Constructor public ProductDaoImpl11() {
			 * System.out.println(this.getClass()); }
			 * 
			 * ///Method public void addProduct(Product product) throws Exception {
			 * sqlSession.insert("ProductMapper.addProduct", product); }
			 * 
			 * public Product getProduct(int prodNo) throws Exception { return
			 * sqlSession.selectOne("ProductMapper.getProduct", prodNo); }
			 * 
			 * public void updateProduct(Product product) throws Exception {
			 * sqlSession.update("ProductMapper.updateProduct", product); }
			 * 
			 * public List<Product> getProductList(Search search) throws Exception { return
			 * sqlSession.selectList("ProductMapper.getProductList", search); }
			 * 
			 * // 게시판 Page 처리를 위한 전체 Row(totalCount) return public int getTotalCount(Search
			 * search) throws Exception { return
			 * sqlSession.selectOne("ProductMapper.getTotalCount", search); } }
			 */