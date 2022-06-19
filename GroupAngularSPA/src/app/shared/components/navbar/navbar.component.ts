import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { AuthenticationService } from 'src/app/core/services/Authentication/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedIn;
  subscription: Subscription = new Subscription;
  constructor(private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService) { 
      
    }

  ngOnInit(): void {
    this.subscription = this.authenticationService.isLoggedIn.subscribe((result)=>{
      this.isLoggedIn = result;
    })
  }
  handleLogout() {
   this.authenticationService.logout();
  }
}
