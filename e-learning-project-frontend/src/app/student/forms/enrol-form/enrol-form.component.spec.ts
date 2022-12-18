import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EnrolFormComponent } from './enrol-form.component';

describe('EnrolFormComponent', () => {
  let component: EnrolFormComponent;
  let fixture: ComponentFixture<EnrolFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EnrolFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EnrolFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
