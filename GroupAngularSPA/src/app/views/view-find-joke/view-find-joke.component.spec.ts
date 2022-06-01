import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewFindJokeComponent } from './view-find-joke.component';

describe('ViewFindJokeComponent', () => {
  let component: ViewFindJokeComponent;
  let fixture: ComponentFixture<ViewFindJokeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewFindJokeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewFindJokeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
