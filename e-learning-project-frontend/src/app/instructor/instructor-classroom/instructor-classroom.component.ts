import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { InstructorService } from 'src/app/service/instructor.service';
import { CommentBoxComponent } from 'src/app/student/comment-box/comment-box.component';
import { AddQuizComponent } from './classroom-activities/add-quiz/add-quiz.component';
import { AttemptsComponent } from './classroom-activities/attempts/attempts.component';
import { NoticeFormComponent } from './classroom-activities/notice-form/notice-form.component';

@Component({
  selector: 'app-instructor-classroom',
  templateUrl: './instructor-classroom.component.html',
  styleUrls: ['./instructor-classroom.component.css']
})
export class InstructorClassroomComponent implements OnInit {

  classId!:number;
  classroom:any;

  notices:any = [];

  quizzes:any = [];

  constructor(private route: ActivatedRoute, private instructorService:InstructorService, private dialog: MatDialog) { }

  ngOnInit(): void {

    this.classId = this.route.snapshot.params['classId'];

    this.instructorService.getClassroomById(this.classId).subscribe(
      (data: any) => {
        this.classroom = data;

        this.instructorService.getNoticesByClassroomId(this.classId).subscribe(
          (notices: any)=>{
            this.notices = notices;
            this.notices.sort(function(a:any,b:any){
              return new Date(b.createdOn).valueOf() - new Date(a.createdOn).valueOf();
            });
          },
          (error: any)=>{
            console.log("error getting notices")
          }
        );

        this.instructorService.getQuizzesByClassroomId(this.classId).subscribe(
          (quizzes: any)=>{
            this.quizzes = quizzes;
            this.quizzes.sort(function(a:any,b:any){
              return new Date(b.createdOn).valueOf() - new Date(a.createdOn).valueOf();
            });
          },
          (error: any)=>{
            console.log("error getting quizzes")
          }
        );

      },
      (error: any) => {
        console.log(error);
      }
    );

  }

  openNoticeForm() {
    let dialogRef = this.dialog.open(NoticeFormComponent,{
      data: {classroom: this.classroom},
    });

    dialogRef.afterClosed().subscribe(res => {
      this.notices.unshift(res.data);
    })
  }

  openQuizForm() {
    let dialogRef  = this.dialog.open(AddQuizComponent,{
      data: {classroom: this.classroom},
    });

    dialogRef.afterClosed().subscribe(res => {
      this.quizzes.unshift(res.data);
    })
  }

  attempts(quiz:any) {
    this.dialog.open(AttemptsComponent,{
      data: {quiz: quiz},
    });
  }

  commentBox(notice:any) {
    this.dialog.open(CommentBoxComponent,{
      data: {noticeEntity: notice},
    });
  }

}
