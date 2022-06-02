import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormBuilder, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { newJoke } from 'src/app/models/newJoke';
import { CreateJokeFormComponent } from './create-joke-form.component';

describe('CreateJokeFormComponent', () => {
  let component: CreateJokeFormComponent;
  let fixture: ComponentFixture<CreateJokeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormsModule, ReactiveFormsModule],
      declarations: [ CreateJokeFormComponent ],
      providers: [FormBuilder]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateJokeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

describe('onSubmit', ()=>{
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      providers: [FormBuilder]
    })
    .compileComponents();
  });
  it('should save form data in newJoke object and emit joke', ()=>{
      const fixture = TestBed.createComponent(CreateJokeFormComponent);
      const component = fixture.componentInstance;
     
      let newJoker = new newJoke("HELLO", "NO");
    //  expect(component.newJokeForm.value.answer).toBe(newJoker._answer);
     // expect(component.newJokeForm.value.question).toBe(newJoker._question);

  })
});