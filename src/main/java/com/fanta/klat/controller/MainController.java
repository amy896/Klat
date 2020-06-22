package com.fanta.klat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	//by 미경, 메인 화면 보여주기 
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String showMain() {
		return "main/main";
	}

}
