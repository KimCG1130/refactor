package com.model2.mvc.service.product.test;

import java.util.ArrayList;

import com.model2.mvc.common.Search;

import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
 * FileName : MyBatisTestApp13.java
  * :: Business Layer unit Test : Service + Persistence (Spring +mybatis + DAO)
  */
public class ProductTest13 {
	
	///main method
	public static void main(String[] args) throws Exception{

		ApplicationContext context =
			new ClassPathXmlApplicationContext(
												new String[] {	"/config/commonservice13.xml",
																"/config/productservice13.xml" });//common user wiring
																				//����Ʈ���� �ʾƵ� �����ΰ����� xml �޾ƿü�����
		System.out.println("\n");

		//==> Bean/IoC Container �� ���� ȹ���� ProductService �ν��Ͻ� ȹ��
		ProductService productService = (ProductService)context.getBean("productServiceImpl12");
		
		System.out.println("DaoImpl,Serviec ����\n");
		
		//==> Test �� Product instance ����  
		Product product = new Product("Test", "�׽�Ʈ��", "98-01-01", 100, "qwer");
				
		//1. addProduct Test  
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		productService.addProduct(product);
		System.out.println("\n");
//		
//		//2. getProduct Test :: �ָ� inert Ȯ�� 
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		product.setProdNo(10137);
		System.out.println(":: "+ productService.getProduct(product.getProdNo()));
		System.out.println("\n");
		//3. ProductMapper10.uadateProduct Test  :: 
		product.setProdDetail("�ٲ㿵");
		product.setProdNo(20000);
//		System.out.println(":: 3. update(UPDATE)  ? ");
//		productDao.updateProduct(product);
		System.out.println("\n");
//		
////		4. ProductMapper10.getProductList Test  :: Dynamic Query Ȯ��
//		System.out.println(":: 4. getProduct(SELECT)  ? ");
//		System.out.println(":: "+ productDao.getProduct(product.getProdNo()));
//		System.out.println("\n");
//		
		//5. ProductMapper10.removeProduct Test
//		System.out.println(":: 5. Use10.removeProduct (DELETE)  ? ");
//		productDao.deleteProduct(product.getProdNo());
//		System.out.println("\n");
//		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////");
//		System.out.println("\n");
		
		//==> Test �� Search instance ���� 
//		Search search = new Search();
//		
//		//1. ProductMapper10.getProductList Test 
//		System.out.println(":: 1. getProductList01(SELECT)  ? ");
//		
//		//2. ProductMapper10.getProductList Test 
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(6);
//	 	search.setSearchCondition("2");
//	 	search.setSearchKeyword("0");
//	 	
//	 	productService.getProductList(search);
//		
//		System.out.println(":: 2. getTotalCount  ? ");
//		int a = productDao.getTotalCount(search);
//		System.out.println(a);
	}//end of main
}//end of class