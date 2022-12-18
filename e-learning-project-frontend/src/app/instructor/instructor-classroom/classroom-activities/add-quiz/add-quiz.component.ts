import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InstructorService } from 'src/app/service/instructor.service';
import { AddQuestionComponent } from '../add-question/add-question.component';

@Component({
  selector: 'app-add-quiz',
  templateUrl: './add-quiz.component.html',
  styleUrls: ['./add-quiz.component.css']
})
export class AddQuizComponent implements OnInit {

  quiz = {

    title:'',
    description:'',
    maxMarks:0,
    duration:null,
    classroom:null,
    questions:[],
    createdOn:new Date(),
    deadline:new Date()

  }

  questions:any = [];

  // date settings
  minDate: Date;
  maxDate: Date;

  constructor(private _formBuilder: FormBuilder, private dialog:MatDialog, public dialogRef: MatDialogRef<AddQuizComponent>, 
    @Inject(MAT_DIALOG_DATA) private data: any, private instructorService:InstructorService, private snack:MatSnackBar) { 

    const currentYear = new Date().getFullYear();
    this.minDate = new Date();
    this.maxDate = new Date(currentYear + 1, 11, 31);

  }

  ngOnInit(): void {
  }

  title = new FormControl("",[Validators.required]);
  description = new FormControl("",[Validators.required]);
  duration = new FormControl("",[Validators.required]);
  deadline = new FormControl("",[Validators.required]);

  popQuestion(index:number) {
    this.questions.splice(index,1);
  }

  questionForm(){
    const dialogRef = this.dialog.open(AddQuestionComponent,{ disableClose: true
      });
    dialogRef.afterClosed().subscribe((result) => {
        console.log('The dialog was closed',result);
        if(result.add){
          this.questions.push(result.question);
        }
      },
      (error)=>{
        console.log("error")
      });
  }

  submit(){
    this.quiz.title = this.title.value;
    this.quiz.description = this.description.value;
    this.quiz.questions = this.questions;
    this.quiz.duration = this.duration.value;
    this.quiz.createdOn = new Date();
    this.quiz.deadline = this.deadline.value;

    for(let q of this.questions){
      this.quiz.maxMarks += q.marks;
    }

    this.quiz.classroom = this.data.classroom;

    this.instructorService.createQuiz(this.quiz).subscribe(

      (data:any)=>{

        this.snack.open('Notice created succesfully', '', {
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

  }

}
