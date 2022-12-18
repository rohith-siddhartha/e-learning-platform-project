import { LocationStrategy } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { LoginService } from 'src/app/service/login.service';
import Swal from 'sweetalert2/dist/sweetalert2.js';
import { StudentClassesComponent } from '../student-classes/student-classes.component';

@Component({
  selector: 'app-quiz-page',
  templateUrl: './quiz-page.component.html',
  styleUrls: ['./quiz-page.component.css']
})
export class QuizPageComponent implements OnInit {

  quizId!:number;

  quiz!:any;

  questions!:any;

  answersMap = new Map <any,any> ();

  responseArray:any = [];

  isSubmit = false;

  timer: any;

  

  attempt = {
    quizIdentifier:this.quizId,
    quiz:this.quiz,
    studentId:this.userService.getUser().id,
    studentName:this.userService.getUser().username,
    responseArray:this.responseArray
  }

  marksScored = 0;
  correctAnswers = 0;
  attempted = 0;

  constructor( private locationSt:LocationStrategy, private route:ActivatedRoute
                , private classroomService:ClassroomService, private userService:LoginService ) { }

  ngOnInit(): void {
    this.preventBackButton();
    this.quizId = this.route.snapshot.params['quizId'];
    this.getQuiz();

    this.attempt.studentId = this.userService.getUser().id;

  }

  preventBackButton() {
    history.pushState(null, '', location.href);
    this.locationSt.onPopState(() => {
      history.pushState(null, '', location.href);
    });
  }

  getQuiz(){

    this.classroomService.getQuizById(this.quizId).subscribe(
      (data: any) => {

        this.quiz = data;
        this.questions = data.questions;

        console.log(this.questions);

        for(let q of this.questions){
          this.answersMap.set(q.id,'');
        }

        this.timer = this.quiz.duration * 60;

        this.startTimer();
  
      },
      (error: any) => {
        console.log(error);
      }
    );

  }

  startTimer() {
    let t = window.setInterval(() => {
      //code
      if (this.timer <= 0) {
        Swal.fire({
          title: ' Time up!! \n Submitting the quiz',
          timer: 1000,
          showCancelButton: false,
          showConfirmButton: false
        }).then(
           () => {
            this.evalQuiz();
          }
        );
        // this.evalQuiz();
        clearInterval(t);
      } else {
        this.timer--;
      }
    }, 1000);
  }

  submitQuiz() {
    Swal.fire({
      title: 'Do you want to submit the quiz?',
      showCancelButton: true,
      confirmButtonText: `Submit`,
      icon: 'info',
    }).then((e) => {
      if (e.isConfirmed) {
        this.isSubmit = true;

        this.evalQuiz();
      }
    });
  }

  evalQuiz() {
    //calculation
    //call to sever  to check questions

    this.attempt.quizIdentifier = this.quizId;

    for (let [key, value] of this.answersMap) {
      this.attempt.responseArray.push([key,value]);
      }

    this.classroomService.evalQuiz(this.attempt).subscribe(
      (data: any) => {
        console.log(data);
        this.marksScored = data.marksScored;
        this.correctAnswers = data.correctAnswers;
        this.attempted = data.attempted;
        this.isSubmit = true;
      },
      (error:any) => {
        console.log(error);
      }
    );
  }

  getFormattedTime() {
    let mm = Math.floor(this.timer / 60);
    let ss = this.timer - mm * 60;
    return `${mm} min : ${ss} sec`;
  }

}

