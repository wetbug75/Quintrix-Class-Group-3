import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateJokeFormComponent } from './create-joke-form.component';

describe('CreateJokeFormComponent', () => {
  let component: CreateJokeFormComponent;
  let fixture: ComponentFixture<CreateJokeFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateJokeFormComponent ]
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
  it('should save form data in newJoke object and emit joke', ()=>{
      const fixture = TestBed.createComponent(CreateJokeFormComponent);
      const component = fixture.componentInstance;
      const spy = spyOnProperty(newJoke, 'answer').returnValue('sup');
      component.onSubmit();
  })
});