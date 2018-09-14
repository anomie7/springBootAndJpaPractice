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
	Category parent;
	Category actionMovie;
	Category romanceMovie;
	Category comedyMovie;

	@Before
	public void testCategory() {
		parent = Category.builder().name("영화").build();
		actionMovie = Category.builder().name("액션").build();
		romanceMovie = Category.builder().name("로맨스").build();
		comedyMovie = Category.builder().name("코메디").build();
		parent.addChild(actionMovie);
		parent.addChild(actionMovie);
		parent.addChild(actionMovie);
		parent.addChild(romanceMovie);
		parent.addChild(comedyMovie);
		entityManager.persist(parent);
		
		Category test = entityManager.find(Category.class, 1L);
		assertEquals("카테고리 아이디가 일치하지 않습니다.", parent.getId(), test.getId());
		assertEquals("자식 객체가 일치하지 않습니다.", actionMovie, test.getChild().get(0));
		assertEquals("자식 객체가 일치하지 않습니다.", romanceMovie, test.getChild().get(1));
		assertEquals("자식 객체가 일치하지 않습니다.", comedyMovie, test.getChild().get(2));
	}

	@Test
	public void testItem() {
		Album album = Album.builder().name("화양연화").price(20000).stockQuantity(450).artist("bts").build();
		entityManager.persist(album);
		Book book = Book.builder().name("노인과 바다").price(10000).stockQuantity(200).author("헤밍웨이").isbn("12200-10")
				.build();
		entityManager.persist(book);
		Movie movie = Movie.builder().name("안시성").price(12000).stockQuantity(1000).actor("조인성").director("조민기").build();
		movie.addCategory(actionMovie);
		movie.addCategory(comedyMovie);
		entityManager.persist(movie);

		Album falbum = entityManager.find(Album.class, 1L);
		Movie fmovie = entityManager.find(Movie.class, 3L);
		Book fbook = entityManager.find(Book.class, 2L);
		
		Category test = entityManager.find(Category.class, 2L);
	}

}
