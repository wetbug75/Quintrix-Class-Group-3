import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindAllJokesButtonComponent } from './find-all-jokes-button.component';

describe('FindAllJokesButtonComponent', () => {
  let component: FindAllJokesButtonComponent;
  let fixture: ComponentFixture<FindAllJokesButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindAllJokesButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindAllJokesButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
