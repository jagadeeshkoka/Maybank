package com.maybank.config;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;

import com.zaxxer.hikari.HikariDataSource;




@Configuration
public class PersistenceConfig {
	
	

	@Value("${maybank.connection.db.dbUserId}")
	private String dbUserId;
	@Value("${maybank.connection.db.dbPassword}")
	private String dbPassword;
	@Value("${maybank.connection.db.dbUrl}")
	private String dbUrl;
	@Value("${maybank.connection.db.dbDriverClass}")
	private String dbDriverClass;
	
	
	
	@Value("${connection.pool.name}")
	private String poolName;
	
	@Value("${connection.pool.maximum.pool.size}")
	private int maxConnection;
	
	
	@Value("${connection.pool.minimum.pool.size}")
	private int minConeection;
	
	@Value("${connection.pool.test.query}")
	private String testQuery;
	
	@Value("${connection.pool.idle.time}")
	private long idleTime;
	
	@Value("${connection.pool.connection.timeout}")
	private long conTimeOut;
	
	
	
	@Bean
	public HikariDataSource dataSourcePool() throws Exception
	{
		HikariDataSource datasource = new HikariDataSource();
		datasource.setUsername(dbUserId);
		datasource.setPassword(dbPassword);
		datasource.setJdbcUrl(dbUrl);
		datasource.setDriverClassName(dbDriverClass);
		datasource.setPoolName(poolName);
		datasource.setMaximumPoolSize(maxConnection);
		datasource.setMinimumIdle(minConeection);
		datasource.setConnectionTestQuery(testQuery);
		datasource.setConnectionTimeout(conTimeOut);
		datasource.setIdleTimeout(idleTime);
			return datasource;
		
    }
	
	@Bean
	public HibernateJpaDialect hibernateJpaDialect() {
		return new HibernateJpaDialect();
	}
	
	
}
