package com.sunil.myportal.service.impl;

import com.sunil.myportal.constant.StatusConstant;
import com.sunil.myportal.dto.DatabaseResponse;
import com.sunil.myportal.repository.DatabaseRepository;
import com.sunil.myportal.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Service
public class DatabaseServiceImpl implements DatabaseService {

	@Autowired
	private DatabaseRepository databaseRepository;

	@Autowired
	private Environment environment;

	private  DataSource dataSource;

	public DatabaseServiceImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public DatabaseResponse getDbDetails() throws SQLException {
		URI uri;
		Connection con = null;
		String[] activeProfiles = environment.getActiveProfiles();

		DatabaseResponse databaseResponse = null;
		try {
			databaseResponse = new DatabaseResponse();
			DatabaseMetaData metaData = this.dataSource.getConnection().getMetaData();
			String cleanURI = metaData.getURL().substring(5);
			uri = URI.create(cleanURI);
			String profile = null;

			for (String profileName : activeProfiles) {
				profile = profileName.toUpperCase();
				databaseResponse.setProfile(profile);
			}
//			if(profile!=null && "stage".equalsIgnoreCase(profile))   // for mongo db
			databaseResponse.setDbVersion(metaData.getDatabaseProductVersion());
			databaseResponse.setUserName(metaData.getUserName().substring(0,metaData.getUserName().lastIndexOf("@")));
			databaseResponse.setDatabasePlatform(metaData.getDatabaseProductName());
			databaseResponse.setHostName(uri.getHost());
			databaseResponse.setDbName(uri.getPath().substring(1));
			databaseResponse.setPortNo(String.valueOf(uri.getPort()));
			databaseResponse.setPassword(environment.getProperty("spring.datasource.password"));

			databaseResponse.setStatus(StatusConstant.STATUS_ACTIVE);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null && !con.isClosed()) {
				try {
					con.close();
				} catch (SQLException e) {
					System.err.println("Connection close error." + e);
				}
			}
		}


		return databaseResponse;
	}


	protected void PropertiesUtility() throws IOException {
		Properties properties = new Properties();
		InputStream inputStream =
				getClass().getClassLoader().getResourceAsStream("application.properties");
		properties.load(inputStream);
//		properties.getProperty("")

	}


}
