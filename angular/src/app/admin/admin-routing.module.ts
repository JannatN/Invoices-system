import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CreateItemComponent } from './components/create-item/create-item.component';
import { CreateInvoiceComponent } from './components/create-invoice/create-invoice.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { UploadFilesComponent } from './components/upload-files/upload-files.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { UpdateInvoiceComponent } from './components/update-Invoice/update-invoice.component';
import { AddEditComponent } from './components/add-edit/add-edit.component';
import { BoardComponent } from '../shared/components/board/board.component';

const routes: Routes = [
  { path: '', component: BoardComponent },
  { path: 'addInvoice', component: CreateInvoiceComponent },
  // { path: 'addInvoice', component: AddEditComponent },
  { path: 'addItem/:id', component: CreateItemComponent },
  { path: 'attachFile/:id', component: UploadFilesComponent },
  // { path: 'updateInvoice/:id', component: AddEditComponent },

  { path: 'updateInvoice/:id', component: UpdateInvoiceComponent },
  { path: 'users', component: UserListComponent },
  { path: 'updateUser/:id', component: UpdateUserComponent },
  { path: 'userDetails/:id', component: UserDetailsComponent },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
