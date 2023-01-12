package com.sunil.myportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sunil.myportal.model.Category;
import com.sunil.myportal.model.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {

	List<Quiz> findByCategory(Category category);

	List<Quiz> findByActive(Boolean status);
	
	List<Quiz> findByCategoryAndActive(Category category, Boolean status);
}
