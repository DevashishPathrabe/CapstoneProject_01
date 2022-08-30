/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: User Service Interface
*/

package com.wipro.cp.doconnect.service;

import java.util.List;

import com.wipro.cp.doconnect.dto.StatusDTO;
import com.wipro.cp.doconnect.dto.UserRegisterDTO;
import com.wipro.cp.doconnect.dto.UserResponseDTO;
import com.wipro.cp.doconnect.dto.UserUpdateDTO;

public interface IUserService {
	
	public StatusDTO<UserResponseDTO> createUser(UserRegisterDTO userRegisterDTO);
	public List<UserResponseDTO> getAllUsers();
	public StatusDTO<UserResponseDTO> getUserById(Long id);
	public StatusDTO<UserResponseDTO> updateUser(UserUpdateDTO userUpdateDTO, Long id);
	public boolean deleteUserById(Long id);
	public void deleteAllUsers();

}
