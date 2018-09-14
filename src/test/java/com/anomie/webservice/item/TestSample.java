package com.anomie.webservice.item;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestSample {
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void test() {
		Category parent = Category.builder()
									.name("영화").build();
		Category actionMovie = Category.builder()
										.name("액션").build();
		Category romanceMovie = Category.builder()
										.name("로맨스").build();
		Category comedyMovie = Category.builder()
										.name("코메디").build();
		parent.addChild(actionMovie);
		parent.addChild(actionMovie);
		parent.addChild(actionMovie);
		parent.addChild(romanceMovie);
		parent.addChild(comedyMovie);
		
		entityManager.persist(parent);
		Category test = entityManager.find(Category.class, 1L);
		assertEquals("카테고리 아이디가 일치하지 않습니다." ,parent.getId(), test.getId());
		assertEquals("자식 객체가 일치하지 않습니다.", actionMovie, test.getChild().get(0));
		assertEquals("자식 객체가 일치하지 않습니다.", romanceMovie, test.getChild().get(1));
		assertEquals("자식 객체가 일치하지 않습니다.", comedyMovie, test.getChild().get(2));
	}

}
