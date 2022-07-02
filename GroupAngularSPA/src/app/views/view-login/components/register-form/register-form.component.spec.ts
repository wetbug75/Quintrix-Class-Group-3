import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';

import { RegisterFormComponent } from './register-form.component';

describe('RegisterFormComponent', () => {
  let component: RegisterFormComponent;
  let fixture: ComponentFixture<RegisterFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule],
      declarations: [ RegisterFormComponent ],
      providers: [FormBuilder]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('form invalid when empty', () => {
    expect(component.userRegisterForm.valid).toBeFalsy();
  })

  it('email field validity', () => {
    let email = component.userRegisterForm.controls['email'];
    expect(email.valid).toBeFalsy();

    let errors = {};
    errors = email.errors || {};
    expect(errors['required']).toBeTruthy();
  })

  it('username field validity', () => {
    let username = component.userRegisterForm.controls['username'];
    expect(username.valid).toBeFalsy();

    let errors = {};
    errors = username.errors || {};
    expect(errors['required']).toBeTruthy();
  })

  it('password field validity', () => {
    let password = component.userRegisterForm.controls['password'];
    expect(password.valid).toBeFalsy();

    let errors = {};
    errors = password.errors || {};
    expect(errors['required']).toBeTruthy();
  })

  it('submitting a form emits a user', () => {
    expect(component.userRegisterForm.valid).toBeFalsy();
    component.userRegisterForm.controls['email'].setValue("test@test.com");
    component.userRegisterForm.controls['username'].setValue("angularTestJasmine");
    component.userRegisterForm.controls['password'].setValue("1234567890");
  })
});
