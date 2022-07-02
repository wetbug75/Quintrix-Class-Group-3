import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { ViewLoginComponent } from './view-login.component';

describe('ViewLoginComponent', () => {
  let component: ViewLoginComponent;
  let fixture: ComponentFixture<ViewLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewLoginComponent ],
      imports: [HttpClientModule, RouterTestingModule]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
