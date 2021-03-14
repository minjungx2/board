package org.minjung.time.service;

import org.minjung.time.mapper.TimeMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
@RequiredArgsConstructor
public class TimeServiceImpl implements TimeService {

	private final TimeMapper mapper;
	
	@Override
	public String getTime() {
		
		return mapper.getTime();
	}

}
