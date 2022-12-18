import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Data } from '@angular/router';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { InstructorService } from 'src/app/service/instructor.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-instructor-classes',
  templateUrl: './instructor-classes.component.html',
  styleUrls: ['./instructor-classes.component.css']
})
export class InstructorClassesComponent implements OnInit {

  instructorId : number | undefined;

  classes:any = [];

  constructor(private instructorService:InstructorService, private loginService:LoginService
    , private snack:MatSnackBar, private classroomService:ClassroomService) { }

  ngOnInit(): void {

    this.instructorId = this.loginService.getUser().id;

    this.getClassrooms(this.instructorId);

  }

  getClassrooms(instructorId:any){

    this.instructorService.getClassroomsByInstructorId(instructorId).subscribe(
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

  delete(classId:number){
      if(confirm("Are you sure to delete")) {
        this.classroomService.deleteClassroomByInstructorId(classId).subscribe(
          (data:Data)=>{
      
            this.snack.open('classroom deleted', '', {
              duration: 1000
            });
    
          },
          (error:Error)=>{
            this.snack.open('deleting classroom failed', '', {
              duration: 1000
            });
          }
        );
    }
  }

  // items = ["item1","item2","af","aff","item1","item2","af","aff","item1","item2","af"];

}
