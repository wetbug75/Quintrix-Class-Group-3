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

    this.jokeService.postJoke(newJokeForm.value).subscribe((response)=>{
      console.log(response);
    })
  }
}
