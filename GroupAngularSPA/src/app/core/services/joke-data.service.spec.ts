import { TestBed } from '@angular/core/testing';

import { JokeDataService } from './joke-data.service';

describe('JokeDataService', () => {
  let service: JokeDataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(JokeDataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
