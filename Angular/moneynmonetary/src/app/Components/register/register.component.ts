import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from "@angular/forms";
import { HttpClient } from '@angular/common/http';
import { Route, Router } from '@angular/router';
import { Customer } from 'src/app/Models/customer';
import { CustomerService } from 'src/app/Services/customer.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public registrationForm !: FormGroup
  constructor(private formBuilder: FormBuilder, private router:Router, private customerService:CustomerService) {
    this.registrationForm=this.formBuilder.group({
      firstName:[''],
      lastName:[''],
      mobileNo:[''],
      address:[''],
      city:[''],
      state:[''],
      zipcode:[''],
      dob:new FormControl({value: null, disabled: false}, [Validators.required]),
      country:[''],
      emailId:['',[Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      password:['', [Validators.required]]
    })
   }
  

  ngOnInit(): void {}

  signup(){

    this.customerService.create(this.registrationForm.value).subscribe(data=>{
      this.registrationForm.reset;
      this.router.navigate(['login']);
    },
    err=>{
      alert("Registration unsuccessful");
    });
  }

}
