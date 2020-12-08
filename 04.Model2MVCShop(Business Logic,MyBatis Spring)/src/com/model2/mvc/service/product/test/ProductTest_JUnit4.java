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
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductTest_JUnit4 {
	
	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		product.setProdName("JUnit");
		product.setProdDetail("JUnit단일 테스트를 시작합니다");
		product.setManuDate("2000-01-01");
		product.setPrice(12345);
		product.setFileName("junit");
		
		productService.addProduct(product);
		
		//product = productService.getProduct(10000); //junit이 아닌 삼성센스라 fail(원하는 결과x)
		product = productService.getProduct(10124);//junit
		
		// 디버깅
		 System.out.println(product);

		// ==> API 확인:각 Assert 구문은 어떤 조건이 참인지 검증하는 방법이다. 단언한 조건이 참이 아니면 테스트는 그 자리에서 멈추고 실패한다.
		Assert.assertEquals("JUnit", product.getProdName());
		Assert.assertEquals("JUnit단일 테스트를 시작합니다", product.getProdDetail());
		Assert.assertEquals("2000-01-01", product.getManuDate());
		Assert.assertEquals(12345, product.getPrice());
		Assert.assertEquals("junit", product.getFileName());
	}
	
	@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		
		product = productService.getProduct(10124);
		
		// ==> console 확인
		System.out.println(product);
		
		// ==> API 확인
		Assert.assertEquals("JUnit", product.getProdName());
		Assert.assertEquals("JUnit단일 테스트를 시작합니다", product.getProdDetail());
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
		Assert.assertEquals("JUnit단일 테스트를 시작합니다", product.getProdDetail());
		Assert.assertEquals("2000-01-01", product.getManuDate());
		Assert.assertEquals(12345, product.getPrice());
		Assert.assertEquals("junit", product.getFileName());
		
		product.setProdName("바꿔");
		product.setProdDetail("모두 바꿔");
		product.setManuDate("1234-01-01");
		product.setPrice(10000);
		product.setFileName("junit");
		
		productService.updateProduct(product);
		
		product = productService.getProduct(10124);
		Assert.assertNotNull(product);
		
		//==> console 확인
		//System.out.println(product);
		
		//==> API 확인
		Assert.assertEquals("바꿔", product.getProdName());
		Assert.assertEquals("모두 바꿔", product.getProdDetail());
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
	 	
	 	//==> console 확인
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
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List <Object>) map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
	 	//==> console 확인
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
	 	
	 	//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = productService.getProductList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
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

		// ==> console 확인
		System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

		System.out.println("=======================================");

		search.setSearchCondition("2");
		search.setSearchKeyword("" + System.currentTimeMillis());
		map = productService.getProductList(search);

		list = (List<Object>) map.get("list");
		Assert.assertEquals(0, list.size());

		// ==> console 확인
		System.out.println(list);

		totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}

}
