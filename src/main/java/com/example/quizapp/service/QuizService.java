package com.example.quizapp.service;

import com.example.quizapp.dao.QuestionDao;
import com.example.quizapp.dao.QuizDao;
import com.example.quizapp.model.Question;
import com.example.quizapp.model.QuestionWrapper;
import com.example.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

      Optional<Quiz> quiz=  quizDao.findById(id);
      List<Question> questionFromDb= quiz.get().getQuestion();
      List<QuestionWrapper> questionForUser= new ArrayList<>();
      for(Question q : questionFromDb){
          QuestionWrapper qw= new QuestionWrapper(q.getId(),q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
                  questionForUser.add(qw);
      }

      return new ResponseEntity<>(questionForUser, HttpStatus.OK);
    }
}
