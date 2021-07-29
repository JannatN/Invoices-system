import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from '../models/item';
import { ItemService } from '../_services/items.service';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { UploadFilesService } from '../_services/upload-file.service';

@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrls: ['./invoice-details.component.css']
})
export class InvoiceDetailsComponent implements OnInit {

  id: number;
  invoice: Invoice;
  items: Item[];
  files: File[];
  fileInfos: Observable<File[]>;

  invoices: Observable<Invoice[]>;
  constructor(private route: ActivatedRoute, private router: Router,
    private invoiceService: InvoiceService, private itemService: ItemService, 
    private location: Location, private uploadService: UploadFilesService) { }

  ngOnInit() {

    this.invoice = new Invoice();
    // this.invoices = this.invoiceService.getInvoicesList();

    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {

        console.log("data", data)
        this.invoice = data;
        this.invoices = data

        console.log(this.invoices)

      })
    error => console.log(error);
    this.fileInfos = this.invoiceService.getFiles(this.id);

  }

  list() {
    this.location.back();
  }
  // downloadFile(data: Response) {
  //   const blob = new Blob([data], { type: 'text/csv' });
  //   const url = window.URL.createObjectURL(blob);
  //   window.open(url);
  // }
 
}