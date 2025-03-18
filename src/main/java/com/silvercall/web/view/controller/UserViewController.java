package com.silvercall.web.view.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("user")
public class UserViewController extends AbstractViewController {

	@GetMapping(path = "login")
	public String login() {
		log.debug("User login page.");
		return "view/user/login";
	}

	@PostMapping(path = "login/submit")
	public String loginSubmit() {
		log.debug("User login submit page.");
		return "redirect:/";
	}

}
