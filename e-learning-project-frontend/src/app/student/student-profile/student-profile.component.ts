import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { UpdateFormComponent } from 'src/app/instructor/instructor-profile/update-form/update-form.component';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-student-profile',
  templateUrl: './student-profile.component.html',
  styleUrls: ['./student-profile.component.css']
})
export class StudentProfileComponent implements OnInit {

  user:any = null;

  constructor(private dialog:MatDialog, private login: LoginService) { }

  ngOnInit(): void {
    this.user = this.login.getUser();
  }

  openUpdateForm(): void {
    const dialogRef = this.dialog.open(UpdateFormComponent, {
      width: '300px',
      height: '700px',
      data: {user: this.user}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

}
