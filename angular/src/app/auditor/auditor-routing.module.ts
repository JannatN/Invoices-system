import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardComponent } from '../shared/components/board/board.component';
import { InvoiceDetailsComponent } from '../shared/components/invoice-details/invoice-details.component';

const routes: Routes = [
  { path: '', component: BoardComponent },
  { path: 'invoiceDetails/:id', component: InvoiceDetailsComponent },


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AuditorRoutingModule { }
