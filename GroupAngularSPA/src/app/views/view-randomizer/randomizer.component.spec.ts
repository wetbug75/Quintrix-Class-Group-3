import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AuthenticationService } from 'src/app/core/services/Authentication/authentication.service';
import { JokeService } from 'src/app/core/services/joke.service';

import { RandomizerComponent } from './randomizer.component';

describe('RandomizerComponent', () => {
  let component: RandomizerComponent;
  let fixture: ComponentFixture<RandomizerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ RandomizerComponent ],
      providers: [ JokeService, AuthenticationService]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RandomizerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});


