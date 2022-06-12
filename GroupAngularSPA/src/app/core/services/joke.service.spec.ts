import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { doesNotReject } from 'assert';

import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';

import { JokeService } from './joke.service';

describe('JokeService', () => {
  let httpClient : HttpClient;
  let httpTestingController : HttpTestingController;
  const httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    })
  }
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports:[HttpClientTestingModule], //provides fake implemntation of HTTP client
      //it doesn't actually send out http request, it merely intercepts them and records them
      //internally.
      providers: [JokeItemComponent]
    });
    // inject the http service and test controller for each test


  });



});
