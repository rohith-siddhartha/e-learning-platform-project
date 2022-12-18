import { HttpClient, HttpParams } from '@angular/common/http';
import { not } from '@angular/compiler/src/output/output_ast';
import { Injectable } from '@angular/core';
import baseurl from './helper';

@Injectable({
  providedIn: 'root'
})
export class InstructorService {

  constructor(private http: HttpClient) { }

  public getClassroomsByInstructorId( id:number ) {

    let url = `${baseurl}/instructor/classrooms`;

    url = url + '/' + id;

    return this.http.get(url);
  }

  public createClassroom( classroom:any ) {

    let url = `${baseurl}/classroom/create`;

    return this.http.post(url,classroom);
  }

  public update(user:any) {

    var url;

    if(user.role === "student"){
      url = `${baseurl}/student/update`;
    }else{
      url = `${baseurl}/instructor/update`;
    }

    return this.http.put(url,user);
  }

  public createQuiz(quiz:any) {

    let url = `${baseurl}/quiz/create`;

    return this.http.post(url,quiz);

  }

  public getQuizzesByClassroomId( classroomId:any ) {

    let url = `${baseurl}/classroom`;

    url = url + '/' + classroomId + '/' + 'quizzes';

    return this.http.get(url);
  }

  public getClassroomById( classroomId:any ) {

    let url = `${baseurl}/classroom`;

    url = url + '/' + classroomId;

    return this.http.get(url);
  }

  public createNotice( notice:any) {

    let url = `${baseurl}/notice/create`;

    return this.http.post(url,notice);

  }

  public getNoticesByClassroomId( classroomId:any ) {

    let url = `${baseurl}/classroom`;

    url = url + '/' + classroomId + '/' + 'notices';

    return this.http.get(url);
  }

}
