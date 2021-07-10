import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { ItemService } from '../_services/items.service';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.css']
})
export class CreateInvoiceComponent implements OnInit {

  invoice: Invoice = new Invoice();
  submitted = false;
  invoices: Invoice;
  item: Item = new Item();

  constructor(private invoiceService: InvoiceService,
    private router: Router, private itemService: ItemService, private route: ActivatedRoute) { }

  ngOnInit() {

  }

  saveInvoice() {
    this.invoiceService.createInvoice(this.invoice).subscribe(data1 => {
        console.log(data1)
        this.invoice = new Invoice();

              
      })
  }
 
  onSubmit() {
    this.invoice.items = [];
    this.invoice.items.push(this.item);
    this.submitted = true;
    this.saveInvoice();
    console.log("invoice created");
  
  }

  gotoList() {
    this.router.navigate(['/invoices']);
  }

  createItem() {
    this.router.navigate(['addItem']);
  }

}