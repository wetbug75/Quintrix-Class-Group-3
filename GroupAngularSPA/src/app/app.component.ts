import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'GroupAngularSPA';
  
  	jokeQuestion = '';
  	//GET request
	jokeAnswer = '';

	getRandomJoke(){
		@ViewChild('jokeQuestion') jQuestion: ElementRef;
		this.jQuestion.nativeElement.innerHTML = '';
		
		@ViewChild('jokeAnswer') jAnswer: ElementRef;
		this.jAnswer.nativeElement.innerHTML = '';
	}
}
