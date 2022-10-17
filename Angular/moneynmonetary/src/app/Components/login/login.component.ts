import { Component, HostListener, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, Validators} from "@angular/forms"
import { ActivatedRoute, Route, Router } from '@angular/router';
import { first } from 'rxjs/operators';
import { Customer } from 'src/app/Models/customer';
import { AuthenticationService } from 'src/app/Services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginform !: FormGroup
  loading = false;
  submitted = false;
  returnUrl: string | any;
  public customer:Customer|any;
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService,
  ) {
      // redirect to home if already logged in
      if (this.authenticationService.currentCustomer) {
          this.router.navigate(['/']);
      }
  }

  siteKey:string="6Ldn8moiAAAAABmxKLJ5KBrDWAX0WMkJ1576RPqV";
  type:any="image";
  size:any;
  public getScreenWidth: any;
  public getScreenHeight: any;

  @HostListener('window:resize', ['$event'])
  onWindowResize() {
    this.getScreenWidth=window.innerWidth;
    if(this.getScreenWidth<720)
    {
      this.size="compact";
    }
    else{
      this.size="normal";
    }
  }

  ngOnInit(): void {

    this.loginform=this.formBuilder.group({
      email:['',[Validators.required, Validators.email,Validators.pattern('^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$')]],
      password:['', [Validators.required]],
      recaptcha: ['', Validators.required]
    });
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }
  // convenience getter for easy access to form fields
  get f() { return this.loginform.controls; }
  loginuser(){
    this.submitted = true;
    // reset alerts on submit
    // stop here if form is invalid
    if (this.loginform.invalid) {
        return;
    }
    this.loading = true;
      this.authenticationService.login(this.f['email'].value, this.f['password'].value).pipe(first())
        .subscribe(data => {
          this.customer=data;
          this.router.navigate([this.returnUrl]);},
          err=>{
            console.log(err);
            alert("Login unsuccessful");
          });
  }

}
