package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;

public class ManageEntitiesTest extends EntityFactory{

	@Test
	void testUpdate() {
		var em = emf.createEntityManager();
		
		var p = em.find(Product.class,1);
		
		em.getTransaction().begin();
		
		p.setName("Other");
		
		em.getTransaction().commit();
		
		
		
	}
	
	
}
