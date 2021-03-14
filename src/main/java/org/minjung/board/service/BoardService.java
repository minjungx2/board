package org.minjung.board.service;

import java.util.List;

import org.minjung.board.domain.Board;
import org.minjung.board.dto.BoardDTO;
import org.minjung.common.dto.PageDTO;

public interface BoardService {
	
	List<BoardDTO> getList(PageDTO pageDTO);
	
	int getTotalCount(PageDTO pageDTO);
	
	BoardDTO readOne(Integer bno);
	
	void insertBoard(BoardDTO boardDTO);
	
	void deleteBoard(Integer bno);
	
	void updateBoard(BoardDTO boardDTO);
	
	default BoardDTO toDTO(Board board){
		BoardDTO dto = new BoardDTO();
		dto.setBno(board.getBno());
		dto.setTitle(board.getTitle());
		dto.setContent(board.getContent());
		dto.setWriter(board.getWriter());
		dto.setRegdate(board.getRegdate());
		dto.setUpdatedate(board.getUpdatedate());
		return dto;
	};
	
	default Board toDomain(BoardDTO dto) {
		return Board.builder()
		.bno(dto.getBno())
		.title(dto.getTitle())
		.content(dto.getContent())
		.writer(dto.getWriter())
		.regdate(dto.getRegdate())
		.updatedate(dto.getUpdatedate()).build();
	}

}