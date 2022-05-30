import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-view-create-joke',
  templateUrl: './view-create-joke.component.html',
  styleUrls: ['./view-create-joke.component.css']
})
export class ViewCreateJokeComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  onSubmitCreateJoke(formControlObj : FormGroup){
    console.log(formControlObj);
    
  }
}
