import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { CreateStateServiceService } from 'src/app/views/view-create-joke/services/create-state-service.service';
import { Status } from 'src/app/models/status';
@Component({
  selector: 'app-create-joke-result',
  templateUrl: './create-joke-result.component.html',
  styleUrls: ['./create-joke-result.component.css']
})
export class CreateJokeResultComponent implements OnChanges {
  dogeImage: boolean;
  happyDogeImage:boolean;
  statusResult!: String;
  @Input() backEndResponseStatus= Status.None;
  constructor(public createForm: CreateStateServiceService) {
    this.dogeImage=false;
    this.happyDogeImage = false;
   }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.backEndResponseStatus.valueOf() === Status.Success) {
        this.statusResult="Congrats! The new Joke was added to our vault of jokes";
        this.happyDogeImage = true;
    } else if(this.backEndResponseStatus.valueOf()===Status.Fail) {
        this.statusResult="Unfortunately, the joke was not added. There was an error. Try again next time";
    } else if(this.backEndResponseStatus.valueOf() === Status.IsNotLoggedIn) {
        this.statusResult="You're not logged in! Please log in to create a new jokes";
        this.dogeImage = true;
    }
  }

  onSubmit(){
    this.createForm.state = true;
  }
}
