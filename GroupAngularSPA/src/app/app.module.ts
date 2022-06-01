import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { ButtonComponent } from './views/view-randomizer/components/button/button.component';
import { JokeComponent } from './views/view-randomizer/components/joke/joke.component';
import { JokeItemComponent } from './views/view-randomizer/components/joke-item/joke-item.component';
import { RandomizerComponent } from './views/view-randomizer/randomizer.component';
import { ViewCreateJokeComponent } from './views/view-create-joke/view-create-joke.component';
import { CreateJokeFormComponent } from './views/view-create-joke/components/create-joke-form/create-joke-form.component';
import { CreateJokeResultComponent } from './views/view-create-joke/components/create-joke-result/create-joke-result.component';
import { NavbarComponent } from './shared/components/navbar/navbar.component';

/*Will come back!*/
const appRoutes: Routes = [
 {path: '', component:RandomizerComponent}
]

@NgModule({
  declarations: [
    AppComponent,

    ButtonComponent,
    JokeComponent,
    JokeItemComponent,
    RandomizerComponent,
       ViewCreateJokeComponent,
    CreateJokeFormComponent,
    CreateJokeResultComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule, 
       ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, {enableTracing: true})


  ],

  providers: [JokeItemComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
	

}


