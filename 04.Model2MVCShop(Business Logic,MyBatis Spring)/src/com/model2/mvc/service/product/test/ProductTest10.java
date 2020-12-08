package com.model2.mvc.service.product.test;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;


public class ProductTest10 {
	
	///main method
	public static void main(String[] args) throws Exception{
	
		//==> SqlSessionFactoryBean.getSqlSession()�� �̿� SqlSession instance GET
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		System.out.println("\n");
		
		//==> Test �� User instance ����  
		Product product = new Product("Test", "�׽�Ʈ��", "98-01-01", 100, "qwer");
		
		
//		//1. ProductMapper10.addProduct Test ok
		System.out.println(":: 1. addProduct(INSERT)  ? ");
		System.out.println(":: "+ sqlSession.insert("ProductMapper.addProduct",product));
		System.out.println("\n");
//		
//		//2. ProductMapper10.getProduct Test  ok
		System.out.println(":: 2. getProduct(SELECT)  ? ");
		product.setProdNo(20000);
		System.out.println(":: "+sqlSession.selectOne("ProductMapper.getProduct",product.getProdNo()));//���� �ϳ��� ����
		System.out.println("\n");
//		
//		//3. ProductMapper10.updateProduct Test  ::
		product.setProdDetail("�ٲ��");
		System.out.println(":: 3. update(UPDATE)  ? ");
		System.out.println(":: "+ sqlSession.update("ProductMapper.updateProduct",product));
		System.out.println("\n");
//		
//		//4. ProductMapper10.getProductList Test  :: Dynamic Query Ȯ�� 
		System.out.println(":: 4. getProduct(SELECT)  ? ");
		System.out.println(":: "+sqlSession.selectOne("ProductMapper.getProduct",product.getProdNo()) );
		System.out.println("\n");
//		
//		//5. ProductMapper10.removeProduct Test
		System.out.println(":: 5. Use10.removeProduct (DELETE)  ? ");
		System.out.println(":: "+sqlSession.delete("ProductMapper.deleteProduct",product.getProdNo()) );
		System.out.println("\n");
		System.out.println("/////////////////////////////////////////////////////////////////////////////////////////////////");
//		System.out.println("\n");
		
		

		//==> Test �� Search instance ���� 
		Search search = new Search();
		
		//1. UserMapper10.getUserList Test 

		
		//2. UserMapper10.getUserList Test 
	 	search.setCurrentPage(1);
	 	search.setPageSize(6);
	 	search.setSearchCondition("2");
	 	search.setSearchKeyword("0");

	 	//SqlSessionFactoryBean.printList( sqlSession.selectList("ProductMapper.getProductList",search) );
	 	System.out.println("�����");
		System.out.println(":: 2. getTotalCount  ? ");
		int a = sqlSession.selectOne("ProductMapper.getTotalCount", search);
		System.out.println(a);
		
	}//end of main
}//end of class