package com.wipro.cp.doconnect.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.wipro.cp.doconnect.component.JwtTokenUtilityComponent;
import com.wipro.cp.doconnect.entity.LogoutToken;
import com.wipro.cp.doconnect.service.LogoutTokenServiceImp;

import io.jsonwebtoken.ExpiredJwtException;

@Configuration
@EnableScheduling
public class LogoutTokenSchedulerConfig {
	
	@Autowired
	private LogoutTokenServiceImp logoutTokenServiceImp;
	
	@Autowired
	private JwtTokenUtilityComponent jwtTokenUtilityComponent;
	
	@Scheduled(fixedDelay = 15 * 60 * 1000) // 15 Minutes
	public void deleteExpiredJwtTokens() {
		System.out.println("deleteExpiredJwtTokens scheduled job started at " + System.currentTimeMillis());
	    List<LogoutToken> logoutTokenList = logoutTokenServiceImp.getAllTokens();
	    logoutTokenServiceImp.deleteAllTokens();
	    List<LogoutToken> validLogoutTokenList = new ArrayList<>();
	    for (LogoutToken logoutToken : logoutTokenList) {
	    	try {
	    		if (jwtTokenUtilityComponent.isTokenExpired(logoutToken.getToken())) {
	    			System.out.println("JWT Token " + logoutToken.getToken() + " removed from logout_token table.");
	    		} else {
	    			validLogoutTokenList.add(logoutToken);
	    		}
	    	} catch (ExpiredJwtException e) {
	    		System.out.println("Exception in deleteExpiredJwtTokens - " + e.toString());
	    	}
	    }
	    logoutTokenServiceImp.createTokens(validLogoutTokenList);
	    System.out.println("deleteExpiredJwtTokens scheduled job finished at " + System.currentTimeMillis());
	}

}
