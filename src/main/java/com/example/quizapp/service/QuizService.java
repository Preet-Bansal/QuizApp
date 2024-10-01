package com.example.quizapp.service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.dao.QuizDao;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

   @Autowired
   QuizDao quizDao;

   @Autowired
   QuestionDao questiondao;


    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Question> question = questiondao.findRandomQuestionByCategory(category, numQ);
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(question);
        quizDao.save(quiz);


        return new ResponseEntity<>("Success", HttpStatus.CREATED);


    }
}
