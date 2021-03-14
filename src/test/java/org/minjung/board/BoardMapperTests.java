package org.minjung.board;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.minjung.board.config.BoardConfig;
import org.minjung.board.domain.Board;
import org.minjung.board.mapper.BoardMapper;
import org.minjung.common.config.CommonConfig;
import org.minjung.common.dto.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, BoardConfig.class})
@Log4j
public class BoardMapperTests {
	
	@Autowired
	BoardMapper mapper;
	
	@Test
	public void getListTest() {
		mapper.getList(10,10,null,"20")
				.forEach(list -> log.info(list));
	}
	
	@Test
	public void getTotalCountTest() {
        PageDTO pageDTO = new PageDTO();
		pageDTO.setType("t");
		pageDTO.setKeyword("747");
		log.info(pageDTO);
		int count = mapper.getTotalCount(pageDTO.getArr(), pageDTO.getKeyword());
		log.info(count);
	}
	
	@Test
	public void getOneTest() {
		Integer bno = 30;
		log.info(mapper.getOne(bno));
	}
	
	@Test
	public void insertTest() {
		Board board = Board.builder()
				.title("등록 테스트")
				.content("테스트")
				.writer("민정")
				.build();
		mapper.insert(board);
	}
	
	@Test
	public void deleteTest() {
		mapper.delete(4095);
	}
	
	@Test
	public void updateTest() {
		Board board = Board.builder()
				.bno(4085)
				.title("수정 테스트")
				.content("테스트 수정")
				.build();
		mapper.update(board);
	}

}