import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ButtonComponent } from './components/button/button.component';
import { JokeboxComponent } from './components/jokebox/jokebox.component';

@NgModule({
  declarations: [
    AppComponent,
    ButtonComponent,
    JokeboxComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
	

}


