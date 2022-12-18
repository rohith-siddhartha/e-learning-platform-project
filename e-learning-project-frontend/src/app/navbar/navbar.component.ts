import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { LoginComponent } from '../login/login.component';
import { LoginService } from '../service/login.service';
import { SignUpComponent } from '../sign-up/sign-up.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn!:boolean;

  constructor(private dialog:MatDialog, private loginService:LoginService, private router:Router) { }

  ngOnInit(): void {

    this.isLoggedIn = this.loginService.isLoggedIn();
    this.loginService.loginStatusSubject.asObservable().subscribe((data) => {
      this.isLoggedIn = this.loginService.isLoggedIn();
    });

  }

  ngOnChange(): void {

    this.isLoggedIn = this.loginService.isLoggedIn();

  }

  openLoginForm(): void {
    const dialogRef = this.dialog.open(LoginComponent, {
      width: '300px',
      height: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  openSignUpForm(): void {
    const dialogRef = this.dialog.open(SignUpComponent, {
      width: '350px',
      height: '600px'
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  public logout() {
    this.loginService.logout();
    window.location.reload();
  }

}
