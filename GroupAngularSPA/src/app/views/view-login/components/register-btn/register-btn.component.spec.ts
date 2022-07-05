import { ComponentFixture, fakeAsync, TestBed, tick } from '@angular/core/testing';

import { RegisterBtnComponent } from './register-btn.component';

describe('RegisterBtnComponent', () => {
  //https://braydoncoyer.dev/blog/learn-how-to-click-a-button-when-angular-unit-testing

  let component: RegisterBtnComponent;
  let fixture: ComponentFixture<RegisterBtnComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegisterBtnComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegisterBtnComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should call #onRegisterSubmit when clicked', fakeAsync(()=>{
    spyOn(component, 'onRegisterSubmit');
    let button = fixture.debugElement.nativeElement.querySelector('button');
    button.click();
    tick();
    expect(component.onRegisterSubmit).toHaveBeenCalled();
  }));
 
  it('should emit the event when #onRegisterSubmit is called',()=>{
    const emitSpy = spyOn(component.onUserWantsNewAccount, 'emit');

    component.onRegisterSubmit();

    expect(emitSpy).toHaveBeenCalled();
  })

  it('should emit true when #onRegisterSubmit is cicked',()=>{
    spyOn(component.onUserWantsNewAccount, 'emit');
    component.onRegisterSubmit();
    expect(component.onUserWantsNewAccount.emit).toHaveBeenCalledWith(true);
  });
});
