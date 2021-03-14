package org.minjung.board.service;

import java.util.List;
import java.util.stream.Collectors;

import org.minjung.board.domain.Board;
import org.minjung.board.dto.BoardDTO;
import org.minjung.board.mapper.BoardMapper;
import org.minjung.common.dto.PageDTO;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper mapper;

	@Override
	public List<BoardDTO> getList(PageDTO pageDTO) {
		return mapper.getList(pageDTO.getSkip(),
				pageDTO.getPerSheet(),
				pageDTO.getArr(),
				pageDTO.getKeyword())
				.stream().map(board -> toDTO(board)).collect(Collectors.toList());
	}

	@Override
	public int getTotalCount(PageDTO pageDTO) {
		return mapper.getTotalCount(pageDTO.getArr(), pageDTO.getKeyword());
	}

	@Override
	public BoardDTO readOne(Integer bno) {
		return toDTO(mapper.getOne(bno));
	}

	@Override
	public void insertBoard(BoardDTO boardDTO) {
		Board board = toDomain(boardDTO);	
		mapper.insert(board);
	}

	@Override
	public void deleteBoard(Integer bno) {
		mapper.delete(bno);
		
	}

	@Override
	public void updateBoard(BoardDTO boardDTO) {
		Board board = toDomain(boardDTO);
		mapper.update(board);
		
	}

}