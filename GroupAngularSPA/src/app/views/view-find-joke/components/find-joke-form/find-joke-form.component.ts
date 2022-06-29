import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { JokeService } from 'src/app/core/services/joke.service';

@Component({
  selector: 'app-find-joke-form',
  templateUrl: './find-joke-form.component.html',
  styleUrls: ['./find-joke-form.component.css']
})
export class FindJokeFormComponent implements OnInit {
  @Output() keyWord = new EventEmitter<string>();
  redmessage: string;
  inputKeyword: string;
  showError: boolean;
  constructor() {
    this.inputKeyword = ""
    this.redmessage = "One word or No input"
    this.showError = false;
    document.addEventListener('keydown', (e)=>{
      if(e.key=='Enter') {
        this.onSubmit();
      }
     });
  }

  ngOnInit(): void { }

  onSubmit() {
    //valid for empty
    if(this.inputKeyword== "") {
      this.showError = false;
      this.keyWord.emit(this.inputKeyword);
    } else {
        let checkSentence =  this.inputKeyword.trim().split(" ");

        if(checkSentence.length == 1) {
          this.showError = false;
          this.keyWord.emit(this.inputKeyword.trim());
        } else {
          this.showError = true;
        }
    }
  }
}

