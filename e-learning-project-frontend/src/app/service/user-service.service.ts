import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseurl from './helper';

@Injectable({
  providedIn: 'root'
})
export class UserServiceService {

  constructor(private http: HttpClient) { }
  //add user

  public addUser(user: any) {

    var url = '';

    if(user.role === "student"){
      url = `${baseurl}/student/create`;
    }

    if(user.role === "instructor"){
      url = `${baseurl}/instructor/create`;
    }

    return this.http.post(url, user);
  }

}
