import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseurl from '../helper';

@Injectable({
  providedIn: 'root'
})
export class ClassroomService {

  constructor(private http:HttpClient) { }

  public getAllClassrooms() {

    let url = `${baseurl}/classroom/all`;

    return this.http.get(url);
  }

  public getClassroomById( classroomId:any ) {

    let url = `${baseurl}/classroom`;

    url = url + '/' + classroomId;

    return this.http.get(url);
  }

  public getQuizzesByClassroomId( classroomId:any ) {

    let url = `${baseurl}/classroom`;

    url = url + '/' + classroomId + '/' + 'quizzes';

    return this.http.get(url);
  }

  public getNoticesByClassroomId( classroomId:any ) {

    let url = `${baseurl}/classroom`;

    url = url + '/' + classroomId + '/' + 'notices';

    return this.http.get(url);
  }

  public enrolStudent(enrollmentRequest:any) {

    let url = `${baseurl}/classroom/enrolstudent`;

    return this.http.post(url,enrollmentRequest);

  }

  public discardEnrollment(enrollmentRequest:any) {

    let url = `${baseurl}/enrollment/discardenrollment`;

    return this.http.post(url,enrollmentRequest);

  }

  public getEnrollmentRequestsByClassroomId(classroomId:any) {

    let url = `${baseurl}/classroom/`;

    url = url + classroomId + `/enrollmentrequests`;

    return this.http.get(url);

  }

  public removeStudentFromClassroom(classroomId:number,studentId:number) {

    let url = `${baseurl}/classroom/`;

    url = url + classroomId + `/removestudent/` + studentId;

    return this.http.put(url,{});

  }

  public addComment(comment:any) {

    let url = `${baseurl}/comment/create`;

    return this.http.post(url,comment);

  }

  public getCommentsByNoticeId(noticeId:number) {
    let url = `${baseurl}/notice/`;

    url = url + noticeId + `/comments`;

    return this.http.get(url);
  }

  public getStudentsByClassroomId(classroomId:any) {

    let url = `${baseurl}/classroom/`;

    url = url + classroomId + `/students`;

    return this.http.get(url);

  }

  public deleteClassroomByInstructorId(classId:number) {

    let url = `${baseurl}/classroom/delete/`;

    url = url + classId;

    return this.http.delete(url);

  }

  public getQuizById( quizId:any ) {

    let url = `${baseurl}/quiz`;

    url = url + '/' + quizId;

    return this.http.get(url);
  }

  public evalQuiz( attempt:any ) {

    let url = `${baseurl}/quiz/evaluatequiz`;

    return this.http.post(url,attempt);
  }

  public getCategories() {

    let url = `${baseurl}/category/all`;
    return this.http.get(url);

  }

  public createEnrollment(enrollment:any) {

    let url = `${baseurl}/enrollment/create`;

    return this.http.post(url,enrollment);

  }

}
