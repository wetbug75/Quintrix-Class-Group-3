import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { ButtonComponent } from './components/button/button.component';
import { JokeComponent } from './components/joke/joke.component';
import { JokeItemComponent } from './components/joke-item/joke-item.component';
import { RandomizerComponent } from './views/view-randomizer/components/randomizer/randomizer.component';

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
    RandomizerComponent
  ],
  imports: [
    BrowserModule, RouterModule.forRoot(appRoutes, {enableTracing: true})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
	

}


