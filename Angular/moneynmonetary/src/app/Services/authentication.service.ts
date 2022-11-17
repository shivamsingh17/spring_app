import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { map } from 'rxjs/operators';

import { Customer } from '../Models/customer';
import { CustomerService } from './customer.service';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    public isUserLoggedIn: boolean = false;
    public isLoggedIn= new Subject<boolean>();
    
    private currentCustomerSubject: BehaviorSubject<Customer>|any;
    public currentCustomer: Observable<Customer>|any;

    constructor(private http: HttpClient,private customerService:CustomerService) {
        this.isUserLoggedIn=localStorage.getItem('isUserLoggedIn')=='true';
        this.isLoggedIn.next(this.isUserLoggedIn);
        let localVal=localStorage.getItem('currentCustomer');
        if(localVal!=null){
            this.currentCustomerSubject = new BehaviorSubject<Customer>(JSON.parse(localVal));
            this.currentCustomer = this.currentCustomerSubject.asObservable();
        } 
    }

    public get currentCustomerValue(): Customer {
        return this.currentCustomerSubject.value;
    }

    login(email: string, password: string):Observable<Customer> {
        return this.http.post<any>(`${this.customerService.authenticationUrl}/authenticate`, { email, password })
            .pipe(map(customer => {
                // store user details and jwt token in local storage to keep user logged in between page refreshes
                localStorage.setItem('currentCustomer', JSON.stringify(customer));
                localStorage.setItem('isUserLoggedIn', "true");
                let localeCustomer=localStorage.getItem('currentCustomer');
                if(localeCustomer!=null){
                    this.currentCustomerSubject = new BehaviorSubject<Customer>(JSON.parse(localeCustomer));
                    this.currentCustomerSubject.next(customer);
                    this.currentCustomer = this.currentCustomerSubject.asObservable();
                    this.isLoggedIn.next(true);
                }
                return customer;
            }));
    }
    saveCustomerData(){
        localStorage.setItem('currentCustomer', JSON.stringify(this.currentCustomerValue));
    }

    logout() {
        // remove user from local storage and set current user to null
        localStorage.removeItem('currentCustomer');
        localStorage.setItem('isUserLoggedIn', "false");
        this.currentCustomerSubject.next(null);
        this.currentCustomer = null;
        this.isLoggedIn.next(false);
    }
}
