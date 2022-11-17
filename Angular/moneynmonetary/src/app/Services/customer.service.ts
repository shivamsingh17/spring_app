import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Customer } from '../Models/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  public customerUrl: string;
  public authenticationUrl:String;
  constructor(private http: HttpClient) {
    this.customerUrl = 'http://localhost:8080/api/v1/customers';
    this.authenticationUrl = 'http://localhost:8080/api/v1/authentication';
  }
  public getBalances(id:String): Observable<Customer> {
    return this.http.get<Customer>(this.customerUrl+"/balances/"+id);
  }
  public create(customer:Customer): Observable<Customer> {
    return this.http.post<Customer>(this.authenticationUrl+'/register',customer);
  }
  public verify(accNo:number,ifscCode:String,jwtToken:String): Observable<Customer> {
    return this.http.post<Customer>(this.customerUrl+"/verify",{accNo,ifscCode});
  }
  public update(customer: Customer) {
    return this.http.put<Customer>(this.customerUrl+"/"+customer.id, customer);
  }
}