import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { dateInputsHaveChanged } from '@angular/material/datepicker/datepicker-input-base';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserServiceService } from '../service/user-service.service'; 

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  constructor(private userService:UserServiceService , private snack: MatSnackBar, public dialogRef: MatDialogRef<SignUpComponent>) { }

  ngOnInit(): void {
  }

  public user = {
    email: '',
    username: '',
    password: '',
    role: ''
  };

  hide = true;

  roles = ["student","instructor"];

  email = new FormControl("",[Validators.email,Validators.required]);
  username = new FormControl("",[Validators.required]);
  password = new FormControl("",[Validators.required,Validators.minLength(6)]);
  role = new FormControl("",[Validators.required]);

  signUpForm = new FormGroup({
    email:this.email,
    username:this.username,
    password:this.password,
    role:this.role
  })

  submit() {

    if (this.email.value.trim() == '' || this.email.value == null) {
      this.snack.open('Email is required !! ', '', {
        duration: 3000,
      });
      return;
    }

    if (this.username.value.trim() == '' || this.username.value == null) {
      this.snack.open('Username is required !! ', '', {
        duration: 3000,
      });
      return;
    }

    if (this.password.value.trim() == '' || this.password.value == null) {
      this.snack.open('Password is required !! ', '', {
        duration: 3000,
      });
      return;
    }

    this.user.email = this.email.value;
    this.user.username = this.username.value;
    this.user.password = this.password.value;
    this.user.role = this.role.value;

    this.userService.addUser(this.user).subscribe(
      (data:any)=>{
        this.snack.open('signup successful', '', {
          duration: 1000
        });

        this.dialogRef.close();

      },
      (error:any)=>{
        this.snack.open('signup unsuccessful', '', {
          duration: 1000
        });

        this.dialogRef.close();

      }
    );

  }

  closeForm(){

    this.dialogRef.close();

  }

}
