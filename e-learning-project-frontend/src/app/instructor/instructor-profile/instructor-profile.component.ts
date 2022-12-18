import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { LoginService } from 'src/app/service/login.service';
import { UpdateFormComponent } from './update-form/update-form.component';

@Component({
  selector: 'app-instructor-profile',
  templateUrl: './instructor-profile.component.html',
  styleUrls: ['./instructor-profile.component.css']
})
export class InstructorProfileComponent implements OnInit {

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
