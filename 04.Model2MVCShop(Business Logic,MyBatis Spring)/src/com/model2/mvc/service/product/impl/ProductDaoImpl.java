//DaoImpl11에 어노테이션(wiring) 추가
  package com.model2.mvc.service.product.impl;
  
  import java.util.List;
  
  import org.apache.ibatis.session.SqlSession;
  import org.springframework.beans.factory.annotation.Autowired; 
  import org.springframework.beans.factory.annotation.Qualifier; 
  import org.springframework.stereotype.Repository;
  
  import com.model2.mvc.common.Search; 
  import com.model2.mvc.service.domain.Product; 
  import com.model2.mvc.service.product.ProductDao;
  
  //==> 상품관리 DAO CRUD 구현 //데이터베이스와 직접적인 통신을 담당하는 퍼시스턴스 계층을 담당할 ProductDao 인터페이스구현
  
  @Repository("productDaoImpl") public class ProductDaoImpl implements
  ProductDao {
  
  @Autowired
  
  @Qualifier("sqlSessionTemplate") private SqlSession sqlSession;
  
  public void setSqlSession(SqlSession sqlSession) { this.sqlSession =
  sqlSession; }
  
  public ProductDaoImpl() { System.out.println(this.getClass()); }
  
  @Override public void addProduct(Product product) throws Exception {
  sqlSession.insert("ProductMapper.addProduct", product); 
  }
  
  @Override public Product getProduct(int prodNo) throws Exception {
	  return (Product) sqlSession.selectOne("ProductMapper.getProduct", prodNo); 
  }
  
  @Override public void updateProduct(Product product) throws Exception {
  sqlSession.update("ProductMapper.updateProduct", product);
  }
  
  @Override public void deleteProduct(int prodNo) throws Exception {
  sqlSession.delete("ProductMapper.deleteProduct", prodNo); 
  }
  
  @Override public List<Product> getProductList(Search search) throws Exception{
	  return sqlSession.selectList("ProductMapper.getProductList", search); 
	  }
  
  @Override
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
  
  }
 