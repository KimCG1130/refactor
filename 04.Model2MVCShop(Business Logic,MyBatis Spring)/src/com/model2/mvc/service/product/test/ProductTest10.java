package com.model2.mvc.service.product.test;

import java.sql.Date;
import java.util.ArrayList;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.impl.ProductDaoImpl11;


import org.apache.ibatis.session.SqlSession;


//prodNo
//String prodName
//String prodDetail
//price
//String manuDate
//String fileName
//Date regDate

public class ProductTest10 {
	
	///main method
	public static void main(String[] args) throws Exception{
		
		//==> SqlSessionFactoryBean.getSqlSession()�� �̿� SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		System.out.println("\n");
		
		//==> Test �� Product instance ����  
		Product product = new Product();
		
		//1. ProductMapper10.addProduct Test  :: products table age/grade/redDate �Է°� Ȯ���Ұ� : OK 
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		System.out.println(":: "+ sqlSession.insert("ProductMapper10.addProduct",product));
		System.out.println("\n");
		
		//2. ProductMapper10.getProduct Test :: �ָ� inert Ȯ�� 
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		System.out.println(":: "+sqlSession.selectOne("ProductMapper10.getProduct",product.getProdNo()) );
		System.out.println("\n");
		
		//3. ProductMapper10.uadateProduct Test  :: products table age/grade/redDate �Է°� Ȯ���Ұ� : OK
		//                              
		product.setProdName("");
		product.setPrice(777);
		System.out.println(":: 3. update(UPDATE)  ? ");
		System.out.println(":: "+ sqlSession.update("ProductMapper10.updateProduct",product));
		System.out.println("\n");
		
		//4. ProductMapper10.getProductList Test  :: Dynamic Query Ȯ�� id=product04/name=�´� �˻�
		System.out.println(":: 4. getProduct(SELECT)  ? ");
		System.out.println(":: "+sqlSession.selectOne("ProductMapper10.getProduct",product.getProdNo()) );
		System.out.println("\n");
		
		//5. ProductMapper10.removeProduct Test
		System.out.println(":: 5. Use10.removeProduct (DELETE)  ? ");
		System.out.println(":: "+sqlSession.delete("ProductMapper10.removeProduct", 
																						   product.getProdNo()) );
		System.out.println("\n");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println("\n");
		
		//==> Test �� Search instance ���� 
		Search search = new Search();
		
		//1. ProductMapper10.getProductList Test 
		System.out.println(":: 1. getProductList01(SELECT)  ? ");
		SqlSessionFactoryBean.printList( sqlSession.selectList("ProductMapper10.getProductList",search) );
		
		//2. ProductMapper10.getProductList Test 
		search.setSearchCondition("productId");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("product01");
		search.setProdNo( arrayList );
		
		System.out.println(":: 2. getProductList01(SELECT)  ? ");
		SqlSessionFactoryBean.printList( sqlSession.selectList("ProductMapper10.getProductList",search) );
		
		//3. ProductMapper10.getProductList Test 
		search.setSearchCondition("prodName");
		search.setProdName(  );
		
		System.out.println(":: 3. getProductList01(SELECT)  ? ");
		SqlSessionFactoryBean.printList( sqlSession.selectList("ProductMapper10.getProductList",search) );

		//END::SqlSession  close
		System.out.println("::END::SqlSession �ݱ�..");
		sqlSession.close();
		
	}//end of main
}//end of class