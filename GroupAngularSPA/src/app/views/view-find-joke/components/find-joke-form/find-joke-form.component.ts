import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { JokeService } from 'src/app/core/services/joke.service';

@Component({
  selector: 'app-find-joke-form',
  templateUrl: './find-joke-form.component.html',
  styleUrls: ['./find-joke-form.component.css']
})
export class FindJokeFormComponent implements OnInit {
  @Output() keyWord = new EventEmitter<string>();
  inputKeyword: string;
  constructor(private jokeService: JokeService) { 
    document.addEventListener('keydown', (e)=>{
      if(e.key=='Enter'){
        this.onSubmit();
      }
     });
  }

  ngOnInit(): void {
    
  }
  onSubmit(){
    this.keyWord.emit(this.inputKeyword);

    
     //this.jokeService.getJokeByKeyword(this.inputKeyword).subscribe(
       //Response => {
         //console.log(Response);
       //});



  }
  

}

