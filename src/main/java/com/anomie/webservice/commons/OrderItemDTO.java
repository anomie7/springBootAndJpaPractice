package com.anomie.webservice.commons;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor 
@AllArgsConstructor
@Builder
@Setter @Getter
public class OrderItemDTO {
	private Long memberId;
	private Long itemId;
	private int price;
	private int count;
}
