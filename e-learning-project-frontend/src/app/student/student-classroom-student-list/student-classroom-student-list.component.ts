import { Component, Input, OnInit, SimpleChanges } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';

@Component({
  selector: 'app-student-classroom-student-list',
  templateUrl: './student-classroom-student-list.component.html',
  styleUrls: ['./student-classroom-student-list.component.css']
})
export class StudentClassroomStudentListComponent implements OnInit {

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

}
