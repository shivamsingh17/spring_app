import { HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from '../Models/customer';
import { Transaction } from '../Models/transaction';
import { AuthenticationService } from './authentication.service';
import { TransactionService } from './transaction.service';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {
  public currentTransactionData:Transaction|any;
  public customer:Customer|any;
  constructor(private router:Router, private transactionService:TransactionService, private authenticationService:AuthenticationService) { 
    this.customer=this.authenticationService.currentCustomerValue;
  }
  public commitPay(){
    this.transactionService.addTransaction(this.currentTransactionData).subscribe((data:Transaction)=>{
      this.customer.checkinBalance=data.currentBalance;
      this.customer.savingsBalance=this.customer.savingsBalance+data.roundedOffAmount-data.amount;
      this.authenticationService.saveCustomerData();
      this.currentTransactionData=null;
      this.router.navigate(['/payments'])
    },(error: HttpErrorResponse)=>{
      if(error.status==500){
        this.authenticationService.logout();
        this.router.navigate(['/login']);
      }
    });
  }
}
