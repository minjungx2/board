package org.minjung.board.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import lombok.extern.log4j.Log4j;

@Configuration
@MapperScan(basePackages = "org.minjung.board.mapper")
@ComponentScan(basePackages = "org.minjung.board.service")
@Log4j
public class BoardConfig {

}
