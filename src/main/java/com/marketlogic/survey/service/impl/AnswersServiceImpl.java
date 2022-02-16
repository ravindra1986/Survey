package com.marketlogic.survey.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marketlogic.survey.exception.ResourceNotFoundException;
import com.marketlogic.survey.model.Answers;
import com.marketlogic.survey.model.Questions;
import com.marketlogic.survey.repository.AnswersRepository;
import com.marketlogic.survey.repository.QuestionsRepository;
import com.marketlogic.survey.service.AnswerService;
@Service
public class AnswersServiceImpl implements AnswerService{

	@Autowired
	private AnswersRepository answersRepository;
	
	@Autowired
	private QuestionsRepository questionsRepository;
	
	@Override
	public Answers save(Answers t) {
		return answersRepository.save(t);
	}

	@Override
	public Answers update(Answers t, Long answerId) throws ResourceNotFoundException {
		Answers answers=answersRepository.findById(answerId).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id :: " + answerId));
		if(answers !=null) {
			answers.setAnswer(t.getAnswer());
		}
		return answersRepository.save(answers);
	}

	@Override
	public boolean delete(long id) throws ResourceNotFoundException {
		try {
			answersRepository.deleteById(id);
			return true;
			}catch(Exception e) {
				throw new  ResourceNotFoundException("Answer Not Found for this id::" + id);
			}
	}

	@Override
	public List<Answers> getAllAnswers() {
		return answersRepository.findAll();
	}

	@Override
	public Answers getAnswersById(long id) throws ResourceNotFoundException {
		return answersRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Answer not found for this id :: " + id));
	}

	@Override
	public Questions updateAnsByQuesId(Answers t, Long questionId, Long ansId) throws ResourceNotFoundException {
		Answers ans=answersRepository.findAnswerByQuestionId(ansId);
		ans.getQuestions().getId();
		Questions questions=questionsRepository.getById(questionId);
		List<Answers> ansList=null;
		if(null !=questions) {
			ansList=questions.getAnswers();
			for(Answers ans1:ansList) {
				if(ans1.getId()==ansId) {
					ans1.setAnswer(t.getAnswer());
				}
			}
			questions.setAnswers(ansList);
		}
		return questionsRepository.save(questions);
	}

}
