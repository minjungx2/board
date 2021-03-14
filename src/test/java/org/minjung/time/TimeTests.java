package org.minjung.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.minjung.common.config.CommonConfig;
import org.minjung.time.config.TimeConfig;
import org.minjung.time.mapper.TimeMapper;
import org.minjung.time.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {CommonConfig.class, TimeConfig.class})
@Log4j
public class TimeTests {
	
	@Autowired
	TimeMapper mapper;
	
	@Autowired
	TimeService service;
	
	@Test
	public void testMapper() {
		log.info("timeMapperTest.........");
		String time = mapper.getTime();
		log.info(time);
	}
	
	@Test
	public void testService() {
		log.info("timeServiceTest..........");
		String time = service.getTime();
		log.info(time);
	} 

}