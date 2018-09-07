package com.anomie.webservice.commons;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestPageDTO {

	@Test
	public void TestgetDisplayPageNumbers() {
		PageDTO result = PageDTO.builder().currentPage(26).totalPage(100).build();
		result.setDisplayPageNumbers();
		assertEquals("보여줄 첫번쨰 페이지 숫자가 다릅니다.",21, result.getDisplayFirstPage());
		assertEquals("보여줄 마지막 페이지 숫자가 다릅니다.",30, result.getDisplayLastPage());
	}
}
