package org.minjung.board.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardDTO {
	
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate, updatedate;

}
