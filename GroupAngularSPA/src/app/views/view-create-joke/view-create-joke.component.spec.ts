import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { JokeItemComponent } from '../view-randomizer/components/joke-item/joke-item.component';
import { ViewCreateJokeComponent } from './view-create-joke.component';

describe('ViewCreateJokeComponent', () => {
  let component: ViewCreateJokeComponent;
  let fixture: ComponentFixture<ViewCreateJokeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [ ViewCreateJokeComponent],
      providers: [JokeItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewCreateJokeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
