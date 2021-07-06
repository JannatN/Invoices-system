import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { Router } from '@angular/router';
import { ItemService } from "../_services/items.service";
import { Item } from '../models/item';
import { Location } from '@angular/common';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.css']
})
export class CreateInvoiceComponent implements OnInit {

  invoice: Invoice = new Invoice();
  item: Item = new Item();
  submitted = false;
  id: number;

  constructor(private invoiceService: InvoiceService, private itemService: ItemService,
    private router: Router, private location: Location) { }

  ngOnInit() {
  }

  newInvoice(): void {
    this.submitted = false;
    this.invoice = new Invoice();
  }

  newItem(): void {
    this.submitted = false;
    this.item = new Item();
  }

  saveInvoice() {
    this.invoiceService
      .createInvoice(this.invoice).subscribe(data => {
        console.log(data)
        this.invoice = new Invoice();
        this.id = this.invoice.id;
      },
        error => console.log(error));
  }

  saveItem() {
    this.itemService
      .createItem(this.item, this.id).subscribe(data => {
        console.log(data)
        this.item = new Item();
        // this.gotoList();
      },
        error => console.log(error));
  }


  onSubmit() {
    this.submitted = true;
    this.saveInvoice();
    this.saveItem();

  }


  gotoList() {
    this.location.back();
  }


}