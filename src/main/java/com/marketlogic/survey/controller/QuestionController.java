package com.marketlogic.survey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marketlogic.survey.exception.ResourceNotFoundException;
import com.marketlogic.survey.model.Questions;
import com.marketlogic.survey.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService surveyService;
	
	@GetMapping("/all")
	@Operation(summary = "Get All Questions")
	public List<Questions> getAllQuestion(){
		return surveyService.getAllQuestions();
	}
	@GetMapping("/{id}")
	@Operation(summary = "Get Question By Id")
	public ResponseEntity<Questions> getQuestionById(@PathVariable(value = "id") Long questionId)throws ResourceNotFoundException{
		Questions question= surveyService.getQuestionById(questionId);
		 return ResponseEntity.ok().body(question);
	}
	@PostMapping("/add") 
	@Operation(summary = "Save the Question")
	public ResponseEntity<Questions> createQueston(@RequestBody Questions questions) {
		Questions _question= surveyService.save(questions);
		return new ResponseEntity<>(_question, HttpStatus.CREATED);
	}
	@PutMapping("/{id}") 
	@Operation(summary = "Update the Question By Id")
	public ResponseEntity<Questions> updateQueston(@PathVariable(value = "id") Long questionId,@RequestBody Questions questions) throws ResourceNotFoundException{
		final Questions updatedQuestion=surveyService.update(questions,questionId);
		return ResponseEntity.ok(updatedQuestion);
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Delete the Question By Id")
	public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable(value = "id") Long questionId) throws ResourceNotFoundException{
		boolean result = surveyService.delete(questionId);
		if (result) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
