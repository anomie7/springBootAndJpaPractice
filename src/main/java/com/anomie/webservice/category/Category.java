package com.anomie.webservice.category;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.anomie.webservice.item.Item;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Category {
	@Id @GeneratedValue
	@Column(name="CATEGORY_ID")
	private Long id;
	
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade= CascadeType.ALL)
	@JoinColumn(name="PARENT_ID")
	private Category parent;
	
	@OneToMany(mappedBy="parent")
	private List<Category> child = new ArrayList<>();
	
	@ManyToMany(mappedBy="categories", fetch=FetchType.LAZY)
	private List<Item> items = new ArrayList<>();
	
	@Builder
	public Category(Long id, String name, Category parent) {
		super();
		this.id = id;
		this.name = name;
		this.parent = parent;
	}
	
	public void addChild(Category child) {
		if (!this.child.isEmpty() && this.child.contains(child)) {
			return;
		}
		this.child.add(child);
		child.setParent(this);
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((child == null) ? 0 : child.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		if (child == null) {
			if (other.child != null)
				return false;
		} else if (!child.equals(other.child))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		return true;
	}
	
}
