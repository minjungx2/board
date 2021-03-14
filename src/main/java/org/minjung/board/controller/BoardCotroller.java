package org.minjung.board.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import javax.validation.Valid;

import org.minjung.board.dto.BoardDTO;
import org.minjung.board.service.BoardService;
import org.minjung.common.dto.PageDTO;
import org.minjung.common.dto.PageMaker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.cj.xdevapi.Result;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j
public class BoardCotroller {
	
	private final BoardService service;
	
	@GetMapping("/list")
	public void listGet(PageDTO pageDTO,Model model) {
		model.addAttribute("list", service.getList(pageDTO));
		model.addAttribute("pageMaker", new PageMaker(pageDTO, service.getTotalCount(pageDTO)));
	}
	
	@GetMapping({"/read","/modify"})
	public void readGet(PageDTO pageDTO, Integer bno,  Model model) {
		model.addAttribute("board", service.readOne(bno));
	}
	
	@GetMapping("/register")
	public void registerGet() {
		
	}
	
	@PostMapping(value = "/register", produces = {"text/plain"})
	@ResponseBody
	public ResponseEntity<String> registerPost(@RequestBody BoardDTO boardDTO, BindingResult result) {
		
		log.info(boardDTO);
		
		if(result.hasErrors()) {
			log.info(result.getAllErrors());
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		service.insertBoard(boardDTO);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@PostMapping("/remove")
	@ResponseBody
	public ResponseEntity<String> removePost(Integer bno) {
		log.info("bno"+bno);
		service.deleteBoard(bno);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	@PostMapping("/modify")
	@ResponseBody
	public ResponseEntity<String> modifyPost(@RequestBody BoardDTO boardDTO, BindingResult result){
		log.info(boardDTO);
		
		if(result.hasErrors()) {
			log.info(result.getAllErrors());
			return new ResponseEntity<String>("fail", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		service.updateBoard(boardDTO);
		return new ResponseEntity<String>("success", HttpStatus.OK);
	}

}
