import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { Users } from 'src/app/models/User';

import { UserPostService } from './user-post.service';

describe('UserPostService', () => {
  //allows for mocking and flushign of requests
  let httpTestingController: HttpTestingController;
  let service: UserPostService;

  beforeEach(() => { 
    //create instance
    TestBed.configureTestingModule({ //instance of test
      imports: [ HttpClientModule, HttpClientTestingModule] //provides mock connection
    
    });
    httpTestingController = TestBed.get(HttpTestingController);

    service = TestBed.inject(UserPostService);
  });

  afterEach(()=>{
    httpTestingController.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('#postUser should return ok status when post user', (done)=>{
    const testUser: Users ={
      username: 'test',
      password: 'test'
    }

    service.registerUser(testUser).subscribe(data=>{
      expect(data).toEqual({status: 200});
      done();
    })
    const testRequest = httpTestingController.expectOne(`${service.springUrl}/newUser`);
    testRequest.flush({status: 200});
  })

  it('#postUser should be POST request',(done)=>{
    const testUser: Users ={
      username: 'test',
      password: 'test'
    }

    service.registerUser(testUser).subscribe(data=>{
      done();
    })
    const testRequest = httpTestingController.expectOne(`${service.springUrl}/newUser`);
    testRequest.flush({ status: 200});
    expect(testRequest.request.method).toBe('POST');

  })
});
