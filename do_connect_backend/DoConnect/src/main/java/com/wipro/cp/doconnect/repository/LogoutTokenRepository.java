/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Logout Token Repository
*/

package com.wipro.cp.doconnect.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.cp.doconnect.entity.LogoutToken;

@Repository
public interface LogoutTokenRepository extends JpaRepository<LogoutToken, Long> {
	
	Optional<LogoutToken> findByToken(String token);

	boolean existsByToken(String token);

}
