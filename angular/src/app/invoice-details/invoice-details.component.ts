import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from '../models/item';
import { ItemService } from '../_services/items.service';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';

@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrls: ['./invoice-details.component.css']
})
export class InvoiceDetailsComponent implements OnInit {

  id: number;
  invoice: Invoice;
  // items:Observable<Item[]>
  invoices: Observable<Invoice[]>;
  constructor(private route: ActivatedRoute, private router: Router,
    private invoiceService: InvoiceService, private location: Location) { }

  ngOnInit() {
    this.invoice = new Invoice();
    this.invoices = this.invoiceService.getInvoicesList();

    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {

        console.log(data)
        this.invoice = data;
        this.invoices = data
        console.log(this.invoices["items"])

        // console.log("start");

        console.log(this.invoices)
        // console.log("end");




      })
    error => console.log(error);
  }



  list() {
    this.location.back();
  }
}