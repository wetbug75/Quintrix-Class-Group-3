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
import { ViewFindJokeComponent } from './views/view-find-joke/view-find-joke.component';
import { ViewLoginComponent } from './views/view-login/view-login.component';
import { ViewRegisterUserComponent } from './views/view-register-user/view-register-user.component';
import { FindJokeFormComponent } from './views/view-find-joke/components/find-joke-form/find-joke-form.component';
import { LoginFormComponent } from './views/view-login/components/login-form/login-form.component';
import { JokeListComponent } from './views/view-find-joke/components/joke-list/joke-list.component';
import { FindAllJokesButtonComponent } from './views/view-find-joke/components/find-all-jokes-button/find-all-jokes-button.component';

/*Will come back!*/
const appRoutes: Routes = [
 {path: '', component:RandomizerComponent},
 {path: 'create', component:ViewCreateJokeComponent},
 {path: 'find', component: ViewFindJokeComponent},
 {path: 'login', component: ViewLoginComponent},
 {path: 'register', component: ViewRegisterUserComponent}
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
    NavbarComponent,
    ViewFindJokeComponent,
    ViewLoginComponent,
    ViewRegisterUserComponent,
    FindJokeFormComponent,
    LoginFormComponent,
    JokeListComponent,
    FindAllJokesButtonComponent
  ],
  imports: [
    BrowserModule, 
       ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forRoot(appRoutes, {enableTracing: false})


  ],

  providers: [JokeItemComponent],
  bootstrap: [AppComponent]
})
export class AppModule {
	

}


