import { HttpClientModule } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';

import { JokePostService } from './joke-post.service';

describe('JokePostService', () => {
  let service: JokePostService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientModule],
      providers: [JokeItemComponent]
    });
    service = TestBed.inject(JokePostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
