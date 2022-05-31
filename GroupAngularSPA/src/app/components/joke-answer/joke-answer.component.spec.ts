import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JokeAnswerComponent } from './joke-answer.component';

describe('JokeAnswerComponent', () => {
  let component: JokeAnswerComponent;
  let fixture: ComponentFixture<JokeAnswerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JokeAnswerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JokeAnswerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
