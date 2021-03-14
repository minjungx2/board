package org.minjung.board;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import java.rmi.StubNotFoundException;

import org.junit.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import lombok.extern.log4j.Log4j;

@Log4j
public class BoardControllerTests extends AbstractControllerTests {

	@Test
	public void listGetTest() throws Exception {
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/board/list")
				).andDo(print());
	}
	
	@Test
	public void readGetTest() throws Exception {
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/board/read")
				.param("bno", "4086")
				).andDo(print());
	}
	
	@Test
	public void registerGetTest() throws Exception {
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/board/register")
				).andDo(print());
	}
	
	@Test
	public void registerPostTest() throws Exception{
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/board/register")
				).andDo(print());
	}
	
	@Test
	public void modifyGetTest() throws Exception {
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders.get("/board/modify")
				.param("bno", "4086")
				).andDo(print());
	}
	
	@Test
	public void removePostTest() throws Exception{
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/board/remove")
				).andDo(print());
	}
	
	@Test
	public void modifyPostTest() throws Exception{
		log.info(mockMvc);
		
		mockMvc.perform(
				MockMvcRequestBuilders.post("/board/modify")
				).andDo(print());
	}
}
