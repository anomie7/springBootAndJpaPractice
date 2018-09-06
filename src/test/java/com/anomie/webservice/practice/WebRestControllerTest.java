package com.anomie.webservice.practice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.anomie.webservice.practice.WebRestController;

@RunWith(SpringRunner.class)
@WebMvcTest(WebRestController.class)
public class WebRestControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private WebRestController webRestController;
	
	@Test
	public void restControllerShouldReturnTextHello() throws Exception {
		this.mvc.perform(get("/hello").accept(org.springframework.http.MediaType.TEXT_PLAIN))
		.andExpect(status().isOk());
	}

}
