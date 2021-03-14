package org.minjung.common.dto;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PageDTO {
	
	@Builder.Default
	private int page = 1;
	@Builder.Default
	private int perSheet = 10;
	
	private String type; //t, tc, tcw -> 배열 -> 루프
	private String keyword;
	
	public int getSkip() {
		return (page-1) * perSheet;
	}
	
	//select 박스
	public String[] getArr() {
		if(keyword == null || keyword.trim().length() == 0) {
			return null;
		}
		if(type == null) {
			return null;
		}
		return type.split(""); //쪼개서 -> 배열
	}
	
	//체크박스
	public Map<String, String> getMap(){
		Map<String, String> map = new HashMap<>();
		map.put("t", "10");
		map.put("c", "10");
		map.put("w", "10");
		return map;
	}

}
