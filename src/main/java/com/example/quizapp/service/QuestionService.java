package com.example.quizapp.service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class QuestionService {


    @Autowired
    QuestionDao questionDao;
    public List<Question> getAllQuestions() {
        return  questionDao.findAll();
    }

    public List<Question> getQuestionsByCategory(String category){
        return questionDao.findByCategory(category);
    }


    public ResponseEntity<String> addQuestion(Question question) {
       questionDao.save(question);
       return new ResponseEntity<>("success", HttpStatus.CREATED);
    }



}

