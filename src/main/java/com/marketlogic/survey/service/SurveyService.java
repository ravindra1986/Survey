package com.marketlogic.survey.service;

import com.marketlogic.survey.model.Questions;

public interface SurveyService {
	public Questions save(Questions t);

	public Questions update(Questions t,Long questionId);

	public boolean delete(long id);
	
}
