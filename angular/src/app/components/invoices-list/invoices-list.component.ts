import { Component, OnInit } from '@angular/core';
import { Invoice } from 'src/app/models/invoice.model';
import { InvoiceService } from 'src/app/_services/invoice.service';
@Component({
  selector: 'app-invoices-list',
  templateUrl: './invoices-list.component.html',
  styleUrls: ['./invoices-list.component.css']
})
export class InvoicesListComponent implements OnInit {
  
  invoices?: Invoice[];
    currentInvoice?: Invoice;
    currentIndex = -1;
    title = '';
  
    constructor(private invoiceService: InvoiceService) { }
  
    ngOnInit(): void {
      this.retrieveInvoices();
    }
  
    retrieveInvoices(): void {
      this.invoiceService.getInvList()
        .subscribe(
          data => {
            this.invoices = data;
            console.log(data);
          },
          error => {
            console.log(error);
          });
    }}