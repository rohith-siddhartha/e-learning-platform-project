import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { SignUpComponent } from './sign-up/sign-up.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MaterialModule } from '../material/material.module'
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from './login/login.component';
import { authInterceptorProviders } from './service/auth.interceptor';
import { HomeComponent } from './home/home.component';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { InstructorDashboardComponent } from './instructor/instructor-dashboard/instructor-dashboard.component';
import { ProfileDetailsFormComponent } from './profile-details-form/profile-details-form.component';
import { InstructorProfileComponent } from './instructor/instructor-profile/instructor-profile.component';
import { InstructorClassesComponent } from './instructor/instructor-classes/instructor-classes.component';
import { CreateClassoomComponent } from './instructor/create-classoom/create-classoom.component';
import { InstructorClassroomComponent } from './instructor/instructor-classroom/instructor-classroom.component';
import { NoticeFormComponent } from './instructor/instructor-classroom/classroom-activities/notice-form/notice-form.component';
import { AddQuizComponent } from './instructor/instructor-classroom/classroom-activities/add-quiz/add-quiz.component';
import { AddQuestionComponent } from './instructor/instructor-classroom/classroom-activities/add-question/add-question.component';
import { StudentClassesComponent } from './student/student-classes/student-classes.component';
import { ExploreClassesComponent } from './student/explore-classes/explore-classes.component';
import { EnrolFormComponent } from './student/forms/enrol-form/enrol-form.component';
import { EnrollmentRequestsComponent } from './instructor/instructor-classroom/classroom-activities/enrollment-requests/enrollment-requests.component';
import { StudentsListComponent } from './instructor/instructor-classroom/classroom-activities/students-list/students-list.component';
import { StudentClassroomComponent } from './student/student-classroom/student-classroom.component';
import { NavbarComponent } from './navbar/navbar.component';
import { MatDialogRef } from '@angular/material/dialog';
import { UpdateFormComponent } from './instructor/instructor-profile/update-form/update-form.component';
import { StudentProfileComponent } from './student/student-profile/student-profile.component';
import { StudentClassroomStudentListComponent } from './student/student-classroom-student-list/student-classroom-student-list.component';
import { CommentBoxComponent } from './student/comment-box/comment-box.component';
import { CommentsComponent } from './student/comment-box/comments/comments.component';
import Swal from 'sweetalert2';
import { QuizPageComponent } from './student/quiz-page/quiz-page.component';
import { ResultBoxComponent } from './student/quiz-page/result-box/result-box.component';
import { NgxUiLoaderHttpModule, NgxUiLoaderModule } from 'ngx-ui-loader';
import { AttemptsComponent } from './instructor/instructor-classroom/classroom-activities/attempts/attempts.component';

@NgModule({
  declarations: [
    AppComponent,
    SignUpComponent,
    LoginComponent,
    HomeComponent,
    StudentDashboardComponent,
    InstructorDashboardComponent,
    ProfileDetailsFormComponent,
    InstructorProfileComponent,
    InstructorClassesComponent,
    CreateClassoomComponent,
    InstructorClassroomComponent,
    NoticeFormComponent,
    AddQuizComponent,
    AddQuestionComponent,
    StudentClassesComponent,
    ExploreClassesComponent,
    EnrolFormComponent,
    EnrollmentRequestsComponent,
    StudentsListComponent,
    StudentClassroomComponent,
    NavbarComponent,
    UpdateFormComponent,
    StudentProfileComponent,
    StudentClassroomStudentListComponent,
    CommentBoxComponent,
    CommentsComponent,
    QuizPageComponent,
    ResultBoxComponent,
    AttemptsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    ReactiveFormsModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    NgxUiLoaderModule,
    NgxUiLoaderHttpModule,
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
