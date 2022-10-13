import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { LoginComponent } from './Components/login/login.component';
import { PaymentsComponent } from './Components/payments/payments.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { RegisterComponent } from './Components/register/register.component';
import { SendMoneyComponent } from './Components/send-money/send-money.component';
import { TransactionVerificationComponent } from './Components/transaction-verification/transaction-verification.component';
import { AuthGuard } from './Guards/auth.guard';

const routes: Routes = [
  { path: "", redirectTo: "dashboard", pathMatch: "full" },
  { path: "login", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  {path:'dashboard', component: DashboardComponent, canActivate:[AuthGuard]},
  {path:'profile', component: ProfileComponent, canActivate:[AuthGuard]},
  {path:'payments', component: PaymentsComponent, canActivate:[AuthGuard]},
  {path:'sendMoney', component: SendMoneyComponent, canActivate:[AuthGuard]},
  {path: 'transactionVerification', component: TransactionVerificationComponent}
  // {path:'contact', component: ContactlistComponent, canActivate:[AuthGuard]},
  // {path:'user', component: UserComponent},
  // {path:'profile/:userid', component: ProfileComponent, children:[
  //   {path:'posts', component: PostsComponent}
  // ]
  
// }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
