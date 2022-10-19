import { formatNumber } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { CustomerService } from 'src/app/Services/customer.service';

@Component({
  selector: 'app-mini-profile',
  templateUrl: './mini-profile.component.html',
  styleUrls: ['./mini-profile.component.css']
})
export class MiniProfileComponent implements OnInit {
  customer:Customer|any;
  checkinBalance:String;
  savingsBalance:String;
  constructor(private customerService:CustomerService,private authenticationService: AuthenticationService,) {
    this.authenticationService.currentCustomer.subscribe((data: any)=>this.customer=data);
    this.checkinBalance=formatNumber(this.customer.checkinBalance, 'en-US', '1.0-2')
    this.savingsBalance=formatNumber(this.customer.savingsBalance, 'en-US', '1.0-2')
   }

  ngOnInit(): void {
    this.updateBalances();
  }
  updateBalances() {
    let currentCustomerValue=this.authenticationService.currentCustomerValue;
    if(currentCustomerValue!==null&&currentCustomerValue.id!==null)this.customerService.getBalances(currentCustomerValue.id).subscribe(customer => {
        // store user details and jwt token in local storage to keep user logged in between page refreshes
        currentCustomerValue.checkinBalance=customer.checkinBalance;
        currentCustomerValue.savingsBalance=customer.savingsBalance;
        this.customer=currentCustomerValue;
        this.checkinBalance=formatNumber(this.customer.checkinBalance, 'en-US', '1.0-2')
        this.savingsBalance=formatNumber(this.customer.savingsBalance, 'en-US', '1.0-2')
        this.authenticationService.saveCustomerData();
    });
  }

}
