import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ResultBoxComponent } from 'src/app/student/quiz-page/result-box/result-box.component';

@Component({
  selector: 'app-attempts',
  templateUrl: './attempts.component.html',
  styleUrls: ['./attempts.component.css']
})
export class AttemptsComponent implements OnInit {

  
  attempts:any;
  quiz:any;

  constructor( private dialog: MatDialog, public dialogRef: MatDialogRef<AttemptsComponent>, @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {


    this.attempts = this.data.quiz.attempts;


    this.quiz = this.data.quiz;

  }

  result(attempt:any,quiz:any){

    this.dialog.open(ResultBoxComponent,{
      data: {attempt:attempt,quiz:quiz},
    });
  }

}
