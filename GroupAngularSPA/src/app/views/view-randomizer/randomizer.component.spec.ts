import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { JokeItemComponent } from './components/joke-item/joke-item.component';

import { RandomizerComponent } from './randomizer.component';

describe('RandomizerComponent', () => {
  let component: RandomizerComponent;
  let fixture: ComponentFixture<RandomizerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ RandomizerComponent ],
      providers: [JokeItemComponent]
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
