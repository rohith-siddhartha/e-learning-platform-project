import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateClassoomComponent } from './create-classoom.component';

describe('CreateClassoomComponent', () => {
  let component: CreateClassoomComponent;
  let fixture: ComponentFixture<CreateClassoomComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateClassoomComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateClassoomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
