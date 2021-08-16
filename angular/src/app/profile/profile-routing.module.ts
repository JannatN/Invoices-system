import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UpdateUserComponent } from '../admin/components/update-user/update-user.component';
import { ProfileComponent } from './profile.component';

const routes: Routes = [
  {
    path:'',
    component: ProfileComponent
  },
  {
    path:'update/:id',
    component: UpdateUserComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProfileRoutingModule { }
