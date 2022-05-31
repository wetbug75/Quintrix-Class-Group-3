import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JokeQuestionComponent } from './joke-question.component';

describe('JokeQuestionComponent', () => {
  let component: JokeQuestionComponent;
  let fixture: ComponentFixture<JokeQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JokeQuestionComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JokeQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
