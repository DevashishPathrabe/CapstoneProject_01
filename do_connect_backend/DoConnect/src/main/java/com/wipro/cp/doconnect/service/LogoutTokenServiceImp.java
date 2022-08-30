/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Logout Token Service
*/

package com.wipro.cp.doconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.cp.doconnect.entity.LogoutToken;
import com.wipro.cp.doconnect.repository.LogoutTokenRepository;

@Service
public class LogoutTokenServiceImp implements ILogoutTokenService {
	
	@Autowired
	LogoutTokenRepository logoutTokenRepository;
	
	/*
	* @Author: Devashish Ashok Pathrabe
	* Modified Date: 26-08-2022
	* Description: Check if a particular token exist
	* Params: Token string
	* Return Type: Boolean
	*/
	@Override
	public boolean checkIfTokenExists(String token) {
		return logoutTokenRepository.existsByToken(token);
	}
	
	/*
	* @Author: Devashish Ashok Pathrabe
	* Modified Date: 26-08-2022
	* Description: Create token
	* Params: LogoutToken 
	* Return Type: LogoutToken
	*/
	@Override
	public LogoutToken createToken(LogoutToken logoutToken) {
		return logoutTokenRepository.save(logoutToken);
	}
	
	/*
	* @Author: Devashish Ashok Pathrabe
	* Modified Date: 26-08-2022
	* Description: Create tokens
	* Params: LogoutToken List
	* Return Type: LogoutToken List
	*/
	@Override
	public List<LogoutToken> createTokens(List<LogoutToken> logoutTokenList) {
		return logoutTokenRepository.saveAllAndFlush(logoutTokenList);
	}
	
	/*
	* @Author: Devashish Ashok Pathrabe
	* Modified Date: 26-08-2022
	* Description: Get all tokens
	* Params: None
	* Return Type: LogoutToken List
	*/
	@Override
	public List<LogoutToken> getAllTokens() {
		return logoutTokenRepository.findAll();
	}

	/*
	* @Author: Devashish Ashok Pathrabe
	* Modified Date: 26-08-2022
	* Description: Delete all tokens
	* Params: None
	* Return Type: None
	*/
	@Override
	public void deleteAllTokens() {
		logoutTokenRepository.deleteAllInBatch();
	}

}
