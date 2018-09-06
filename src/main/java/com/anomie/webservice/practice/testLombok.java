package com.anomie.webservice.practice;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(access= AccessLevel.PROTECTED)

@Entity
public class testLombok {
	@Id @GeneratedValue
	@Column(name="TEST_LOMBOK_ID")
	private Long id;
	
	private String name;
	
	@Builder.Default
	@Transient
	private List<String> tmp = new ArrayList<>();

}
