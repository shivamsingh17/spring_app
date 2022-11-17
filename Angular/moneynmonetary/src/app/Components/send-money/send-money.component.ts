import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {FormGroup, FormBuilder, Validators, FormControl} from "@angular/forms"
import { Customer } from 'src/app/Models/customer';
import { Transaction } from 'src/app/Models/transaction';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { CustomerService } from 'src/app/Services/customer.service';
import { TransactionService } from 'src/app/Services/transaction.service';
import { PaymentService } from 'src/app/Services/payment.service';
import { OtpService } from 'src/app/Services/otp.service';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-send-money',
  templateUrl: './send-money.component.html',
  styleUrls: ['./send-money.component.css']
})
export class SendMoneyComponent implements OnInit{

  public inputpay: any;
  public customer: Customer;
  public mergerName: String;
  public recipient:Customer|any;
  public transaction:Transaction|any;
  public fundTransferForm !: FormGroup;
  public constructor(private router:Router,private otpService:OtpService, private paymentService:PaymentService,private formBuilder: FormBuilder,private customerService:CustomerService,private transactionService:TransactionService, private authenticationService:AuthenticationService) {
      this.customer= this.authenticationService.currentCustomerValue;
      this.mergerName= this.customer.firstName.concat(" ").concat(this.customer.lastName.toString());
      this.fundTransferForm = this.formBuilder.group({
        payeename: new FormControl({value: null, disabled: true}, [Validators.required]),
        accnumber: ['', [Validators.required]],
        accnumber_conf: ['', [Validators.required]],
        ifsc: ['', [Validators.required]],
        amount: ['', [Validators.required]],
        amountconf: ['', [Validators.required]],
        reason: [''],
        name_card:new FormControl({value: this.mergerName, disabled: true}, [Validators.required]),
        remarks: [''],
        accNo:new FormControl({value: this.customer.accNo, disabled: true}, [Validators.required]),
    });
  }
  ngOnInit(){
    if(this.paymentService.currentTransactionData!=null){
      if(confirm("Do you want to continue with your last incomplete transaction?")) {
        this.transaction=this.paymentService.currentTransactionData;
        this.fundTransferForm = this.formBuilder.group({
          payeename: new FormControl({value: null, disabled: true}, [Validators.required]),
          accnumber: [this.transaction.receiverAccountNumber, [Validators.required]],
          accnumber_conf: [this.transaction.receiverAccountNumber, [Validators.required]],
          ifsc: [this.transaction.ifsc, [Validators.required]],
          amount: [this.transaction.amount, [Validators.required]],
          amountconf: [this.transaction.amount, [Validators.required]],
          reason: [''],
          name_card:new FormControl({value: this.mergerName, disabled: true}, [Validators.required]),
          remarks: [this.transaction.remarks],
          accNo:new FormControl({value: this.customer.accNo, disabled: true}, [Validators.required]),
      });
      }
    }
  }
  public verify() {
    this.inputpay=this.fundTransferForm.value;
    this.customerService.verify(this.inputpay.accnumber,this.inputpay.ifsc,this.customer.jwtToken).subscribe(data => {
      //now use the customer data to populate the payee name
      this.recipient=data;
      this.inputpay=this.fundTransferForm.value;
      this.fundTransferForm = this.formBuilder.group({
        payeename: new FormControl({value: this.recipient.firstName.concat(" ").concat(this.recipient.lastName.toString()), disabled: true}, [Validators.required]),
        accnumber: [this.inputpay.accnumber, [Validators.required,Validators.minLength(9)]],
        accnumber_conf: [this.inputpay.accnumber_conf],
        ifsc: [this.inputpay.ifsc, [Validators.required]],
        amount: [this.inputpay.amount, [Validators.required]],
        amountconf: [this.inputpay.amountconf],
        reason: [this.inputpay.reason],
        name_card:new FormControl({value: this.mergerName, disabled: true}, [Validators.required]),
        remarks: [this.inputpay.remarks],
        accNo:new FormControl({value: this.customer.accNo, disabled: true}, [Validators.required]),
    });
    } ,(error: HttpErrorResponse)=>{
      if(error.status==500){
        this.authenticationService.logout();
        this.router.navigate(['/login']);
      }
    });
  }
  public pay() {
    this.inputpay=this.fundTransferForm.value;
    if((this.inputpay.accnumber===this.inputpay.accnumber_conf) && (this.inputpay.accnumber!=="")){
      if(this.inputpay.amount===this.inputpay.amountconf && (this.inputpay.amount!=="")){
        if(this.recipient!=null&&this.fundTransferForm.valid){
          this.inputpay=this.fundTransferForm.value;
          this.transaction = {
            amount: this.inputpay.amount,
            roundedOffAmount: Math.ceil(this.inputpay.amount),
            timeStamp: null,
            receiverAccountNumber: this.inputpay.accnumber,
            ifsc:this.inputpay.ifsc,
            remarks: this.inputpay.remarks,
            currentBalance: this.customer.checkinBalance-Math.ceil(this.inputpay.amount),
            recipient: this.recipient.id,
            customerAccountNumber:this.customer.accNo,
            customerId: this.customer.id
          };
          this.paymentService.currentTransactionData=this.transaction;
          this.otpService.sendOtp(this.customer).subscribe(data=>{
          }
          ,(error: HttpErrorResponse)=>{
            if(error.status==500){
              this.authenticationService.logout();
              this.router.navigate(['/login']);
            }
            else{
              alert("Something went wrong");
              this.router.navigate(['/sendMoney']);
            }
          });
          
          this.router.navigate(['/transactionVerification']);
        }
          else{
            alert("Please verify the recipient");
          }
      }
      else{
        alert("Tranaction amount. and the Confirm transaction amount. should be same or left empty")
      }
    }
    else{
      alert("Account no. and the Confirm Account no. should be same or left empty")
    }
  }

}
