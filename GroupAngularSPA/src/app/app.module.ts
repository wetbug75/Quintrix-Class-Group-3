import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ButtonComponent } from './components/button/button.component';
import { JokeboxComponent } from './components/jokebox/jokebox.component';
import { JokeQuestionComponent } from './components/joke-question/joke-question.component';
import { JokeAnswerComponent } from './components/joke-answer/joke-answer.component';

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    JokeboxComponent,
    JokeQuestionComponent,
    JokeAnswerComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
	

}


