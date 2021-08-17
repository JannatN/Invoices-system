import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Location } from '@angular/common';
import { Invoice } from 'src/app/core/models/invoice';
import { Item } from 'src/app/core/models/item';
import { InvoiceService } from 'src/app/core/services/invoices.service';
import { TokenStorageService } from 'src/app/core/services/token-storage.service';


@Component({
  selector: 'app-update-invoice',
  templateUrl: './update-invoice.component.html',
  styleUrls: ['./update-invoice.component.css']
})
export class UpdateInvoiceComponent implements OnInit {

  id: number;
  invoice: Invoice;
  item: Item;

  constructor(private route: ActivatedRoute, private router: Router,
    private invoiceService: InvoiceService, private location: Location, private tokenStorageService: TokenStorageService) { }

  ngOnInit() {
    this.invoice = new Invoice();

    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log("before", data)
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

  list() {
    this.location.back();
  }
}
