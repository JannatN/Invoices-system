import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.css']
})
export class CreateInvoiceComponent implements OnInit {

  invoice: Invoice = new Invoice();
  submitted = false;
  invoices:Invoice;

  
  constructor(private invoiceService: InvoiceService, 
    private router: Router) { }

  ngOnInit() {
  }

  newInvoice(): void {
    this.submitted = false;
    this.invoice = new Invoice();
  }


  saveInvoice() {
    this.invoiceService
      .createInvoice(this.invoice).subscribe(data => {
        console.log(data)
        this.invoice = new Invoice();
      },
        error => console.log(error));
  }
 


  onSubmit() {
    this.submitted = true;
    this.saveInvoice();
    console.log(this.invoices);

  }

  gotoList() {
    this.router.navigate(['/invoices']);
  }
  

}