import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JokesPaginateComponent } from './jokes-paginate.component';

describe('JokesPaginateComponent', () => {
  let component: JokesPaginateComponent;
  let fixture: ComponentFixture<JokesPaginateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JokesPaginateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JokesPaginateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
