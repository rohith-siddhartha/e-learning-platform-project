import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-profile-details-form',
  templateUrl: './profile-details-form.component.html',
  styleUrls: ['./profile-details-form.component.css']
})
export class ProfileDetailsFormComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  interestsList: string[] = ['Java', 'Python', 'Spring', 'Angular', 'Sql'];

  firstname = new FormControl("",[]);
  lastname = new FormControl("",[]);
  about = new FormControl("",[Validators.maxLength(60)]);
  interests = new FormControl("",[]);
  profilPicture = new FormControl("",[]);

  profileDetailsForm = new FormGroup({
    firstName:this.firstname,
    lastname:this.lastname,
    about:this.about,
    interests:this.interests,
    profilPicture:this.profilPicture
  })

  
  submit() {

  }

}
