package com.exam.controller;

import com.exam.entity.exam.Quiz;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/quiz")
@CrossOrigin("*")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //add quiz service
    @PostMapping("/")
    public ResponseEntity<Quiz> addQuiz(@RequestBody Quiz quiz){
        Quiz quiz1=this.quizService.addQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }
    //update quiz service
    @PutMapping("/")
    public ResponseEntity<Quiz> updateQuiz(@RequestBody Quiz quiz){
        Quiz quiz1=this.quizService.updateQuiz(quiz);
        return ResponseEntity.ok(quiz1);
    }
    //Get all Quizzes
    @GetMapping("/")
    public ResponseEntity<Set<Quiz>> getQuizzes(){
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }
    //Get Quiz By id
    @GetMapping("/{qId}")
    public ResponseEntity<Quiz> getQuizById(@PathVariable("qId") Long qId){
        return ResponseEntity.ok(this.quizService.getQuiz(qId));
    }
    //Delete Quiz by id
    @DeleteMapping("/{qId}")
    public ResponseEntity<String> deleteQuizById(@PathVariable("qId") Long qId){
        this.quizService.deleteQuiz(qId);
        return ResponseEntity.ok("Quiz is deleted successfully");
    }
}
