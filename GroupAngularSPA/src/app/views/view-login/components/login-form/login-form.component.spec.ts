import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Users } from 'src/app/models/User';

import { LoginFormComponent } from './login-form.component';

describe('LoginFormComponent', () => {
  let component: LoginFormComponent;
  let fixture: ComponentFixture<LoginFormComponent>;
  //fixture provides methods and properties that help test functionality of component


  beforeEach(async () => {
    //this beforeeach, sets up the environment for testing
    await TestBed.configureTestingModule({
      declarations: [ LoginFormComponent ],
      imports:[ReactiveFormsModule, FormsModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    //this creates an instance of component
    fixture = TestBed.createComponent(LoginFormComponent);
    component = fixture.componentInstance;
    component.ngOnInit(); //force the ngOnInit
    fixture.detectChanges(); //ensure data is up to date
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('form invalid when empty', ()=>{
    //checking empty form object
    expect(component.userLoginForm.valid).toBeFalsy();
  })

  it('password field validity',()=>{
    let password = component.userLoginForm.controls['password'];
    expect(password.valid).toBeFalsy(); //empty field - should fail test

    let errors = {};
    errors = password.errors || {};
    expect(errors['required']).toBeTruthy(); //test empty password field

    password.setValue("test");
    errors = password.errors || {};
    expect(password.valid).toBeTruthy(); //test filled out password field
    expect(errors['required']).toBeUndefined; //test required , check error . should be undefined

  })

  it('submitting a form emits a User object containing username and password',()=>{
    expect(component.userLoginForm.valid).toBeFalsy();
    component.userLoginForm.controls['userName'].setValue("simon");
    component.userLoginForm.controls['password'].setValue("kevin");
    expect(component.userLoginForm.valid).toBeTruthy();
    let user: Users;
    component.onUserFormGroupSubmit.subscribe((submittedFormData)=> user = submittedFormData);

    component.onSubmit();
    expect(user.username).toBe("simon");
    expect(user.password).toBe("kevin");
    
  })

  it('submit a login form with empty username field and empty login field',()=>{
    let passwordfield = component.userLoginForm.controls['password'];
    let usernamefield = component.userLoginForm.controls['userName'];

    expect(component.userLoginForm.valid).toBeFalsy();
    component.onSubmit();
    let user: Users;
    console.log(user);
    component.onUserFormGroupSubmit.subscribe((submittedFormData)=> user = submittedFormData);
    expect(user).toBeUndefined(); //check if there is anything in form 
    expect(component.submitted).toBeTruthy(); //check if it was submitted
    expect(usernamefield.valid).toBeFalsy(); //check validity of field
    expect(passwordfield.valid).toBeFalsy(); //check validity of field
    
  })
 
});
