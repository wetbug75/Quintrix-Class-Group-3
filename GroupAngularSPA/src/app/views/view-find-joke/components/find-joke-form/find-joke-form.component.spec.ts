import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindJokeFormComponent } from './find-joke-form.component';

describe('FindJokeFormComponent', () => {
  let component: FindJokeFormComponent;
  let fixture: ComponentFixture<FindJokeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindJokeFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindJokeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
