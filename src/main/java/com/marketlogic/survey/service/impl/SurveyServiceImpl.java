package com.marketlogic.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketlogic.survey.model.Questions;
import com.marketlogic.survey.repository.QuestionsRepository;
import com.marketlogic.survey.service.SurveyService;
@Service
public class SurveyServiceImpl implements SurveyService{
	
	@Autowired
	private QuestionsRepository questionsRepository;

	@Override
	public Questions save(Questions questions) {
		return questionsRepository.save(questions);
		
	}

	@Override
	public Questions update(Questions t,Long questionId) {
		Questions ques=questionsRepository.findById(questionId).get();
		if(null != ques && null !=ques.getAnswers() && !ques.getAnswers().isEmpty()) {
			ques.setQuestion(t.getQuestion());
			ques.setAnswers(t.getAnswers());
		}
		return ques;
	}

	@Override
	public boolean delete(long id) {
		try {
		questionsRepository.deleteById(id);
		return true;
		}catch(Exception e) {
			return false;	
		}
		
	}

}
