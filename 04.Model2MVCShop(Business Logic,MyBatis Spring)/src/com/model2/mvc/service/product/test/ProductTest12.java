package com.model2.mvc.service.product.test;

import java.util.ArrayList;

//import mybatis.service.domain.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.impl.ProductDaoImpl11;

import org.apache.ibatis.session.SqlSession;

/*
 * FileName : MyBatisTestApp11.java 
 *  :: Persistence Layer unit Test :service+persistence(11)
  */
public class ProductTest12 {
	
	///main method
	public static void main(String[] args) throws Exception{

		//==> SqlSessionFactoryBean.getSqlSession()을 이용 SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		//==> ProductDaoImpl11 생성 및 sqlSession instance setter injection
		ProductDaoImpl11 productDao = new ProductDaoImpl11();
		productDao.setSqlSession(sqlSession);
		System.out.println("\n");
		
		//==> Test 용 Product instance 생성  
		Product product = new Product("product04","주몽","product04",null,0);
		
		//1. addProduct Test  
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		System.out.println(":: "+ productDao.addProduct(product) );
		System.out.println("\n");
		
		//2. getProduct Test :: 주몽 inert 확인 
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		System.out.println(":: "+ productDao.getProduct(product.getProductId()) );
		System.out.println("\n");

		//3. uadateProduct Test  :: 주몽 ==> 온달 수정
		product.setProductName("온달");
		System.out.println(":: 3. update(UPDATE)  ? ");
		System.out.println(":: "+productDao.updateProduct(product) );
		System.out.println("\n");
		
		//4. getProductList Test ::
		System.out.println(":: 4. getProductList(SELECT)  ? ");
		Search search = new Search();
		search.setSearchCondition("productId");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("product04");
		search.setProductId( arrayList );
		
		System.out.println(":: List<Product> 내용 : "+ productDao.getProductList(search) );
		System.out.println("\n");
		
		//5. removeProduct Test
		System.out.println(":: 5. removeProduct (DELETE)  ? ");
		System.out.println(":: "+productDao.removeProduct(product.getProductId()) );
		System.out.println("\n");
		
		//6. getProductList Test 
		System.out.println(":: 6. getProductList(SELECT)  ? ");
		System.out.println("List<Product> 내용 : "+ productDao.getProductList(search) );
		System.out.println("\n");
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession 닫기..");
		sqlSession.close();
	}//end of main
}//end of class