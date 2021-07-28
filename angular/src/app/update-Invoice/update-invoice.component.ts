import { Component, OnInit } from '@angular/core';
import { Invoice } from '../models/invoice';
import { ActivatedRoute, Router } from '@angular/router';
import { InvoiceService } from '../_services/invoices.service';
import {Location} from '@angular/common';
import { Item } from '../models/item';


@Component({
  selector: 'app-update-invoice',
  templateUrl: './update-invoice.component.html',
  styleUrls: ['./update-invoice.component.css']
})
export class UpdateInvoiceComponent implements OnInit {

  id: number;
  invoice: Invoice;
  item: Item;

  constructor(private route: ActivatedRoute,private router: Router,
    private invoiceService: InvoiceService, private location: Location) { }

  ngOnInit() {
    this.invoice = new Invoice();

    this.id = this.route.snapshot.params['id'];
    
    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log(data)
        this.invoice = data;
      }, error => console.log(error));
  }

  updateInvoice() {
    this.invoiceService.updateInvoice(this.id, this.invoice)
      .subscribe(data => {
        console.log(data);
        this.invoice = new Invoice();
        this.list();
      }, error => console.log(error));
  }

  onSubmit() {
    this.updateInvoice();    
  }

  list(){
    this.location.back();  }
}
