package com.jinqiu.app.security;

import com.jinqiu.app.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 864000000; // 10 days, in MS
	public static final String TOKEN_PREFIX_STRING = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String LOG_IN_URL = "/users/login";
	//public static final String TOKEN_SECRET = "default"; //some random alpha-numeric string
	public static final int USER_ID_LENGTH = 16;
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext
													.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}
