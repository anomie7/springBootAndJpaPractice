package com.anomie.webservice.commons;

import java.util.List;

import com.anomie.webservice.item.Album;
import com.anomie.webservice.item.Book;
import com.anomie.webservice.item.Item;
import com.anomie.webservice.item.Movie;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ItemDTO {
	private Long itemId;
	private String itemName;
	private int price;
	private int stockQuantity;
	private List<Long> category_id;
	private String author;
	private String isbn;
	private String artist;
	private String etc;
	private String director;
	private String actor;
	private String kindOfItem;
	
	@Builder
	public ItemDTO(Long itemId, String itemName, int price, int stockQuantity, List<Long> category_id, String author,
			String isbn, String artist, String etc, String director, String actor, String kindOfItem) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.category_id = category_id;
		this.author = author;
		this.isbn = isbn;
		this.artist = artist;
		this.etc = etc;
		this.director = director;
		this.actor = actor;
		this.kindOfItem = kindOfItem;
	}
	
	public Item toEntity() {
		
		if(this.kindOfItem == null) {
			throw new NullPointerException();
		}
		
		if(this.kindOfItem.equals("book")) {
			Book book = Book.builder()
					.name(this.itemName)
					.price(price)
					.stockQuantity(stockQuantity)
					.author(author)
					.isbn(isbn).build();
			;
			return book;
		}else if(this.kindOfItem.equals("album")) {
			Album album = Album.builder()
					.name(this.itemName)
					.price(price)
					.stockQuantity(stockQuantity)
					.artist(artist)
					.etc(etc).build();
			return album;
		}else if(this.kindOfItem.equals("movie")) {
			Movie movie = Movie.builder()
					.name(this.itemName)
					.price(price)
					.stockQuantity(stockQuantity)
					.director(director)
					.actor(actor)
					.build();
			return movie;
		}
		return null;
	}
}
