import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewRegisterUserComponent } from './view-register-user.component';

describe('ViewRegisterUserComponent', () => {
  let component: ViewRegisterUserComponent;
  let fixture: ComponentFixture<ViewRegisterUserComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewRegisterUserComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewRegisterUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
