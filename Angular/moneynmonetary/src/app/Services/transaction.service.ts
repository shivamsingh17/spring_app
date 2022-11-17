import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../Models/transaction';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class TransactionService {
  public paymentsUrl:string;
  public transactions:Observable<Transaction[]>|undefined;
  constructor(private http:HttpClient,private authenticationService:AuthenticationService) { 
    this.paymentsUrl="http://localhost:8080/api/v1/payments";
  }

  public addTransaction(txn:Transaction): Observable<Transaction> {
    let result: any;
    result = this.http.post<Transaction>(this.paymentsUrl,txn);
    return result;
  }

  public getTransactions(customerId:String): Observable<Transaction[]> {
    let result: any;
    result = this.http.get<Transaction>(this.paymentsUrl+"/"+customerId);
    this.transactions=result;
    return result;
  }

  // deleteTransaction(transId: any): Observable<any> {
  //   let result: any;
  //   result = this.http.delete<Transaction>("http://localhost:8080/payments/"+transId);
  //   return result;
  // }
}
