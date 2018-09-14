package com.anomie.webservice.item;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
@Entity
@DiscriminatorValue("M")
public class Movie extends Item{
	private String director;
	private String actor;
	
	@Builder
	public Movie(Long id, String name, int price, int stockQuantity, String director, String actor) {
		super(id, name, price, stockQuantity);
		this.director = director;
		this.actor = actor;
	}
}
