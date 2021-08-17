import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateInvoiceComponent } from './components/create-invoice/create-invoice.component';
import { UploadFilesComponent } from './components/upload-files/upload-files.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSortModule } from '@angular/material/sort';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { AdminRoutingModule } from './admin-routing.module';
import { UserListComponent } from './components/user-list/user-list.component';
import { UpdateUserComponent } from './components/update-user/update-user.component';
import { UserDetailsComponent } from './components/user-details/user-details.component';
import { CreateItemComponent } from './components/create-item/create-item.component';
import { UpdateInvoiceComponent } from './components/update-Invoice/update-invoice.component';
import { AddEditComponent } from './components/add-edit/add-edit.component';
import { SharedModule } from '../shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [CreateInvoiceComponent, UpdateInvoiceComponent,
    UploadFilesComponent, CreateItemComponent, AddEditComponent,
    UserListComponent, UpdateUserComponent, UserDetailsComponent],
  imports: [
    CommonModule,
    AdminRoutingModule,
    ReactiveFormsModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatFormFieldModule,
    MatSortModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule,
    SharedModule
  ]
})
export class AdminModule { }
