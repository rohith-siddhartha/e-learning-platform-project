import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseurl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {

  constructor(private http:HttpClient) { }

  public getClassroomsByStudentId( id:number ) {

    let url = `${baseurl}/student/classrooms`;

    url = url + '/' + id;

    return this.http.get(url);
  }

}
