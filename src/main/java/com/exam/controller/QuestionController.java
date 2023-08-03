package com.exam.controller;

import com.exam.entity.exam.Question;
import com.exam.entity.exam.Quiz;
import com.exam.repo.QuestionRepository;
import com.exam.services.QuestionService;
import com.exam.services.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<List<Question>> getQuestionByQuiz(@PathVariable("qId") Long qId){
        Quiz quiz=this.quizService.getQuiz(qId);
        //We will get the number of questions of all the quizzes
        Set<Question> questions=quiz.getQuestions();
        //We need to send those only questions that have in the quiz
        List list=new ArrayList(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }
        //shuffle will change the order of the questions
        Collections.shuffle(list);
        return ResponseEntity.ok(list);
    }
    //get all questions by quiz id
    @GetMapping("/quiz/all/{qId}")
    public ResponseEntity<Set<Question>> getQuestionByQuizAdmin(@PathVariable("qId") Long qId){
        Quiz quiz=this.quizService.getQuiz(qId);
        //We will get the number of questions of all the quizzes
        Set<Question> questionsOfQuiz=quiz.getQuestions();
        return ResponseEntity.ok(questionsOfQuiz);
    }
    //delete the question
    @DeleteMapping("/{quesId}")
    public void deleteQuestionById(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
       // return ResponseEntity.ok("Question is deleted successfully");
    }
    //eval quiz
    @PostMapping("/eval-quiz")
    public ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions){
        System.out.println(questions);
        double marksGot=0;
        int correctAnswers=0;
        int attempted=0;
        for(Question q: questions){
            Question question=this.questionService.get(q.getQuesId());
            if(question.getAnswer().equals(q.getGivenAnswer())){
                //correct
                correctAnswers++;
                double marksSingleQuestion=Double.parseDouble(
                        questions.get(0).getQuiz().getMaxMarks())/questions.size();
                marksGot+=marksSingleQuestion;
            }
            if(q.getGivenAnswer()!=null ){
               attempted++;
            }
        };
        Map<Object,Object> map=
                Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);
        return ResponseEntity.ok(map);
    }

}
