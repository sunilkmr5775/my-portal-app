package com.sunil.myportal.service.impl;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sunil.myportal.model.Menu;
import com.sunil.myportal.repository.MenuRepository;
import com.sunil.myportal.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

	@Autowired
	private MenuRepository menuRepository;

	@Override
	public Menu addMenu(Menu menu) {
		
		menu.setCreatedDate(LocalDateTime.now());
		menu.setModifiedDate(null);
		return this.menuRepository.save(menu);
	}

//	@Override
//	public Quiz updateQuiz(Quiz quiz) {
//		return this.quizRepository.save(quiz);
//	}
//
	@Override
	public Set<Menu> getMenus() {
		return new LinkedHashSet<>(this.menuRepository.findAll());
	}
//
//	@Override
//	public Quiz getQuiz(Long quizId) {
//		Quiz quiz = this.quizRepository.findById(quizId).get();
//		return quiz;
//	}
//
//	@Override
//	public void deleteQuiz(Long quizId) {
//		this.quizRepository.deleteById(quizId);
//
//	}
//
//	@Override
//	public List<Quiz> getQuizzesOfCategory(Category category) {
//		// TODO Auto-generated method stub
//		return this.quizRepository.findByCategory(category);
//	}
//
//	
//	
////	Get Active Quizzes
//	@Override
//	public List<Quiz> getActiveQuizzes() {
//
//		return this.quizRepository.findByActive(StatusConstant.STATUS_ACTIVE1);
//	}
//
//	@Override
//	public List<Quiz> getActiveQuizzesOfCategory(Category category) {
//		// TODO Auto-generated method stub
//		return this.quizRepository.findByCategoryAndActive(category, StatusConstant.STATUS_ACTIVE1);
//	}
	
}
