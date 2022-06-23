import { Component, HostBinding, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/core/services/Authentication/authentication.service';
import { AppComponent } from 'src/app/app.component';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  isLoggedIn: boolean;
  subscription: Subscription = new Subscription;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
    private appComponent: AppComponent) { 
      
    }

  ngOnInit(): void {
    this.subscription = this.authenticationService.isLoggedIn.subscribe((result)=>{
      this.isLoggedIn = result;
    })
  }
  handleLogout() {
   this.authenticationService.logout();
  }

  defaultClick() {
    this.appComponent.setCssDefault();
  }
  darkClick() {
    this.appComponent.setCssDark();
  }
  artsyClick() {
    this.appComponent.setCssArtsy();
  }
  bluePurpleClick() {
    this.appComponent.setCssBP();
  }
}
