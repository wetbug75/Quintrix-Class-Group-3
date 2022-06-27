import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RedMessageComponent } from './red-message.component';

describe('RedMessageComponent', () => {
  let component: RedMessageComponent;
  let fixture: ComponentFixture<RedMessageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RedMessageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RedMessageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
