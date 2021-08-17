import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { RegisterComponent } from './register/register.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { AuthGuard } from './core/helpers/auth.guard';
import { AdminRoleGuard } from './core/helpers/admin-role.guard';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { AudRoleGuard } from './core/helpers/aud-role.guard';




const routes: Routes = [
  // { path: 'home', component: HomeComponent },
  { path: 'logout', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', loadChildren: () => import('./register/register.module').then(m => m.RegisterModule) },
  {
    path: 'profile', loadChildren: () => import('./profile/profile.module').then(m => m.ProfileModule),
    canActivate: [AuthGuard]
  },
  {
    path: 'admin', loadChildren: () => import('./admin/admin.module').then(m => m.AdminModule),
    canActivate: [AuthGuard, AdminRoleGuard]
  },
  {
    path: 'auditor', loadChildren: () => import('./auditor/auditor.module').then(m => m.AuditorModule),
    canActivate: [AuthGuard, AudRoleGuard]
  },
  {
    path: 'board', loadChildren: () => import('./shared/shared.module').then(m => m.SharedModule),
    canActivate: [AuthGuard]
  },
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: '**', component: PageNotFoundComponent},


];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
