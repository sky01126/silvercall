package com.silvercall.web.view.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@Controller
public class MainViewController {

	/**
	 * Reqeust Mapping이 정의 되지 않은 페이지 처리.
	 *
	 * @param model model attributes.
	 * @return view page.
	 */
	@RequestMapping(path = { "*" })
	public String main(Model model) {
		log.debug("Access the main page.");
		model.addAttribute("message", "Access the main page.");
		return "view/main";
	}

}
