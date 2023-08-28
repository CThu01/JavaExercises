package com.jdc.mkt.test;

import org.junit.jupiter.api.Test;

import com.jdc.mkt.entity.Product;

public class RemoveTest extends EntityFactory{

	@Test
	void testRemove() {
		var em = emf.createEntityManager();
		var p = em.find(Product.class, 5);
		
		em.getTransaction().begin();
		
		em.remove(p);
		
		em.getTransaction().commit();
	}
	
}


