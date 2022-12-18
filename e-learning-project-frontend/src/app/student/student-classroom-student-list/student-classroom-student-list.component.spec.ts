import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentClassroomStudentListComponent } from './student-classroom-student-list.component';

describe('StudentClassroomStudentListComponent', () => {
  let component: StudentClassroomStudentListComponent;
  let fixture: ComponentFixture<StudentClassroomStudentListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentClassroomStudentListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentClassroomStudentListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
