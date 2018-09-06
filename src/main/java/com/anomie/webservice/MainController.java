package com.anomie.webservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping(path="/")
	public String index(Model model, HttpServletRequest req, HttpServletResponse res) {
		return "index";
	}
}
