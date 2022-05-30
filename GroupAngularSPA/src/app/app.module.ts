import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { ViewCreateJokeComponent } from './views/view-create-joke/view-create-joke.component';
import { CreateJokeFormComponent } from './views/view-create-joke/components/create-joke-form/create-joke-form.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewCreateJokeComponent,
    CreateJokeFormComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
