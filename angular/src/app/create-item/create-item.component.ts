import { Item } from '../models/item';
import { Component, OnInit, Input } from '@angular/core';
import { ItemService } from "../_services/items.service";
import { ActivatedRoute, Router } from '@angular/router';
import { InvoiceService } from '../_services/invoices.service';
import { Invoice } from '../models/invoice';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {
  invoice: Invoice;
  item: Item = new Item();
  submitted = false;
  id: number;

  constructor(private invoiceService: InvoiceService, private itemService: ItemService,
    private router: Router, private route: ActivatedRoute) { }

  ngOnInit() {
    // this.getInv();
    // this.id = this.route.snapshot.params['id'];
    this.invoice = new Invoice();

    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log(data)
        this.invoice = data;
      }, error => console.log(error));



  }


  newItem(): void {
    this.submitted = false;
    this.item = new Item();
    this
  }


  save() {

    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log(data.id)
        this.itemService
          .createItem2(this.item, data.id).subscribe(data => {
            console.log(data)
            this.item = new Item();
          },
            error => console.log(error));

      })
  }

  onSubmit() {
    this.submitted = true;
    this.save();
  }

  gotoList() {
    this.router.navigate(['/items']);
  }
  createItem() {
    this.router.navigate(['addItem']);
  }

  invoiceid(id: number) {
    this.router.navigate(['addInvoice', id]);
  }
}


