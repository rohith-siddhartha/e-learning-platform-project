import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { InstructorService } from 'src/app/service/instructor.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-enrollment-requests',
  templateUrl: './enrollment-requests.component.html',
  styleUrls: ['./enrollment-requests.component.css']
})
export class EnrollmentRequestsComponent implements OnInit {

  instructorId : number | undefined;

  classes:any = [];

  enrollmentRequests:any = [];

  constructor(private instructorService:InstructorService, private loginService:LoginService
    , private snack:MatSnackBar, private classroomService:ClassroomService) { 
      // this.getEnrollmentRequests(this.classes);
    }

  ngOnInit(): void {

    this.instructorId = this.loginService.getUser().id;

    this.getClassrooms(this.instructorId);

    // this.getEnrollmentRequests(this.classes);

  }

  getClassrooms(instructorId:any){

    this.instructorService.getClassroomsByInstructorId(instructorId).subscribe(
      (data:any)=>{
  
        this.classes = data;

        this.getEnrollmentRequests(data);

      },
      (error:any)=>{
        this.snack.open('getting classrooms failed', '', {
          duration: 1000
        });
      }
    );

  }

  getEnrollmentRequests(classes:any[]){

    for (var classroom of classes){
      this.getEnrollmentRequestsByClassId(classroom.id);
    }

  }

  getEnrollmentRequestsByClassId(classroomId:any){
  
    this.classroomService.getEnrollmentRequestsByClassroomId(classroomId).subscribe(
      (data:any)=>{

        for(var x of data){
          this.enrollmentRequests.push(x);
        }

      },
      (error:any)=>{
        this.snack.open('getting infrmatn failed', '', {
          duration: 1000
        });
      }
    );

  }

  enrolStudent(enrollmentRequest:any){

    this.classroomService.enrolStudent(enrollmentRequest).subscribe(
      (data:any)=>{

        this.snack.open( data.name + ' is now your student', '', {
          duration: 1000
        });

        this.enrollmentRequests.splice(this.enrollmentRequests.indexOf(enrollmentRequest),1);

      },
      (error:any)=>{

        this.snack.open(' something wrong ', '', {
          duration: 1000
        });

      }
    );

  }

  discardEnrollment(enrollmentRequest:any){

    this.classroomService.discardEnrollment(enrollmentRequest).subscribe(
      (data:any)=>{

        this.snack.open(' enrollment request declined ', '', {
          duration: 1000
        });

        this.enrollmentRequests.splice(this.enrollmentRequests.indexOf(enrollmentRequest),1);

      },
      (error:any)=>{

        this.snack.open(' something wrong ', '', {
          duration: 1000
        });

      }
    );

  }

}
