import { TestBed } from '@angular/core/testing';

import { CreateStateServiceService } from './create-state-service.service';

describe('CreateStateServiceService', () => {
  let service: CreateStateServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CreateStateServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
