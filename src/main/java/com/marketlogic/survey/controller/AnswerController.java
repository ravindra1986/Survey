package com.marketlogic.survey.controller;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketlogic.survey.exception.ResourceNotFoundException;
import com.marketlogic.survey.model.Answers;
import com.marketlogic.survey.model.Questions;
import com.marketlogic.survey.service.AnswerService;
import com.marketlogic.survey.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("/add") 
	@Operation(summary = "Save the Answers")
	public ResponseEntity<Answers> createAnswer(@RequestBody Answers answers) {
		Answers _answer= answerService.save(answers);
		return new ResponseEntity<>(_answer, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}") 
	@Operation(summary = "Update the Answer By Id")
	public ResponseEntity<Answers> updateAnswer(@PathVariable(value = "id") Long answerId,@RequestBody Answers answers) throws ResourceNotFoundException{
		final Answers updatedAnswers=answerService.update(answers,answerId);
		return ResponseEntity.ok(updatedAnswers);
	}
	@PutMapping("/{id}/question/{id}") 
	@Operation(summary = "Update the answer By Question Id")
	public ResponseEntity<Questions> updateAnswer(@PathVariable(value = "id") Long answerId,@RequestBody Answers answers,@PathVariable(value = "id") Long quesId) throws ResourceNotFoundException{
		final Questions updatedAnswers=answerService.updateAnsByQuesId(answers,answerId,quesId);
		return ResponseEntity.ok(updatedAnswers);
	}
	@DeleteMapping("/{id}/question/{id}")
	@Operation(summary = "Delete the Answer By Question Id")
	public ResponseEntity<Questions> deleteAnswerByQuesId(@PathVariable(value = "id") Long questionId,@PathVariable(value = "id") Long ansId) throws ResourceNotFoundException{
		Questions question=questionService.getQuestionById(questionId);
		if(null !=question) {
			List<Answers> listAns=question.getAnswers();
			ListIterator<Answers> itr=listAns.listIterator();
			while(itr.hasNext()) {
				if(itr.next().getId()==ansId) {
					itr.remove();
				}
			}
			
			question.setAnswers(listAns);
		}
		Questions que= questionService.save(question);
		return ResponseEntity.ok(que);
	}
	
}
