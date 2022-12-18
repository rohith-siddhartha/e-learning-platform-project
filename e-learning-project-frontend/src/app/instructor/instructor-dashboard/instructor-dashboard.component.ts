import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-instructor-dashboard',
  templateUrl: './instructor-dashboard.component.html',
  styleUrls: ['./instructor-dashboard.component.css']
})
export class InstructorDashboardComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  classes = ["class1","class2","class3","class4","class5","class1","class2","class3","class4","class5","class1","class2","class3","class4","class5","class1","class2","class3","class4","class5","class1","class2","class3","class4","class5"];

}
