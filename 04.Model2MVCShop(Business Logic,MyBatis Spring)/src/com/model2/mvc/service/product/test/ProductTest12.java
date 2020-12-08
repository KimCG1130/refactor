package com.model2.mvc.service.product.test;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.impl.ProductDaoImpl11;
import com.model2.mvc.service.product.impl.ProductServiceImpl12;

/*
 * FileName : MyBatisTestApp12.java
  * :: Business Layer unit Test : Service + Persistence (MyBatis + DAO)
  */
public class ProductTest12 {
	
	///main method
	public static void main(String[] args) throws Exception{

		//==> SqlSessionFactoryBean.getSqlSession()�� �̿� SqlSession instance GET
SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		//==> ProductDaoImpl11 ���� �� sqlSession instance setter injection
		ProductDaoImpl11 productDao = new ProductDaoImpl11();
		productDao.setSqlSession(sqlSession);
		System.out.println("\n");
		
		ProductServiceImpl12 productService = new ProductServiceImpl12();
		productService.setProductDao(productDao);
		System.out.println("\n");
		
		//==> Test �� Product instance ����  
		Product product = new Product("Test", "�׽�Ʈ��", "98-01-01", 100, "qwer");
		
		//1. addProduct Test  
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		productDao.addProduct(product);//void���� �ַܼ� ���̰� �� �� �ִ� �����������
		System.out.println("\n");
		
		//2. getProduct Test ::  insert Ȯ�� 
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		product.setProdNo(20000);
		System.out.println(":: "+ productDao.getProduct(product.getProdNo()) );
		System.out.println("\n");

		//3. uadateProduct Test  ::  ����
		product.setProdDetail("�ٲ��");
		System.out.println(":: 3. update(UPDATE)  ? ");
		product.setProdNo(20000);
		productDao.updateProduct(product);
		System.out.println("\n");
		
		//4. getProductList Test ::
		//System.out.println(":: 4. getProductList(SELECT)  ? ");
		//Search search = new Search();
		//search.setSearchCondition("prodNo");
		//ArrayList<String> arrayList = new ArrayList<String>();
		//arrayList.add("product");
		//search.setProd( arrayList );
		
		//System.out.println(":: List<Product> ���� : "+ productDao.getProductList(search) );
		System.out.println("\n");
		
		//5. removeProduct Test
		System.out.println(":: 5. removeProduct (DELETE)  ? ");
	    productDao.deleteProduct(product.getProdNo());
		System.out.println("\n");
	 	
		//6. getProductList Test 
		System.out.println(":: 6. getProductList(SELECT)  ? ");
		//System.out.println("List<Product> ���� : "+ productDao.getProductList(search) );
		System.out.println("\n");
		
		Search search = new Search();
		
		//1. UserMapper10.getUserList Test 
		System.out.println(":: 1. getUserList01(SELECT)  ? ");
		
		//2. UserMapper10.getUserList Test 
	 	search.setCurrentPage(1);
	 	search.setPageSize(6);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("0");
	 	
	 	productDao.getProductList(search);
		
		System.out.println(":: 2. getTotalCount  ? ");
		int a = productDao.getTotalCount(search);
		System.out.println(a);
		
		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
	}//end of main
}//end of class