import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';

import { UserPostService } from './user-post.service';

describe('UserPostService', () => {
  let service: UserPostService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientModule]
    });
    service = TestBed.inject(UserPostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
