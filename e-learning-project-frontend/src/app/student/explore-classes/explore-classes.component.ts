import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator, PageEvent } from '@angular/material/paginator';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { NoticeFormComponent } from 'src/app/instructor/instructor-classroom/classroom-activities/notice-form/notice-form.component';
import { ClassroomService } from 'src/app/service/classroom/classroom.service';
import { LoginService } from 'src/app/service/login.service';
import { EnrolFormComponent } from '../forms/enrol-form/enrol-form.component';
import { StudentClassesComponent } from '../student-classes/student-classes.component';

export interface UserData {
  id: string;
  name: string;
  progress: string;
  fruit: string;
}

/** Constants used to fill up our data base. */
const FRUITS: string[] = [
  'blueberry',
  'lychee',
  'kiwi',
  'mango',
  'peach',
  'lime',
  'pomegranate',
  'pineapple',
];
const NAMES: string[] = [
  'Maia',
  'Asher',
  'Olivia',
  'Atticus',
  'Amelia',
  'Jack',
  'Charlotte',
  'Theodore',
  'Isla',
  'Oliver',
  'Isabella',
  'Jasper',
  'Cora',
  'Levi',
  'Violet',
  'Arthur',
  'Mia',
  'Thomas',
  'Elizabeth',
];

/**
 * @title Paginator
 */
@Component({
  selector: 'app-explore-classes',
  templateUrl: './explore-classes.component.html',
  styleUrls: ['./explore-classes.component.css']
})
export class ExploreClassesComponent implements OnInit {

  categories = new FormControl('');

  student!:any;

  categoryNames:any[] = [];

  classes:any[] = [];

  classesData:any[] = [];

  categoriesCheckboxMap:any = [];

  checked:boolean = false;

  pageEvent!:PageEvent;

  constructor(private classroomService:ClassroomService, private snack:MatSnackBar, private dialog: MatDialog, private loginService:LoginService) { }

  ngOnInit(): void {

    this.getAllClasses();
    this.getCategories();
    this.student = this.loginService.getUser();

  }

  applyFilter(event: Event) {
  //   const filterValue = (event.target as HTMLInputElement).value;
  //   this.dataSource.filter = filterValue.trim().toLowerCase();

  //   if (this.dataSource.paginator) {
  //     this.dataSource.paginator.firstPage();
  //   }
  }

  categoriesSelected(){

  }

  getAllClasses(){
    this.classroomService.getAllClassrooms().subscribe(
      (data:any)=>{
        this.classes = data;
        this.classesData = data;
        console.log(data);
      },
      (error:any)=>{
        this.snack.open('getting classrooms failed', '', {
          duration: 1000
        });
      }
    );
  }

  getCategories(){
    this.classroomService.getCategories().subscribe(
      (data:any)=>{
        for(var x of data){
          this.categoryNames.push(x.name);
          this.categoriesCheckboxMap.push({category:x.name,checked:false});
        }
      },
      (error:any)=>{
        this.snack.open('getting categories failed', '', {
          duration: 1000
        });
      }
    );
  }

  openEnrolForm(_classroom:any){
    this.dialog.open(EnrolFormComponent,{
      data: {classroom: _classroom, student: this.student},
    });
  }

  isCategoryChecked(categoryName:any):any  {
    
    for(let i of this.categoriesCheckboxMap) {
      if(categoryName === i.category && i.checked === true) {
        return true;
      }
    }

    return false;

  }

  function(){

    this.classesData = [];
    var changed = false;
    
    for(let classroom of this.classes) {

      for(let category of classroom.categories) {

          if(this.isCategoryChecked(category.name)){
            changed = true;
            this.classesData.push(classroom);
          }        

      }

    }

    if(!changed) {
      for(let classroom of this.classes) {

        this.classesData.push(classroom);
  
      }
    }

    console.log(this.classesData);

  }

  pageIndex:number = 0;
    pageSize:number = 10;
    lowValue:number = 0;
    highValue:number = this.pageSize;       

  getPaginatorData(event:any){
     this.pageSize = event.pageSize;
     if(event.pageIndex === this.pageIndex + 1){
        this.lowValue = this.lowValue + this.pageSize;
        this.highValue =  this.lowValue + this.pageSize;
       }
    else if(event.pageIndex === this.pageIndex - 1){
       this.lowValue = this.lowValue - this.pageSize;
       this.highValue =  this.highValue - this.pageSize;
      }   
       this.pageIndex = event.pageIndex;
 }

  // constructor() {
  // }
  // ngOnInit(): void {
  //   throw new Error('Method not implemented.');
  // }

  // localNewspapers = ["helllo", "kuss"];

  // lowValue: number = 0;
  // highValue: number = 20;

  // // used to build a slice of papers relevant at any given time
  // public getPaginatorData(event: PageEvent): PageEvent {
  //   this.lowValue = event.pageIndex * event.pageSize;
  //   this.highValue = this.lowValue + event.pageSize;
  //   return event;
  // }
}

