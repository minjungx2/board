package org.minjung.board.domain;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Board {
	
	private Integer bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate, updatedate;

}
