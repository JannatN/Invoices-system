import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BoardComponent } from './components/board/board.component';
import { InvoiceDetailsComponent } from './components/invoice-details/invoice-details.component';

const routes: Routes = [
  {
    path: '',
    component: BoardComponent,

  },
  {
    path: 'invoiceDetails/:id',
    component: InvoiceDetailsComponent,
    data: {
      allowedRoles: ['ROLE_ADMIN', 'ROLE_AUDITOR']
    }
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SharedRoutingModule { }
