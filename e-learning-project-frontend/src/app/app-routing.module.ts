import { FullscreenOverlayContainer } from '@angular/cdk/overlay';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { InstructorClassroomComponent } from './instructor/instructor-classroom/instructor-classroom.component';
import { InstructorDashboardComponent } from './instructor/instructor-dashboard/instructor-dashboard.component';
import { LoginComponent } from './login/login.component';
import { InstructorGuardService } from './service/instructor-guard.service';
import { StudentGuardService } from './service/student-guard.service';
import { SignUpComponent } from './sign-up/sign-up.component';
import { StudentDashboardComponent } from './student-dashboard/student-dashboard.component';
import { QuizPageComponent } from './student/quiz-page/quiz-page.component';
import { StudentClassroomComponent } from './student/student-classroom/student-classroom.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    pathMatch: 'full',
  },
  // {
  //   path: 'signup',
  //   component: SignUpComponent,
  //   pathMatch: 'full',
  // },
  // {
  //   path: 'login',
  //   component: LoginComponent,
  //   pathMatch: 'full',
  // },
  {
    path: 'home',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'instructor',
    component: InstructorDashboardComponent,
    pathMatch: 'full',
    canActivate: [InstructorGuardService]
  },
  {
    path: 'student',
    component: StudentDashboardComponent,
    pathMatch: 'full',
    canActivate: [StudentGuardService]
  },
  {
    path: 'instructor/classroom/:classId',
    component: InstructorClassroomComponent,
    canActivate: [InstructorGuardService]
  },
  {
    path: 'student/classroom/:classId',
    component: StudentClassroomComponent,
    canActivate: [StudentGuardService]
  },
  {
    path: 'student/quiz/:quizId',
    component: QuizPageComponent,
    canActivate: [StudentGuardService]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
