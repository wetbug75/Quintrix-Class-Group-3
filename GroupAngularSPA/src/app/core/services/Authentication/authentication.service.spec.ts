import { TestBed, inject  } from "@angular/core/testing";
import { AuthenticationService } from "./authentication.service";
import {HttpClientTestingModule} from '@angular/common/http/testing';
import { JokeService } from "../joke.service";

describe('Authentication Service', ()=>{
    let service: AuthenticationService
    beforeEach(()=>{
        TestBed.configureTestingModule({
            
            imports: [HttpClientTestingModule],
            providers: [ JokeService]
        });
        service = TestBed.inject(AuthenticationService)
    }); // beforeEach
    it('should be created', () => {
        expect(service).toBeTruthy();

      });
     
    });