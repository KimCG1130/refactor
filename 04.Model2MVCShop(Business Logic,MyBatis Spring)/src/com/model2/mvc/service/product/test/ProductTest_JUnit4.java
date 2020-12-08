package com.model2.mvc.service.product.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

/*
 *	FileName :  ProductServiceTest.java
 * �� JUnit4 (Test Framework) �� Spring Framework ���� Test( Unit Test)
 * �� Spring �� JUnit 4�� ���� ���� Ŭ������ ���� ������ ��� ���� �׽�Ʈ �ڵ带 �ۼ� �� �� �ִ�.
 * �� @RunWith : Meta-data �� ���� wiring(����,DI) �� ��ü ����ü ����
 * �� @ContextConfiguration : Meta-data location ����
 * �� @Test : �׽�Ʈ ���� �ҽ� ����
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductTest_JUnit4 {
	
	//==>@RunWith,@ContextConfiguration �̿� Wiring, Test �� instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("JUnit");
		product.setProdDetail("JUnit���� �׽�Ʈ�� �����մϴ�");
		product.setManuDate("2000-01-01");
		product.setPrice(12345);
		product.setFileName("junit");
		
		productService.addProduct(product);
		
		//product = productService.getProduct(10000); //junit�� �ƴ� �Ｚ������ fail(���ϴ� ���x)
		product = productService.getProduct(10124);//junit
		
		// �����
		 System.out.println(product);

		// ==> API Ȯ��:�� Assert ������ � ������ ������ �����ϴ� ����̴�. �ܾ��� ������ ���� �ƴϸ� �׽�Ʈ�� �� �ڸ����� ���߰� �����Ѵ�.
		Assert.assertEquals("JUnit", product.getProdName());
		Assert.assertEquals("JUnit���� �׽�Ʈ�� �����մϴ�", product.getProdDetail());
		Assert.assertEquals("2000-01-01", product.getManuDate());
		Assert.assertEquals(12345, product.getPrice());
		Assert.assertEquals("junit", product.getFileName());
	}
	
	@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		
		product = productService.getProduct(10124);
		
		// ==> console Ȯ��
		System.out.println(product);
		
		// ==> API Ȯ��
		Assert.assertEquals("JUnit", product.getProdName());
		Assert.assertEquals("JUnit���� �׽�Ʈ�� �����մϴ�", product.getProdDetail());
		Assert.assertEquals("2000-01-01", product.getManuDate());
		Assert.assertEquals(12345, product.getPrice());
		Assert.assertEquals("junit", product.getFileName());
		
		Assert.assertNotNull(productService.getProduct(10124));
		//Assert.assertNotNull(productService.getProduct(10002));
	}
	
	//@Test
	public void testUpdateProduct() throws Exception {
		
		Product product = productService.getProduct(10143);
		Assert.assertNotNull(product);
		
		Assert.assertEquals("JUnit", product.getProdName());
		Assert.assertEquals("JUnit���� �׽�Ʈ�� �����մϴ�", product.getProdDetail());
		Assert.assertEquals("2000-01-01", product.getManuDate());
		Assert.assertEquals(12345, product.getPrice());
		Assert.assertEquals("junit", product.getFileName());
		
		product.setProdName("�ٲ�");
		product.setProdDetail("��� �ٲ�");
		product.setManuDate("1234-01-01");
		product.setPrice(10000);
		product.setFileName("junit");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10124);
		Assert.assertNotNull(product);
		
		//==> console Ȯ��
		//System.out.println(product);
		
		//==> API Ȯ��
		Assert.assertEquals("�ٲ�", product.getProdName());
		Assert.assertEquals("��� �ٲ�", product.getProdDetail());
		Assert.assertEquals("1234-01-01", product.getManuDate());
		Assert.assertEquals(10000, product.getPrice());
		Assert.assertEquals("junit", product.getFileName());
	}
	
	//@Test 
	public void testDeleteProduct() throws Exception {
		
		productService.deleteProduct(30000);
		
		Product product = productService.getProduct(30000);
		
		Assert.assertNotNull(product);
	}
	
	@Test
	public void testGetProductListAll() throws Exception {
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map <String, Object> map = productService.getProductList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdNo() throws Exception {
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10143");
	 	Map <String, Object> map = productService.getProductList(search);
	 	
	 	List <Object> list = (List <Object>) map.get("list");
	 	Assert.assertEquals(0, list.size()); //success
	 	//Assert.assertEquals(3, list.size()); //fail
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List <Object>) map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdName() throws Exception {
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("a");
	 	
	 	Map <String, Object> map = productService.getProductList(search);
	 	
	 	List <Object> list = (List <Object>) map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console Ȯ��
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdPrice() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("2");
		search.setSearchKeyword("12");

		Map<String, Object> map = productService.getProductList(search);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console Ȯ��
		System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("2");
		search.setSearchKeyword("" + System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());

		// ==> console Ȯ��
		System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}

}
