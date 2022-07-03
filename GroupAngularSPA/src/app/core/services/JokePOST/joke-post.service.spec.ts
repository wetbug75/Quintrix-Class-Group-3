import { HttpClientModule } from '@angular/common/http';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';
import { JokeItemComponent } from 'src/app/views/view-randomizer/components/joke-item/joke-item.component';
import { Joke } from 'src/app/models/Joke';
import { JokePostService } from './joke-post.service';

describe('JokePostService', () => {
  let httpTestingController: HttpTestingController;
  let service: JokePostService;

  let testJoke: Joke = {
    question: "This is a test?",
    answer: "A test",
    id: 1,
    upvotes: 100,
    downvotes: 50,
    created_by: "Bob",
    author_name: "Bob",
    author_id: 20
  };


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ HttpClientModule, HttpClientTestingModule ],
      providers: [JokeItemComponent]
    });
    httpTestingController = TestBed.get(HttpTestingController);
    service = TestBed.inject(JokePostService);
  });

  afterEach(() => {
    httpTestingController.verify();
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('#postJoke() should be able to post a joke to backend', () => {
    service.postJoke(testJoke).subscribe(data => {
      expect(data).toEqual(testJoke, 'should return created');
    });
    const httpStatus = httpTestingController.expectOne(`${service.springUrl}/newJoke`);
    expect(httpStatus.request.method).toBe('POST');
  });

  it('#updateLikeCount() should update upvote count on backend database', () => {
    service.updateLikeCount(testJoke, testJoke.id);
    const httpStatus = httpTestingController.expectOne(`${service.springUrl}/jokes/${testJoke.id}/update/upvote`);
    expect(httpStatus.request.method).toBe('PUT');
  });


  it('#updateDislikeCount() should update downvote count on backend database', () => {
    service.updateDislikeCount(testJoke, testJoke.id);
    const httpStatus2 = httpTestingController.expectOne(`${service.springUrl}/jokes/${testJoke.id}/update/downvote`);
    expect(httpStatus2.request.method).toBe('PUT');
  });


  it('#updateUserJokeVote() should update user information with related joke and upvote/downvote', () => {
    service.updateUserJokeVote(testJoke.author_id, testJoke.id, "DOWNVOTE");
    const httpStatus = httpTestingController.expectOne(`${service.springUrl}/voteStatus/update/${testJoke.author_id}/${testJoke.id}`);
    expect(httpStatus.request.method).toBe('PUT');
  });
});
