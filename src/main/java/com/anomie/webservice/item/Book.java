package com.anomie.webservice.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@Entity
@DiscriminatorValue("B")
public class Book extends Item{
	private String author;
	private String isbn;
	
	@Builder
	public Book(Long id, String name, int price, int stockQuantity, String author, String isbn) {
		super(id, name, price, stockQuantity);
		this.author = author;
		this.isbn = isbn;
	}
}
