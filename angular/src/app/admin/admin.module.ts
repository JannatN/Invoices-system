import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateInvoiceComponent } from './components/create-invoice/create-invoice.component';
import { InvoiceDetailsComponent } from './components/invoice-details/invoice-details.component';
import { UploadFilesComponent } from './components/upload-files/upload-files.component';
import { CreateItemComponent } from './components/create-item/create-item.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { UpdateInvoiceComponent } from './components/update-Invoice/update-invoice.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSortModule } from '@angular/material/sort';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { AdminRoutingModule } from './admin-routing.module';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [CreateInvoiceComponent, InvoiceDetailsComponent,
    UploadFilesComponent, CreateItemComponent, BoardAdminComponent,
    UpdateInvoiceComponent],
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
