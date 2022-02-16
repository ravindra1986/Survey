package com.marketlogic.survey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketlogic.survey.exception.ResourceNotFoundException;
import com.marketlogic.survey.model.Questions;
import com.marketlogic.survey.repository.QuestionsRepository;
import com.marketlogic.survey.service.QuestionService;
@Service
public class QuestonServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionsRepository questionsRepository;

	@Override
	public Questions save(Questions questions) {
		return questionsRepository.save(questions);
		
	}

	@Override
	public Questions update(Questions t,Long questionId) throws ResourceNotFoundException{
		Questions ques=questionsRepository.findById(questionId).orElseThrow(() -> new ResourceNotFoundException("Queston not found for this id :: " + questionId));
		if(null != ques && null !=ques.getAnswers() && !ques.getAnswers().isEmpty()) {
			ques.setQuestion(t.getQuestion());
			ques.setAnswers(t.getAnswers());
		}
		return ques;
	}

	@Override
	public boolean delete(long id) throws ResourceNotFoundException{
		try {
		questionsRepository.deleteById(id);
		return true;
		}catch(Exception e) {
			throw new  ResourceNotFoundException("Question Not Found for this id::" + id);
		}
		
	}

	@Override
	public List<Questions> getAllQuestions() {
		return questionsRepository.findAll();
	}

	@Override
	public Questions getQuestionById(long id) throws ResourceNotFoundException {
		return questionsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Queston not found for this id :: " + id));
	}

}
