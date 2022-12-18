import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { InstructorService } from 'src/app/service/instructor.service';

@Component({
  selector: 'app-enrol-form',
  templateUrl: './enrol-form.component.html',
  styleUrls: ['./enrol-form.component.css']
})
export class EnrolFormComponent implements OnInit {

  enrollment:{ name: string, motivation: string, student:any, classroom:any } = {

    name:'',
    motivation:'',
    student:null,
    classroom:null
  };

  constructor( public dialogRef: MatDialogRef<EnrolFormComponent>, @Inject(MAT_DIALOG_DATA) private data: any, private classroomService:ClassroomService,
  private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  name = new FormControl("",[Validators.required])
  motivation = new FormControl("",[Validators.required]);

  enrollmentForm = new FormGroup({
    name:this.name,
    motivation:this.motivation
  });

  submit(){

    this.enrollment.name = this.name.value;
    this.enrollment.motivation = this.motivation.value;
    this.enrollment.student = this.data.student;
    this.enrollment.classroom = this.data.classroom;

    this.classroomService.createEnrollment(this.enrollment).subscribe(

      (data:any)=>{

        this.snack.open('Enrolment created succesfully', '', {
          duration: 1000
        });

        this.dialogRef.close();

      },
      (error:any)=>{

        this.snack.open('Notice not created', '', {
          duration: 1000
        });

        this.dialogRef.close();

      }


    )

  };

}
