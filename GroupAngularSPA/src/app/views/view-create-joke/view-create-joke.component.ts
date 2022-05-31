import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { JokeDataService } from 'src/app/core/services/joke-data.service';
@Component({
  selector: 'app-view-create-joke',
  templateUrl: './view-create-joke.component.html',
  styleUrls: ['./view-create-joke.component.css']
})
export class ViewCreateJokeComponent implements OnInit {
 
  constructor(private jokeService : JokeDataService) { }

  ngOnInit(): void {
  }

  onSubmitCreateJoke(newJokeForm : FormGroup){
    //we can use a model here to send to backend
     //TODO
     
    console.log(newJokeForm.value);

    //temporary code because the json server won't accept anything that doesn't have an id
    const newJoke = {
      answer : newJokeForm.value.answer,
      question: newJokeForm.value.question,
      createdBy: newJokeForm.value.createdBy,
      id: Math.random()
    }
    //above is place holder code. 
   
    this.jokeService.postJoke(newJoke).subscribe((response)=>{
      console.log(response);
    })
  }
}
