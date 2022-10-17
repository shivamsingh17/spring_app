import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { CustomerService } from 'src/app/Services/customer.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  currentCustomer: Customer|any;
  customers = [];

  constructor(
    private authenticationService: AuthenticationService
  ) {
      this.authenticationService.currentCustomer.subscribe((data:Customer)=>this.currentCustomer=data);
    }

  ngOnInit() {
  }
  

}
