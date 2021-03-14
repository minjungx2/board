package org.minjung.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.minjung.board.domain.Board;

public interface BoardMapper {
	
	List<Board> getList(@Param("skip") int skip,
			            @Param("count") int count,
			            @Param("arr") String[] arr,
			            @Param("keyword") String keyword);

	int getTotalCount(@Param("arr") String[] arr,
			          @Param("keyword") String keyword);
	
	@Select("select * from tbl_board2 where bno = #{bno}")
	Board getOne(Integer bno);
	
	void insert(Board board);
	
	void delete(Integer bno);
	
	void update(Board board);

}
