package org.minjung.common;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.minjung.common.config.CommonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CommonConfig.class)
@Log4j
public class ConfigLoadTests {
	
	@Test
	public void testConnection() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/minjung?serverTimezone=UTC";
		String username = "minjung";
		String password = "minjung";
		
		Connection con = DriverManager.getConnection(url, username, password);
		log.info(con);
		con.close();
	}
	
	@Autowired
	DataSource ds;
	
	@Test
	public void dataSourceTest() {
		log.info("dataSourceTest............");
		assertNotNull(ds);
	}
	
	@Autowired
	private SqlSessionFactory sqlSessionFactory;

    @Test 
	public void testSesstion() {
		SqlSession session = sqlSessionFactory.openSession();
		log.info(session);
		session.close();
	}

}