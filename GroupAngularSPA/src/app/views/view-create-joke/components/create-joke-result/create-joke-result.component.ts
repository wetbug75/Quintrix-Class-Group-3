import { Component, EventEmitter, Input, OnChanges, OnInit, Output, SimpleChanges } from '@angular/core';
import { CreateStateServiceService } from 'src/app/views/view-create-joke/services/create-state-service.service';
import { Status } from 'src/app/shared/status';

@Component({
  selector: 'app-create-joke-result',
  templateUrl: './create-joke-result.component.html',
  styleUrls: ['./create-joke-result.component.css']
})
export class CreateJokeResultComponent implements OnChanges {
  statusResult!: String;
  @Input() backEndResponseStatus= Status.None;
  constructor(public createForm: CreateStateServiceService) { }

  ngOnChanges(changes: SimpleChanges): void {
    if(this.backEndResponseStatus.valueOf() === Status.Success){
      this.statusResult="Congrats! The new Joke was added to our vault of jokes";
      console.log("response")
      console.log(this.backEndResponseStatus);
    }else if(this.backEndResponseStatus.valueOf()===Status.Fail){
      this.statusResult="Unfortunately, the joke was not added. There was an error. Try again next time";
    }

    
  }

 
    onSubmit(){
       this.createForm.state = true;
    }
  

}
