import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgxPaginationModule } from 'ngx-pagination';
import { FormsModule } from '@angular/forms';
import { RouterTestingModule } from "@angular/router/testing";


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
import { FindJokeFormComponent } from './views/view-find-joke/components/find-joke-form/find-joke-form.component';
import { LoginFormComponent } from './views/view-login/components/login-form/login-form.component';
import { RatingsComponent } from './shared/components/ratings/ratings.component';
import { JokesPaginateComponent } from './views/view-find-joke/components/jokes-paginate/jokes-paginate.component';
import { RegisterFormComponent } from './views/view-login/components/register-form/register-form.component';
import { RegisterBtnComponent } from './views/view-login/components/register-btn/register-btn.component';

import { HttpInterceptorService } from './core/services/Interceptor/HttpInterceptorService';
import { LoadingService } from './core/services/Loading/loading.service';
import { LoadingInterceptor } from './core/services/Interceptor/LoadingInterceptorService';
import { FooterComponent } from './shared/components/footer/footer.component';
import { LoadingComponent } from './shared/components/loading/loading.component';
import { RedMessageComponent } from './shared/components/red-message/red-message.component';
import { AuthenticationService } from './core/services/Authentication/authentication.service';
/*Will come back!*/
const appRoutes: Routes = [
 {path: '', component:RandomizerComponent},
 {path: 'create', component:ViewCreateJokeComponent},
 {path: 'find', component: ViewFindJokeComponent},
 {path: 'login', component: ViewLoginComponent}
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
    FindJokeFormComponent,
    LoginFormComponent,
    RatingsComponent,
    JokesPaginateComponent,
    RegisterFormComponent,
    RegisterBtnComponent,
    FooterComponent,
    LoadingComponent,
    RedMessageComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    ReactiveFormsModule,
    RouterTestingModule,
    NgxPaginationModule,
    FormsModule,
    
    RouterModule.forRoot(appRoutes, {enableTracing: false})


  ],


  providers: [
     LoadingService,
    AuthenticationService,
  { 

    provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
  },
  { 

    provide: HTTP_INTERCEPTORS,
      useClass: LoadingInterceptor,
      multi: true
  }
],

  bootstrap: [AppComponent, FooterComponent]
})
export class AppModule {


}


