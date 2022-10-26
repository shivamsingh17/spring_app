import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { TransactionService } from 'src/app/Services/transaction.service';
import { Transaction } from 'src/app/Models/transaction';
import { formatNumber } from '@angular/common';
import { HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payments',
  templateUrl: './payments.component.html',
  styleUrls: ['./payments.component.css']
})
export class PaymentsComponent implements OnInit {
  public customer: Customer;
  transactions: Transaction|any;
  
  constructor(private transactionservice:TransactionService,
    private router:Router,
    private authenticationservice:AuthenticationService) {
      this.customer = this.authenticationservice.currentCustomerValue;
     }
commaSeparatedValue(value:any){
  return formatNumber(value,'en-US', '1.0-2')
}
  ngOnInit(): void {
    this.transactionservice.getTransactions(this.customer.id).subscribe(
      data =>{
        this.transactions = data;
      },(error: HttpErrorResponse)=>{
        if(error.status==500){
          this.authenticationservice.logout();
          this.router.navigate(['/login']);
        }
      }
    );
  }


}
