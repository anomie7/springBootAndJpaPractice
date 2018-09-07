package com.anomie.webservice.commons;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageDTO {
	private int currentPage;
	private int totalPage;
	private int numberOfElements;
	private List<?> content;
	private boolean isFirst;
	private boolean isLast;
	private boolean hasNext;
	private boolean hasPrivious;
	
	@Builder
	public PageDTO(int currentPage, int totalPage, int numberOfElements,List<?> content, boolean isFirst, boolean isLast, boolean hasNext,
			boolean hasPrivious) {
		super();
		this.currentPage = currentPage;
		this.totalPage = totalPage;
		this.numberOfElements = numberOfElements;
		this.content = content;
		this.isFirst = isFirst;
		this.isLast = isLast;
		this.hasNext = hasNext;
		this.hasPrivious = hasPrivious;
	}
	
	
	
}
