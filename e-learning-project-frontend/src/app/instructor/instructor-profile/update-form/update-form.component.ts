import { Component, Inject, OnInit, Optional } from '@angular/core';
import { FormControl, Validators, FormGroup } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { LoginComponent } from 'src/app/login/login.component';
import { InstructorService } from 'src/app/service/instructor.service';
import { LoginService } from 'src/app/service/login.service';
import { DialogData } from '../../instructor-classroom/classroom-activities/notice-form/notice-form.component';

export interface UpdateFormDialogData {
  user: any;
}

@Component({
  selector: 'app-update-form',
  templateUrl: './update-form.component.html',
  styleUrls: ['./update-form.component.css']
})
export class UpdateFormComponent implements OnInit {

  user!:any;

  constructor(private login: LoginService, private instructorService: InstructorService, private snack:MatSnackBar, @Optional() public dialogRef: MatDialogRef<UpdateFormComponent>, @Inject(MAT_DIALOG_DATA) public data: UpdateFormDialogData) { }

  ngOnInit(): void {
    this.user = this.data.user;
  }

  

  username = new FormControl(this.data.user.username,[Validators.required]);
  email = new FormControl(this.data.user.email,[Validators.required, Validators.email]);
  firstName = new FormControl("",[]);
  lastName = new FormControl("",[]);
  about = new FormControl("",[]);

  updateForm = new FormGroup({
    username:this.username,
    email:this.email,
    firstName:this.firstName,
    lastNamel:this.lastName,
    about:this.about
  });



  submit() {

    if (this.username.value.trim() == '' || this.username.value == null) {
      this.snack.open('Username is required !! ', '', {
        duration: 3000,
      });
      return;
    }

    if (this.email.value.trim() == '' || this.email.value == null) {
      this.snack.open('Password is required !! ', '', {
        duration: 3000,
      });
      return;
    }

    this.user.username = this.username.value;
    this.user.email = this.email.value;
    this.user.firstName = this.firstName.value;
    this.user.lastName = this.lastName.value;
    this.user.about = this.about.value;

    this.instructorService.update(this.user).subscribe(
      (data:any)=>{
        this.login.setUser(data);
        this.snack.open('Update successful', '', {
          duration: 1000
        });
      },
      (error:any)=>{
        this.snack.open('Update failed', '', {
          duration: 1000
        });
      }
    );

    this.updateForm.reset();
    this.dialogRef.close();

  }

  closeForm(){

    this.dialogRef.close();

  }

}
