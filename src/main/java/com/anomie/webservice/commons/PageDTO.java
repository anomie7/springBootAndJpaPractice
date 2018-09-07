package com.anomie.webservice.commons;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageDTO {
	private final int DISPLAY_PAGE_SIZE = 10;
	
	private int currentPage;
	private int totalPage;
	private int numberOfElements;
	private List<?> content;
	private boolean isFirst;
	private boolean isLast;
	private boolean hasNext;
	private boolean hasPrivious;
	private int displayFirstPage;
	private int displayLastPage;
	
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

	public void setDisplayPageNumbers() {
		int tmp = (currentPage/DISPLAY_PAGE_SIZE);
		if((currentPage%DISPLAY_PAGE_SIZE) > 0) {
			this.displayFirstPage = tmp > 0 ? tmp * DISPLAY_PAGE_SIZE + 1 : 1;
			this.displayLastPage = displayFirstPage + (DISPLAY_PAGE_SIZE-1);
		}else if((currentPage%DISPLAY_PAGE_SIZE) == 0) {
			this.displayLastPage = tmp * DISPLAY_PAGE_SIZE;
			this.displayFirstPage =  displayLastPage - (DISPLAY_PAGE_SIZE-1);
		}
	}
	
}
