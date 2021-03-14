package org.minjung.board;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.minjung.board.config.BoardConfig;
import org.minjung.board.dto.BoardDTO;
import org.minjung.board.service.BoardService;
import org.minjung.common.config.CommonConfig;
import org.minjung.common.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, BoardConfig.class})
@Log4j
public class BoardServiceTests {
	
	@Autowired
	BoardService service;
	
	@Test
	public void testGetList() {
		service.getList(PageDTO.builder().page(1).type("t").keyword("9").build())
		.forEach(dto -> log.info(dto));
	}
	
	@Test
	public void testGetTotalCount() {
		PageDTO pageDTO = new PageDTO();
		pageDTO.setType("t");
		pageDTO.setKeyword("20");
		log.info(pageDTO);
		int count = service.getTotalCount(pageDTO);
		log.info(count);
	}
	
	@Test
	public void testReadOne() {
		Integer bno = 15;
		log.info(service.readOne(bno));
	}
	
	@Test
	public void insertBoardTest() {
		BoardDTO dto = new BoardDTO();
		dto.setTitle("test");
		dto.setContent("test");
		dto.setWriter("test");
		service.insertBoard(dto);
	}
	
	@Test
	public void deleteTest() {
		service.deleteBoard(4098);
	}
	
	@Test
	public void updateBoardTest() {
		BoardDTO dto = new BoardDTO();
		dto.setBno(3060);
		dto.setTitle("updatetest");
		dto.setContent("test");
		service.updateBoard(dto);
	}

}