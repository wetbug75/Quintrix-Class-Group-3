import { HttpClient } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, ComponentFixtureAutoDetect, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Joke } from 'src/app/models/Joke';
import { JokesPaginateComponent } from '../jokes-paginate/jokes-paginate.component';

import { FindJokeFormComponent } from './find-joke-form.component';

describe('FindJokeFormComponent', () => {
  let component: FindJokeFormComponent;
  let fixture: ComponentFixture<FindJokeFormComponent>;
  let component2: JokesPaginateComponent;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ReactiveFormsModule, FormsModule],
      declarations: [ FindJokeFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindJokeFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('form invalid when empty', () => {
    expect(component.inputKeyword).toBeFalsy();
  });

  it('searching a keyword inputKeyword should equal keyword', () => {
    const input = 
    fixture.debugElement.query(By.css('input[name="inputKeyword"]'));
    const keyword = input.nativeElement;
    keyword.value = 'Updated';
    keyword.dispatchEvent(new Event('input'));
    expect(component.inputKeyword).toEqual('Updated');
  })

  //test button functionality 
    //if field is empty keyword is undefined
    it('submitting with an empty keyword field', () => {
      let keyword: String;

      component.onSubmit();
      component.keyWord.subscribe((value) => keyword = value);
      
      expect(keyword).toBeUndefined();
    })
    //if keyword is not empty emits a keyword
    it('submitted keyword properly', () => {
      component.inputKeyword = "programmer";

      let keyword: String;

      component.keyWord.subscribe((value) => keyword = value);
      component.onSubmit();
      
      expect(keyword).toEqual("programmer");
    })
    //if keyword is more than one word, show error
    it('submitted keyword with multiple words', () => {
      component.inputKeyword = "the programmer";

      let keyword: String;

      component.keyWord.subscribe((value) => keyword = value);
      component.onSubmit();

      expect(component.showError).toBeTruthy();
    }) 

  
  
  //i think this would be a test for jokes paginate
  /*it('searching a keyword a list should be returned', () => {
    const input = 
    fixture.debugElement.query(By.css('input[name="inputKeyword"]'));
    const keyword = input.nativeElement;
    keyword.value = "programmer";
    keyword.dispatchEvent(new Event('input'));

    let joke: Joke[];

    component.
  })*/

});


