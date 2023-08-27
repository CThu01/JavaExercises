package com.jdc.mkt.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.jdc.mkt.entity.Category;
import com.jdc.mkt.entity.Product;

@TestMethodOrder(OrderAnnotation.class)
public class ManipulateEntityTest {

	static EntityManagerFactory emf;
	
	@BeforeAll
	static void init() {
		emf = Persistence.createEntityManagerFactory("manage-entities");
	}
	
	
	@AfterAll
	static void close() {
		if(null != emf && emf.isOpen()) {
			emf.close();
		}
	}
	
	
	@Test
	void insetCategory() {
		// transient state
		var cat = new Category("Snacks");
		
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		// to be managed state
		em.persist(cat);
		
		em.getTransaction().commit();
		
		em = emf.createEntityManager();
		// to be manage state
		var category = em.find(Category.class, 3);
//		assertTrue(em.contains(category));
		//to be detached state from manage
		em.detach(category);
//		assertFalse(em.contains(category));
		// to be managed state from merge
		var categoryUpdate = em.merge(category);
//		assertTrue(em.contains(categoryUpdate));
	}
	
	
//	@ParameterizedTest
	@CsvSource("Sunflower seeds,2000,Snacks")
	void insert(String pName,int price,String cName) {
		// to be transient state
		var product = new Product(pName,price);
		var category = new Category(cName);
		product.setCategory(category);
		
		var em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(product);
		em.persist(category);
		em.getTransaction().commit();
	}
	
	@Order(3)
	@ParameterizedTest
	@ValueSource(ints=4)
	void find_method(int id) {
		var em = emf.createEntityManager();
		var product = em.find(Product.class, id);
		
		assertEquals("Lemon",product.getName());
		assertNull(product);
	}
	
	@Order(4)
	@ParameterizedTest
	@ValueSource(ints=4)
	void reference_method(int id) {
		var em = emf.createEntityManager();
		var product = em.find(Product.class, id);
		
		assertEquals("Lemon",product.getName());
		assertThrows(EntityNotFoundException.class,() -> em.getReference)
	}
	
	
}









