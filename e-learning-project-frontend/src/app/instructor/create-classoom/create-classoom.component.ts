import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { MAT_CHECKBOX_CONTROL_VALUE_ACCESSOR } from '@angular/material/checkbox';
import { MatSnackBar } from '@angular/material/snack-bar';
import { title } from 'process';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { InstructorService } from 'src/app/service/instructor.service';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-create-classoom',
  templateUrl: './create-classoom.component.html',
  styleUrls: ['./create-classoom.component.css']
})
export class CreateClassoomComponent implements OnInit {

  constructor(private _formBuilder: FormBuilder, private snack:MatSnackBar, private instructorService:InstructorService, 
    private loginService:LoginService, private classroomService:ClassroomService) { }

  ngOnInit(): void {

    this.instructor = this.loginService.getUser();
    this.getCategories();

  }

  

  instructor:any;

  classroom:{ title: string, description: string, instructors: any[], capacity:number, categories:any[], createdOn:Date } = {
    title:'',
    description:'',
    instructors:[],
    capacity:Number.MAX_VALUE,
    categories:[],
    createdOn:new Date()
  }

  categoryNames:any = [];

  title = new FormControl("",[Validators.required]);
  description = new FormControl("",[Validators.maxLength(200),Validators.required]);
  capacity = new FormControl("",[]);
  // Categories = new Array(this.categoryNames.length).fill(new FormControl("",[]));
  Categories = new Map();

  firstFormGroup = this._formBuilder.group({
    title:this.title,
    description:this.description
  });
  secondFormGroup = this._formBuilder.group({
    Categories:this.Categories
    // Categories:this.Categories
  });

  ThirdFormGroup = this._formBuilder.group({
    capacity:this.capacity
  });

  submit(){
    if (this.title.value.trim() == '' || this.title.value == null) {
      this.snack.open('title is required !! ', '', {
        duration: 3000,
      });
      return;
    }

    this.classroom.title = this.title.value;
    this.classroom.description = this.description.value;
    this.classroom.capacity = this.capacity.value;
    for(const [key, value] of this.Categories.entries()){
      if(value.value === true){
        this.classroom.categories.push({name:key});
      }
    }
    this.classroom.instructors.push(this.instructor);
    this.classroom.createdOn = new Date();

    this.instructorService.createClassroom(this.classroom).subscribe(
      (data:any)=>{

        this.snack.open('classroom created' , '', {
          duration: 1000
        });

      },
      (error:any)=>{
        this.snack.open('classroom deleted', '', {
          duration: 1000
        });
      }
    );

    this.firstFormGroup.reset();
    this.secondFormGroup.reset();
    this.ThirdFormGroup.reset();

  }

  getCategories(){
    this.classroomService.getCategories().subscribe(
      (data:any)=>{
        for(var x of data){
          this.categoryNames.push(x.name);
        }
        for(var x of this.categoryNames){
          this.Categories.set(x,new FormControl("",[]));
        }
      },
      (error:any)=>{
        this.snack.open('getting categories failed', '', {
          duration: 1000
        });
      }
    );
  }

}
