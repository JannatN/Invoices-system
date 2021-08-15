import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './admin/components/board-admin/board-admin.component';
import { AuthGuard } from './core/helpers/auth.guard';
import { HasRoleGuard } from './core/helpers/has-role.guard';

// import { UpdateInvoiceComponent } from './board-admin/update-Invoice/update-invoice.component';
// import { InvoiceDetailsComponent } from './board-admin/invoice-details/invoice-details.component';
// import { CreateInvoiceComponent } from './board-admin/components/create-invoice/create-invoice.component';

// import { CreateItemComponent } from './board-admin/create-item/create-item.component';
// import { UploadFilesComponent } from './board-admin/upload-files/upload-files.component';

const routes: Routes = [
  { path: 'home', component: HomeComponent },
  { path: 'logout', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule) },
  {
    path: 'profile', loadChildren: () => import('./profile/profile.module').then(m => m.ProfileModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AuthGuard, HasRoleGuard]
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },


];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
