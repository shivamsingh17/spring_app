import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { AuthenticationService } from 'src/app/Services/authentication.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isLoggedInVal:boolean|any;
  public profile: Customer|any;
  constructor(private authenticationService:AuthenticationService) {
    this.isLoggedInVal=this.authenticationService.isUserLoggedIn;
    if(this.isLoggedInVal)this.profile=this.authenticationService.currentCustomerValue;
   }

  ngOnInit(): void {
    this.authenticationService.isLoggedIn.subscribe((data) => {
      this.isLoggedInVal = data;
      if(this.isLoggedInVal)this.profile=this.authenticationService.currentCustomerValue;
    });
  }

}
