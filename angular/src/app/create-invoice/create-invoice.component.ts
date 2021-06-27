import { Invoice } from '../invoice';
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

  constructor(private invoiceService: InvoiceService,
    private router: Router) { }

  ngOnInit() {
  }

  newInvoice(): void {
    this.submitted = false;
    this.invoice = new Invoice();
  }

  save() {
    this.invoiceService
    .createInvoice(this.invoice).subscribe(data => {
      console.log(data)
      this.invoice = new Invoice();
      this.gotoList();
    }, 
    error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.save();    
  }

  gotoList() {
    this.router.navigate(['/invoices']);
  }
}
