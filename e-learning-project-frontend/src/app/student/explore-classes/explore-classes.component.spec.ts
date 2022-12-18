import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExploreClassesComponent } from './explore-classes.component';

describe('ExploreClassesComponent', () => {
  let component: ExploreClassesComponent;
  let fixture: ComponentFixture<ExploreClassesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ExploreClassesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExploreClassesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
