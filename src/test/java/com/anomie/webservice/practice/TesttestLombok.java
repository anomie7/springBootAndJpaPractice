package com.anomie.webservice.practice;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TesttestLombok {
	
	@Autowired
	private testLombokRepository tr;
	
	private testLombok tl;
	
	@Before
	public void 객체생성() {
		tl = testLombok.builder()
						.name("미누")
						.build();
		tr.save(tl);
	}
	
	@Test
	public void 객체_동일성_확인() {
		testLombok tl2 = tr.findAll().iterator().next();
		assertEquals("생성한 객체와 조회한 객체가 같지 않다",tl, tl2);
	}
}
