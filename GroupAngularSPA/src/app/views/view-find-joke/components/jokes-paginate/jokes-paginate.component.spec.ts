import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NgxPaginationModule } from 'ngx-pagination';
import { LoadingService } from 'src/app/core/services/Loading/loading.service';

import { JokesPaginateComponent } from './jokes-paginate.component';

describe('JokesPaginateComponent', () => {
  let component: JokesPaginateComponent;
  let fixture: ComponentFixture<JokesPaginateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ JokesPaginateComponent ],
      imports: [HttpClientModule, NgxPaginationModule],
      providers: [LoadingService]
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
