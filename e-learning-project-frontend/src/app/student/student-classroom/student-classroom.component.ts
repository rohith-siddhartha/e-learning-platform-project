import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { CommentBoxComponent } from '../comment-box/comment-box.component';
import Swal from 'sweetalert2/dist/sweetalert2.js';
import { LoginService } from 'src/app/service/login.service';
import { ResultBoxComponent } from '../quiz-page/result-box/result-box.component';

@Component({
  selector: 'app-student-classroom',
  templateUrl: './student-classroom.component.html',
  styleUrls: ['./student-classroom.component.css']
})
export class StudentClassroomComponent implements OnInit {

  classId:any;
  student:any;
  classroom:any;
  notices:any = [];
  quizzes:any = [];

  date!:Date;

  constructor(private route: ActivatedRoute, private classroomService:ClassroomService, private dialog: MatDialog, private router:Router, private loginService: LoginService) { }

  ngOnInit(): void {

    this.classId = this.route.snapshot.params['classId'];
    this.date = new Date();

    this.classroomService.getClassroomById(this.classId).subscribe(
      (data: any) => {
        this.classroom = data;

        this.classroomService.getNoticesByClassroomId(this.classId).subscribe(
          (notices: any)=>{
            this.notices = notices;
            this.notices.sort(function(a:any,b:any){
              return new Date(b.createdOn).valueOf() - new Date(a.createdOn).valueOf();
            });
          },
          (error: any)=>{
            console.log("error notices")
          }
        );

        this.classroomService.getQuizzesByClassroomId(this.classId).subscribe(
          (quizzes: any)=>{
            this.quizzes = quizzes;
            this.quizzes.sort(function(a:any,b:any){
              return new Date(b.createdOn).valueOf() - new Date(a.createdOn).valueOf();
            });
          },
          (error: any)=>{
            console.log("error notices")
          }
        );

      },
      (error: any) => {
        console.log(error);
      }
    );

    this.student = this.loginService.getUser();

  }

  checkAttempt(attempts:any){

    for (let attempt of attempts) {
      if(attempt.studentId === this.student.id){
        return true;
      }
    }

    return false;

  }

  result(quiz:any,attempts:any){

    var a;

    for (let attempt of attempts) {
      if(attempt.studentId === this.student.id){
        a = attempt;
      }
    }

    this.dialog.open(ResultBoxComponent,{
      data: {attempt:a,quiz:quiz},
    });
  }

  commentBox(notice:any) {
    this.dialog.open(CommentBoxComponent,{
      data: {noticeEntity: notice},
    });
  }

  startQuiz( quizId:any ){  
    Swal.fire({  
      title: 'Are you sure want to start the quiz?',
      icon: 'warning',  
      showCancelButton: true,  
      confirmButtonText: 'yes',  
      cancelButtonText: 'cancel'  
    }).then((result) => {  
      if (result.value) {  
        this.router.navigate(['/student/quiz/' + quizId]);
      } else if (result.dismiss === Swal.DismissReason.cancel) {   
      }  
    })  
  }

}
