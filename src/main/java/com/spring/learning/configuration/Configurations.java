package com.spring.learning.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class Configurations {

	// @Bean
	// Uncomment above line to create a basic data source (Without connection
	// pooling)
	public DataSource getMySqlDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/JDBC_JPA");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}

	@Bean
	public DataSource getMySqlPoolableDataSource() {
		BasicDataSource dataSource = new BasicDataSource();

		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/JDBC_JPA");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		dataSource.setDefaultAutoCommit(true);
		dataSource.setInitialSize(10);
		dataSource.setMaxIdle(2);
		dataSource.setMaxTotal(20);
		dataSource.setDefaultReadOnly(false);
		dataSource.setDefaultCatalog("JDBC_JPA");

		return dataSource;
	}

	@Bean
	@Autowired
	public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean
	@Autowired
	public PlatformTransactionManager getPlatformTransactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}
