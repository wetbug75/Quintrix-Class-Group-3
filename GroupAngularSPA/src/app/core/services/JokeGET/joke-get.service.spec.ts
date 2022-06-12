import { TestBed } from '@angular/core/testing';

import { JokeGetService } from './joke-get.service';

describe('JokeGetService', () => {
  let service: JokeGetService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JokeGetService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
