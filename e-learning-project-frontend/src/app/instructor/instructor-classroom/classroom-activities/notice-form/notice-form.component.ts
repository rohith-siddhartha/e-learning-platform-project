import { not } from '@angular/compiler/src/output/output_ast';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InstructorProfileComponent } from 'src/app/instructor/instructor-profile/instructor-profile.component';
import { InstructorService } from 'src/app/service/instructor.service';

export interface DialogData {
  animal: string;
  name: string;
}

@Component({
  selector: 'app-notice-form',
  templateUrl: './notice-form.component.html',
  styleUrls: ['./notice-form.component.css']
})
export class NoticeFormComponent implements OnInit {

  notice:{ title: string, description: string, createdOn: Date, classroom:any } = {

    title:'',
    description:'',
    createdOn:new Date(),
    classroom:null
  };

  constructor( public dialogRef: MatDialogRef<NoticeFormComponent>, @Inject(MAT_DIALOG_DATA) private data: any, private instructorService:InstructorService,
  private snack:MatSnackBar) { }

  ngOnInit(): void {
  }

  title = new FormControl("",[Validators.required])
  content = new FormControl("",[Validators.required]);

  noticeForm = new FormGroup({
    title:this.title,
    username:this.content
  });

  submit(){

    this.notice.title = this.title.value;
    this.notice.description = this.content.value;
    this.notice.createdOn = new Date();
    this.notice.classroom = this.data.classroom;

    this.instructorService.createNotice(this.notice).subscribe(

      (data:any)=>{

        this.snack.open('Notice created succesfully', '', {
          duration: 1000
        });

        this.dialogRef.close({ data: data });

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
