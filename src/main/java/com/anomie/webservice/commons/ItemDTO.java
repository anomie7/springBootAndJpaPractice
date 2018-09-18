package com.anomie.webservice.commons;

import java.util.List;
import java.util.stream.Collectors;

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
	
	public static ItemDTO toItemDTO(Item item) {
		if (item.getClass().equals(Book.class)) {
			return toBook(item);
		} else if (item.getClass().equals(Album.class)) {
			return toAlbum(item);
		} else if (item.getClass().equals(Movie.class)) {
			return toMovie(item);
		}
		return null;
	}
	
	public static ItemDTO toBook(Item item) {
		Book book = (Book) item;
		ItemDTO result = null;
		List<Long> categoryIds = book.getCategories().stream().map(m -> m.getId()).collect(Collectors.toList());
		result = ItemDTO.builder().itemName(book.getName()).itemId(book.getId()).price(book.getPrice())
				.stockQuantity(book.getStockQuantity()).author(book.getAuthor()).isbn(book.getIsbn()).category_id(categoryIds).build();
		return result;
	}
	
	public static ItemDTO toAlbum(Item item) {
		Album album = (Album) item;
		ItemDTO result = null;
		List<Long> categoryIds = album.getCategories().stream().map(m -> m.getId()).collect(Collectors.toList());
		result = ItemDTO.builder().itemName(album.getName()).itemId(album.getId()).price(album.getPrice())
				.stockQuantity(album.getStockQuantity()).artist(album.getArtist()).etc(album.getEtc()).category_id(categoryIds).build();
		return result;
	}
	
	public static ItemDTO toMovie(Item item) {
		Movie movie = (Movie) item;
		ItemDTO result = null;
		List<Long> categoryIds = movie.getCategories().stream().map(m -> m.getId()).collect(Collectors.toList());
		result = ItemDTO.builder().itemName(movie.getName()).itemId(movie.getId()).price(movie.getPrice())
				.stockQuantity(movie.getStockQuantity()).director(movie.getDirector()).actor(movie.getActor()).category_id(categoryIds).build();
		return result;
	}
}
