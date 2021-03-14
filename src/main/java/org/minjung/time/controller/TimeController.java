package org.minjung.time.controller;

import org.minjung.time.service.TimeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/time")
@Log4j
@RequiredArgsConstructor
public class TimeController {
	
	private final TimeService service;
	
	@GetMapping("/now")
	public void timeGet(Model model) {
		String time = service.getTime();
		model.addAttribute("time", time);
	}

}
