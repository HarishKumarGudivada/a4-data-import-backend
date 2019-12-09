package com.a4data.etl.util;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import com.mongodb.MongoClientURI;

@Configuration
public class MultipleDBConfig {
	
	  @Autowired
	  private Environment env;
	  
	  @Autowired
	  private ApplicationContext context;

	
	@Bean(name = "mysqlDb")
	@ConfigurationProperties(prefix = "spring.ds-mysql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mysqlJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("mysqlDb") DataSource dsMySQL) {
		return new JdbcTemplate(dsMySQL);
	}
	
	@Bean(name = "postgresDb")
	@ConfigurationProperties(prefix = "spring.ds-post")
	public DataSource postgresDataSource() {
		return  DataSourceBuilder.create().build();
	}

	@Bean(name = "postgresJdbcTemplate")
	public JdbcTemplate postgresJdbcTemplate(@Qualifier("postgresDb") DataSource dsPostgres) {
		return new JdbcTemplate(dsPostgres);
	}
	
	
	@Bean(name = "databases")
	public Map<String,JdbcTemplate> loadDatabases(){
		Map<String, JdbcTemplate> map = new HashMap<>();
		map.put("postgresdb", (JdbcTemplate) context.getBean("postgresJdbcTemplate"));
		map.put("mysqldb", (JdbcTemplate) context.getBean("mysqlJdbcTemplate"));
		return map;
	}
	
	/*
	  @Bean
	    public MongoDbFactory mongoDbFactory() {
	        return new SimpleMongoDbFactory(new MongoClientURI(env.getProperty("spring.data.mongodb.uri")));
	    }

		@Bean
		public MongoTemplate mongoTemplate() {
			MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());

			return mongoTemplate;

	    }
	    */
}