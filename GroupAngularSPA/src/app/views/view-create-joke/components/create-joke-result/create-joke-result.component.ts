import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { Status } from 'src/app/shared/status';

@Component({
  selector: 'app-create-joke-result',
  templateUrl: './create-joke-result.component.html',
  styleUrls: ['./create-joke-result.component.css']
})
export class CreateJokeResultComponent implements OnChanges {
  statusResult!: String;
  @Input() backendResponse!: Status;
  constructor() { }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.backendResponse.valueOf() === Status.Success){
      this.statusResult="Congrats! The new Joke was added to our vault of jokes";
    }else if(this.backendResponse.valueOf()===Status.Fail){
      this.statusResult="Unfortunately, the joke was not added. There was an error. Try again next time";
    }
  }

 

  

}
