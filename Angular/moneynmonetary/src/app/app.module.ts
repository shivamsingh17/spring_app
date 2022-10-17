import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './Components/navbar/navbar.component';
import { FooterComponent } from './Components/footer/footer.component';
import { QuickLinksComponent } from './Components/quick-links/quick-links.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { PaymentsComponent } from './Components/payments/payments.component';
import { SendMoneyComponent } from './Components/send-money/send-money.component';
import { RegisterComponent } from './Components/register/register.component';
import { LoginComponent } from './Components/login/login.component';
import { TransactionVerificationComponent } from './Components/transaction-verification/transaction-verification.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MiniProfileComponent } from './Components/mini-profile/mini-profile.component';
import { MiniPaymentsComponent } from './Components/mini-payments/mini-payments.component';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { NgxCaptchaModule } from 'ngx-captcha';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthInterceptor } from './Inteceptor/auth.interceptor';



@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    QuickLinksComponent,
    DashboardComponent,
    ProfileComponent,
    PaymentsComponent,
    SendMoneyComponent,
    RegisterComponent,
    LoginComponent,
    TransactionVerificationComponent,
    MiniProfileComponent,
    MiniPaymentsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
    MatToolbarModule,
    MatSidenavModule,
    MatButtonModule,
    MatIconModule,
    MatDividerModule,
    NgxCaptchaModule
  ],
  providers: [{
    provide : HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi   : true,
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
