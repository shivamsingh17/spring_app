import { Component, Input, OnInit } from '@angular/core';
import { Customer } from 'src/app/Models/customer';
import { Transaction } from 'src/app/Models/transaction';
import { formatNumber } from '@angular/common';
import { TransactionService } from 'src/app/Services/transaction.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-mini-payments',
  templateUrl: './mini-payments.component.html',
  styleUrls: ['./mini-payments.component.css']
})
export class MiniPaymentsComponent implements OnInit {
  @Input() customer:Customer|any;
  transactions: Transaction[]|any;
  constructor(private transactionservice:TransactionService,private router:Router, private authenticationService:AuthenticationService) {
    transactionservice.transactions?.subscribe(data=>{this.transactions=data.slice(0,2)},(error: HttpErrorResponse)=>{
      //means token expired
      if(error.status==500){
        this.authenticationService.logout();
        this.router.navigate(['/login']);
      }
    });
  }
  commaSeparatedValue(value:any){
    return formatNumber(value,'en-US', '1.0-2')
  }
  ngOnInit(): void {
    if(this.transactions==null){
      this.transactionservice.getTransactions(this.customer.id).subscribe(
        (data:Transaction[])=>{
          this.transactions=data.slice(0,2);
        },(error: HttpErrorResponse)=>{
          if(error.status==500){
            this.authenticationService.logout();
            this.router.navigate(['/login']);
          }
        }
      );
    }
  }

}
