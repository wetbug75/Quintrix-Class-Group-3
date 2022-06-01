import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JokeItemComponent } from './joke-item.component';

describe('JokeItemComponent', () => {
  let component: JokeItemComponent;
  let fixture: ComponentFixture<JokeItemComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JokeItemComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JokeItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
