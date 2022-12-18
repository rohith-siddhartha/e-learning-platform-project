import { Component, OnInit, Optional } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor( private loginService: LoginService, private snack: MatSnackBar, private router:Router, @Optional() public dialogRef: MatDialogRef<LoginComponent> ) { }

  ngOnInit(): void {
  }

  hide = true;

  loginDetails = {

    username: '',
    password: ''

  }

  username = new FormControl("",[Validators.required]);
  password = new FormControl("",[Validators.required]);

  loginForm = new FormGroup({
    username:this.username,
    password:this.password
  });



  submit() {

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

    this.loginDetails.username = this.username.value;
    this.loginDetails.password = this.password.value;

    this.loginService.generateToken(this.loginDetails).subscribe(
      (data:any)=>{

        this.loginService.loginUser(data.token);

        this.loginService.getCurrentUser().subscribe((user: any) => {

          // if(userDetails.role=="student"){
          //   this.loginService.setUser(JSON.parse(userDetails.student));
          // }
          
          this.loginService.setUser(user);

          //redirect ...ADMIN: admin-dashboard
          //redirect ...NORMAL:normal-dashboard
          if (this.loginService.getUserRole() == 'student') {
            this.router.navigate(['student']);
            this.loginService.loginStatusSubject.next(true);

          } else if (this.loginService.getUserRole() == 'instructor') {
            this.router.navigate(['instructor']);
            this.loginService.loginStatusSubject.next(true);

          } else {
            this.loginService.logout();
          }
        });

        this.snack.open('Login successful', '', {
          duration: 1000
        });

        this.dialogRef.close();

      },
      (error:any)=>{
        this.snack.open('Login unsuccessful', '', {
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
