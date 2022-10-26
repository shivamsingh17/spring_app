import { DatePipe } from '@angular/common';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Customer } from 'src/app/Models/customer';
import { AuthenticationService } from 'src/app/Services/authentication.service';
import { CustomerService } from 'src/app/Services/customer.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  public profile: Customer;
  pipe = new DatePipe('en-US');
  todayWithPipe:String|any;
  public constructor(
    private router: Router, 
    private customerService:CustomerService,
    private authenticationService:AuthenticationService) {
      this.profile= this.authenticationService.currentCustomerValue;
      this.todayWithPipe = this.pipe.transform(this.profile.dob, 'yyyy/MM/dd');
  }

  public save() {
    this.customerService.update(this.profile).subscribe(data => {
      this.authenticationService.saveCustomerData();
      alert("Changes have been saved!!")
    },(error: HttpErrorResponse)=>{
      if(error.status==500){
        this.authenticationService.logout();
        this.router.navigate(['/login']);
      }
    });
  }
  public logout(){
      this.authenticationService.logout();
      this.router.navigate(['/login']);
  }
  public ngOnInit() {
  }

}
