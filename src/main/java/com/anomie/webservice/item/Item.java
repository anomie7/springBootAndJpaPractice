package com.anomie.webservice.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.anomie.webservice.category.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
public abstract class Item {
	@Id @GeneratedValue
	@Column(name="ITEM_ID")
	private Long id;
	private String name;
	private int price;
	private int stockQuantity;
	
	@ManyToMany(cascade= CascadeType.PERSIST, fetch= FetchType.LAZY)
	@JoinTable(name="CATEGORY_ITEM",
	   joinColumns = @JoinColumn(name="ITEM_ID"),
	   inverseJoinColumns= @JoinColumn(name="CATEGORY_ID"))
	private List<Category> categories = new ArrayList<>();
	
	public Item(Long id, String name, int price, int stockQuantity) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.stockQuantity = stockQuantity;
	}
	
	public void addCategory(Category category) {
		if(!this.categories.isEmpty() && this.categories.contains(category)) {
			return;
		}
		this.categories.add(category);
	}
}
