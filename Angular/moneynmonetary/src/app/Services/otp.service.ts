import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../Models/customer';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class OtpService {
  public otpUrl: string;
  constructor(private http: HttpClient,private authenticationService:AuthenticationService) {
    this.otpUrl = 'http://localhost:8080/api/v1/otp';
  }
  public sendOtp(customer:Customer): Observable<String> {
    return this.http.post<String>(this.otpUrl+"/generateOtp",customer);
  }
  verifyOtp(custId: String, otp: String) {
    return this.http.post<any>(this.otpUrl+"/verifyOtp",{custId,otp});
  }
}
