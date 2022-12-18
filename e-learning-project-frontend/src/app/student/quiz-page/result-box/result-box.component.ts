import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-result-box',
  templateUrl: './result-box.component.html',
  styleUrls: ['./result-box.component.css']
})
export class ResultBoxComponent implements OnInit {

  constructor( private loginService:LoginService , private classroomService:ClassroomService, public dialogRef: MatDialogRef<ResultBoxComponent>, @Inject(MAT_DIALOG_DATA) public data: any,
              private snack:MatSnackBar ) { }

  ngOnInit(): void {
  }

}
