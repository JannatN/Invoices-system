import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardUserComponent } from './board-user/board-user.component';
import { BoardAuditorComponent } from './board-auditor/board-auditor.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { UpdateUserComponent } from './update-user/update-user.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { UserListComponent } from './user-list/user-list.component';

import { UpdateInvoiceComponent } from './update-Invoice/update-invoice.component';
import {InvoiceDetailsComponent } from './invoice-details/invoice-details.component';
import { CreateInvoiceComponent } from './create-invoice/create-invoice.component';

import { CreateItemComponent } from './create-item/create-item.component';
import { UploadFilesComponent } from './upload-files/upload-files.component';




const routes: Routes = [
  // { path: 'home', component: HomeComponent },
  { path: 'logout', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'user', component: BoardUserComponent },
  { path: 'auditor', component: BoardAuditorComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'users', component: UserListComponent },
  { path: 'update/:id', component: UpdateUserComponent },
  { path: 'details/:id', component: UserDetailsComponent },
  { path: 'detailsInvoice/:id', component: InvoiceDetailsComponent },
  // { path: 'updateInvoice/:id', component: UpdateInvoiceComponent },
  // { path: 'addInvoice', component: CreateInvoiceComponent },
  { path: 'updateInvoice/:id', component: HomeComponent },
  { path: 'addInvoice', component: HomeComponent },
  
//   {
  
//     children: [
      
//         { path: 'addInvoice', component: HomeComponent },
//         { path: 'updateInvoice/:id', component: HomeComponent }
//     ]
// },
  { path: 'addItem/:id', component: CreateItemComponent },
  { path: 'attachFile/:id', component: UploadFilesComponent },


];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
