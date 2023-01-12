package com.sunil.myportal.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.sunil.myportal.model.Question;
import com.sunil.myportal.model.Quiz;

@Service
public interface QuestionService {
	
	public Question addQuestion(Question question);

	public Question updateQuestion(Question question);

	public Set<Question> getQuestions();

	public Question getQuestion(Long questionId);

	public void deleteQuestion(Long questionId);

	public Set<Question> getQuestionsOfQuiz(Quiz quiz);
}
