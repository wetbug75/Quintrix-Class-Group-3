import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ViewCreateJokeComponent } from './view-create-joke.component';

describe('ViewCreateJokeComponent', () => {
  let component: ViewCreateJokeComponent;
  let fixture: ComponentFixture<ViewCreateJokeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewCreateJokeComponent ]
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
