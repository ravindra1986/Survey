package com.marketlogic.survey.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="Questions")
public class Questions implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6712356533474496339L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	 
	@Column(name = "question")
	@JsonProperty("question")
	
	@NotEmpty(message="Question can't blank or empty")
	private String question; 
	
	
	@OneToMany(targetEntity=Answers.class,cascade = CascadeType.ALL, 
             fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "question_id", referencedColumnName = "id")
	@JsonIgnoreProperties("questions")
	@JsonProperty("answersList")
    private List<Answers> answersList;
	
	public Questions() {
		
	}

	public Questions(String question, List<Answers> answersList) {
		super();
		this.question = question;
		this.answersList = answersList;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Answers> getAnswers() {
		return answersList;
	}

	public void setAnswers(List<Answers> answersList) {
		this.answersList = answersList;
	}

	@Override
	public String toString() {
		return "Questions [id=" + id + ", question=" + question + ", answersList=" + answersList + "]";
	}
	
	
	
}
