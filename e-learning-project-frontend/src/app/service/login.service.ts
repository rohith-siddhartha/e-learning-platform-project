import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';
import baseurl from './helper';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  public loginStatusSubject = new Subject<boolean>();

  baseUrl = `${baseurl}`;

  constructor(private http:HttpClient) { }


  // generating token
  public generateToken( user:any ) {

    var url = `${baseurl}/generate`;

    return this.http.post(url, user);
  }


  // getting the current user
  public getCurrentUser() {
    return this.http.get( this.baseUrl+`/current-user`);
  }

  
  // logging in user by setting the token in local storage
  public loginUser(token:any) {
    localStorage.setItem('token', token);

    return true;
  }

  //isLogin: user is logged in or not
  public isLoggedIn() {
    let tokenStr = localStorage.getItem('token');
    if (tokenStr == undefined || tokenStr == '' || tokenStr == null) {
      return false;
    } else {
      return true;
    }
  }

  // logout : remove token from local storage
  public logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
    // this.loginStatusSubject.next(false);
    return true;
  }

  //get token
  public getToken() {
    return localStorage.getItem('token');
  }

  //set userDetail
  public setUser(user: any) {
    localStorage.setItem('user', JSON.stringify(user));
  }

  //getUser
  public getUser() {
    let userStr = localStorage.getItem('user');
    if (userStr != null) {
      return JSON.parse(userStr);
    } else {
      this.logout();
      return null;
    }
  }

  //get user role

  public getUserRole() {
    let user = this.getUser();
    return user.role;
  }

}
