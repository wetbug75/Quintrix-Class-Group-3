import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JokeboxComponent } from './jokebox.component';

describe('JokeboxComponent', () => {
  let component: JokeboxComponent;
  let fixture: ComponentFixture<JokeboxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JokeboxComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(JokeboxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
