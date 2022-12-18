import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InstructorClassroomComponent } from './instructor-classroom.component';

describe('InstructorClassroomComponent', () => {
  let component: InstructorClassroomComponent;
  let fixture: ComponentFixture<InstructorClassroomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InstructorClassroomComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(InstructorClassroomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
