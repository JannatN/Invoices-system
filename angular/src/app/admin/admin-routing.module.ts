import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { CreateInvoiceComponent } from './components/create-invoice/create-invoice.component';
import { CreateItemComponent } from './components/create-item/create-item.component';
import { InvoiceDetailsComponent } from './components/invoice-details/invoice-details.component';
import { UpdateInvoiceComponent } from './components/update-Invoice/update-invoice.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { UploadFilesComponent } from './components/upload-files/upload-files.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { UserListComponent } from './components/user-list/user-list.component';

const routes: Routes = [
  { path: '', component: BoardAdminComponent },
  { path: 'addInvoice', component: CreateInvoiceComponent },
  { path: 'addItem/:id', component: CreateItemComponent },
  { path: 'attachFile/:id', component: UploadFilesComponent },
  { path: 'updateInvoice/:id', component: UpdateInvoiceComponent },
  { path: 'invoiceDetails/:id', component: InvoiceDetailsComponent },
  { path: 'users', component: UserListComponent },
  { path: 'updateUser/:id', component: UpdateUserComponent },
  { path: 'userDetails/:id', component: UserDetailsComponent },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AdminRoutingModule { }
