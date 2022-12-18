import { Component, OnInit, Output, EventEmitter, Inject } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css']
})
export class AddQuestionComponent implements OnInit {

  @Output() event = new EventEmitter<any>();

  passData(){
    this.event.emit({question:this.question, add:this.add});
  }

  question:any = {

    title:'',
    description:'',
    options:['','','',''],
    answer:'',
    marks:0,
    quiz:null

  }

  add:boolean = false;

  valid:boolean = false;

  answercheck!:boolean;

  constructor(private dialogRef: MatDialogRef<AddQuestionComponent>) { }

  ngOnInit(): void {
  }

  title = new FormControl("",[Validators.required]);
  description = new FormControl("",[Validators.required]);
  option1 = new FormControl("",[Validators.required]);
  option2 = new FormControl("",[Validators.required]);
  option3 = new FormControl("",[Validators.required]);
  option4 = new FormControl("",[Validators.required]);
  answer = new FormControl("",[Validators.required]);
  marks = new FormControl("",[Validators.required]);

  validator() {
    
    for (var field in this.question){
      if(field === "quiz" || field === "marks"){
        continue;
      }
      if(this.question[field] === null){
        this.valid = false;
      }
    }

    return this.valid = true;

  }

  click() {
    this.add = true;
    this.dialogRef.close({question:this.question, add:this.add});
  }

  cancel() {
    this.add = false;
    this.dialogRef.close({question:this.question, add:this.add});
  }

}
