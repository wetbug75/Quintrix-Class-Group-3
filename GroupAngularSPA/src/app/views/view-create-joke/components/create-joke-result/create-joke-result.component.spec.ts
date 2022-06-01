import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateJokeResultComponent } from './create-joke-result.component';

describe('CreateJokeResultComponent', () => {
  let component: CreateJokeResultComponent;
  let fixture: ComponentFixture<CreateJokeResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateJokeResultComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateJokeResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
