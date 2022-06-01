import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ViewCreateJokeComponent } from './views/view-create-joke/view-create-joke.component';
import { CreateJokeFormComponent } from './views/view-create-joke/components/create-joke-form/create-joke-form.component';
import { CreateJokeResultComponent } from './views/view-create-joke/components/create-joke-result/create-joke-result.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewCreateJokeComponent,
    CreateJokeFormComponent,
    CreateJokeResultComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
