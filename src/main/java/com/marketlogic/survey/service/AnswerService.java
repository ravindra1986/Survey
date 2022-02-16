package com.marketlogic.survey.service;

import java.util.List;

import com.marketlogic.survey.exception.ResourceNotFoundException;
import com.marketlogic.survey.model.Answers;
import com.marketlogic.survey.model.Questions;

public interface AnswerService {
	public Answers save(Answers t);

	public Answers update(Answers t,Long questionId)throws ResourceNotFoundException;

	public boolean delete(long id) throws ResourceNotFoundException;
	
	public List<Answers> getAllAnswers();
	
	public Answers getAnswersById(long id)throws ResourceNotFoundException;
	
	public Questions updateAnsByQuesId(Answers t,Long questionId, Long ansId)throws ResourceNotFoundException;
}
