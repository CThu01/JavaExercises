package com.jdc.mkt.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Basic;
import javax.persistence.ElementCollection;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.FetchType.EAGER;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "product")
public class Product implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	private int price;
	@ElementCollection
	private List<String> tags;
	
	@ManyToOne /* (fetch = LAZY) */
	private Category category;

	public Product(String name, int price) {
		super();
		this.name = name;
		this.price = price;
	}
	
	
}



