import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { RatingsComponent } from 'src/app/shared/components/ratings/ratings.component';

import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';

import { JokeService } from './joke.service';
import { JokeGetService } from './JokeGET/joke-get.service';
import { JokePostService } from './JokePOST/joke-post.service';

describe('JokeService', () => {
  let httpClient : HttpClient;
  let httpTestingController : HttpTestingController;
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  }
  let service = JokeService
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule], //provides fake implemntation of HTTP client
      //it doesn't actually send out http request, it merely intercepts them and records them
      //internally.
      providers: [JokeItemComponent, JokeGetService, JokePostService, RatingsComponent],
      declarations: []
    });
    // inject the http service and test controller for each test


  });
  it('should create', () => {
    expect(service).toBeTruthy();
    
  });


});
