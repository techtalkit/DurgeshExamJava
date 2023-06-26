package com.exam.controller;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;

    //add question api
    @PostMapping("/")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        Question quest1=this.questionService.addQuestion(question);
        return ResponseEntity.ok(quest1);
    }
    //update question api
    @PutMapping("/")
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question){
        Question quest1=this.questionService.updateQuestion(question);
        return ResponseEntity.ok(quest1);
    }
    //get all Questions
    @GetMapping("/")
    public ResponseEntity<Set<Question>> getQuestions(){
        return ResponseEntity.ok(this.questionService.getQuestions());
    }
    //get question by id
    @GetMapping("/{quesId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable("quesId") Long quesId){
        return ResponseEntity.ok(this.questionService.getQuestion(quesId));
    }
    //get questions by quiz id
    @GetMapping("/quiz/{qId}")
    public ResponseEntity<Set<Question>> getQuestionByQuiz(@PathVariable("qId") Long qId){
//        Quiz quiz=new Quiz();
//        quiz.setqId(qId);
//        Set<Question> questionOfQuiz=this.questionService.getQuestionsOfQuiz(quiz);
//        return ResponseEntity.ok(questionOfQuiz);
    }
}
