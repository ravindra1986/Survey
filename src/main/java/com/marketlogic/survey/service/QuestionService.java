package com.marketlogic.survey.service;

import java.util.List;

import com.marketlogic.survey.exception.ResourceNotFoundException;
import com.marketlogic.survey.model.Questions;

public interface QuestionService {
	public Questions save(Questions t);

	public Questions update(Questions t,Long questionId)throws ResourceNotFoundException;

	public boolean delete(long id) throws ResourceNotFoundException;
	
	public List<Questions> getAllQuestions();
	
	public Questions getQuestionById(long id)throws ResourceNotFoundException;;
	
}
