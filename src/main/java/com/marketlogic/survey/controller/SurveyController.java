package com.marketlogic.survey.controller;

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

import com.marketlogic.survey.model.Questions;
import com.marketlogic.survey.service.SurveyService;

@RestController
@RequestMapping("/survey")
public class SurveyController {

	@Autowired
	private SurveyService surveyService;
	
	@PostMapping("/add") 
	public Questions createQueston(@RequestBody Questions questions) {
			return (Questions) surveyService.save(questions);
	}
	@PutMapping("/update/{id}") 
	public Questions updateQueston(@PathVariable(value = "id") Long questionId,@RequestBody Questions questions) {
			return (Questions) surveyService.update(questions,questionId);
			
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteQuestion(@PathVariable(value = "id") Long questionId,
			@RequestBody Questions questions) {
		boolean result = surveyService.delete(questionId);
		if (result) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
}
