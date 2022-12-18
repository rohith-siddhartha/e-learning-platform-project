import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';

@Component({
  selector: 'app-students-list',
  templateUrl: './students-list.component.html',
  styleUrls: ['./students-list.component.css']
})
export class StudentsListComponent implements OnInit {

  @Input() classroom:any;

  
  students:any = [];

  constructor(private classroomService:ClassroomService, private snack:MatSnackBar) {

   }

  ngOnInit(): void {

  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['classroom']) {
        this.getStudents(this.classroom.id);
    }
}

  getStudents(classroomId:number){

    this.classroomService.getStudentsByClassroomId(classroomId).subscribe(
      (data:any)=>{

        this.students = data;

      },
      (error:any)=>{

        this.snack.open('error students failed', '', {
          duration: 1000
        });

      }
    )

  }

  removeStudentFromClassroom(studentId:number){

    this.classroomService.removeStudentFromClassroom(this.classroom.id,studentId).subscribe(

      (data:any)=>{

        this.classroom = data;

        this.snack.open('removed student', '', {
          duration: 1000
        });

      },
      (error:any)=>{

        this.snack.open('failed student', '', {
          duration: 1000
        });

      }

    )

  }

}
