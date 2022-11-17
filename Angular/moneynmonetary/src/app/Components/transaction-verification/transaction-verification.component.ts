import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Customer } from 'src/app/Models/customer';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { OtpService } from 'src/app/Services/otp.service';
import { PaymentService } from 'src/app/Services/payment.service';

@Component({
  selector: 'app-transaction-verification',
  templateUrl: './transaction-verification.component.html',
  styleUrls: ['./transaction-verification.component.css']
})
export class TransactionVerificationComponent implements OnInit {
  public customer:Customer;
  public maskedEmailId:String;
  public otpField:String;
  constructor(private otpService:OtpService,private router:Router, private authenticationService:AuthenticationService,private paymentService:PaymentService) {
    this.customer=this.authenticationService.currentCustomerValue;
    this.maskedEmailId=this.customer.emailId.substring(0,3);
    var ind=this.customer.emailId.indexOf("@");
    for(let i=3;i<ind-3;i++){
      this.maskedEmailId+="X";
    }
    this.maskedEmailId+=this.customer.emailId.substring(ind-3,this.customer.emailId.length);
    this.otpField="";
  }

  ngOnInit(): void {
  }
  pay(){
    this.otpService.verifyOtp(this.customer.id,this.otpField).subscribe(data=>{
      this.paymentService.commitPay();
    },(error: HttpErrorResponse)=>{
      if(error.status==500){
        this.authenticationService.logout();
        this.router.navigate(['/login']);
      }
      else{
        alert("Wrong otp");
      }
    });
  }
}
