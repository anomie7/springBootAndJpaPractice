package com.anomie.webservice.commons;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestPageDTO {
	private PageDTO result;
	
	@Before
	public void 객체_생성() {
		result = PageDTO.builder().currentPage(26).totalPage(100).build();
		result.setDisplayPageNumbers();
	}
	
	@Test
	public void TestgetDisplayPageNumbers() {
		assertEquals("보여줄 첫번쨰 페이지 숫자가 다릅니다.",21, result.getDisplayFirstPage());
		assertEquals("보여줄 마지막 페이지 숫자가 다릅니다.",30, result.getDisplayLastPage());
	}
	
	@Test(expected =ArrayIndexOutOfBoundsException.class )
	public void testGetPageNumbers() {
		result.setPageNumbers();
		assertEquals(21, result.getPAGE_NUMBERS()[0]);
		assertEquals(26, result.getPAGE_NUMBERS()[5]);
		assertEquals(30, result.getPAGE_NUMBERS()[9]);
		assertEquals(30, result.getPAGE_NUMBERS()[10]);
	}
}
