import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginService } from 'src/app/service/login.service';
import { StudentServiceService } from 'src/app/service/student/student-service.service';

@Component({
  selector: 'app-student-classes',
  templateUrl: './student-classes.component.html',
  styleUrls: ['./student-classes.component.css']
})
export class StudentClassesComponent implements OnInit {

  studentId!:number;

  classes:any = [];

  constructor(private loginService:LoginService, private studentService:StudentServiceService,
    private snack:MatSnackBar) { }

  ngOnInit(): void {

    this.studentId = this.loginService.getUser().id;

    this.getClassrooms(this.studentId);

  }

  getClassrooms(studentId:any){

    this.studentService.getClassroomsByStudentId(studentId).subscribe(
      (data:any)=>{
  
        this.classes = data;

      },
      (error:any)=>{
        this.snack.open('getting classrooms failed', '', {
          duration: 1000
        });
      }
    );

  }

}
