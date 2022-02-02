package com.marketlogic.survey.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Answers")

public class Answers implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 57999070891052986L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "answer")
	private String answer;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "question_id", nullable = true) 
	@JsonIgnoreProperties("questions")
	//@JsonInclude(Include.NON_NULL)
    private Questions questions;
	
	public Answers() {
		
	}
	
	public Answers(String answer, Questions questions) {
		super();
		this.answer = answer;
		this.questions = questions;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Questions getQuestions() {
		return questions;
	}

	public void setQuestions(Questions questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Answers [id=" + id + ", answer=" + answer + ", questions=" + questions + "]";
	}
	
	
}
