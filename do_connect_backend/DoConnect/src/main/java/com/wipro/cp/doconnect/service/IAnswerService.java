/*
* @Author: Devashish Ashok Pathrabe
* Modified Date: 26-08-2022
* Description: Answer Service Interface
*/

package com.wipro.cp.doconnect.service;

import java.util.List;

import com.wipro.cp.doconnect.dto.AnswerRequestDTO;
import com.wipro.cp.doconnect.dto.AnswerResponseDTO;
import com.wipro.cp.doconnect.dto.AnswerUpdateDTO;
import com.wipro.cp.doconnect.dto.StatusDTO;

public interface IAnswerService {
	
	public StatusDTO<List<AnswerResponseDTO>> getAllAnswers(String answerStatus);
	public StatusDTO<List<AnswerResponseDTO>> getAllAnswersForQuestionId(Long questionId, String answerStatus);
	public StatusDTO<AnswerResponseDTO> createAnswerForQuestionId(Long questionId, AnswerRequestDTO answerRequestDTO, String postedBy);
	public StatusDTO<AnswerResponseDTO> updateAnswerForQuestionId(Long questionId, Long answerId, AnswerUpdateDTO answerUpdateDTO, String approvedBy);
	public StatusDTO<Boolean> deleteAnswerForQuestionById(Long questionId, Long answerId);

}
