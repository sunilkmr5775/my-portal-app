package com.sunil.myportal.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.myportal.model.Question;
import com.sunil.myportal.model.Quiz;
import com.sunil.myportal.repository.QuestionRepository;
import com.sunil.myportal.service.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {


		@Autowired
		private QuestionRepository questionRepository;
		
		
		@Override
		public Question addQuestion(Question question) {
			return	this.questionRepository.save(question);
		}

		@Override
		public Question updateQuestion(Question question) {
			return this.questionRepository.save(question);
		}

		@Override
		public Set<Question> getQuestions() {
			return new LinkedHashSet<>(this.questionRepository.findAll());
		}

		@Override
		public Question getQuestion(Long questionId) {
			Question question = this.questionRepository.findById(questionId).get();
			return question;
		}
		
		@Override
		public Set<Question> getQuestionsOfQuiz(Quiz quiz) {
			// TODO Auto-generated method stub
			return this.questionRepository.findByQuiz(quiz);
		}

		@Override
		public void deleteQuestion(Long quesId) {
			Question question = new Question();
			question.setQuesId(quesId);
			this.questionRepository.delete(question);
			
		}

	
	
}
