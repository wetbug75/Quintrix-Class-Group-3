import { TestBed } from '@angular/core/testing';

import { JokePostService } from './joke-post.service';

describe('JokePostService', () => {
  let service: JokePostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JokePostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
