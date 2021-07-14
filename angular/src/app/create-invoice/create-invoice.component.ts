import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFilesService } from '../_services/upload-file.service';
import { File} from "../models/file"

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.css']
})
export class CreateInvoiceComponent implements OnInit {

  invoice: Invoice = new Invoice();
  submitted = false;
  // invoices: Invoice;
  item: Item = new Item();
  location: any;
  files: File[];

  constructor(private invoiceService: InvoiceService,
    private router: Router, private route: ActivatedRoute, private uploadService: UploadFilesService) { }

  ngOnInit() {
    // this.fileInfos = this.uploadService.getFiles();

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
    // this.invoice.files = this.files;
    // this.invoice.files.push(this.files);
    // this.uploadFiles();

    console.log("array item", this.item)
    console.log("array file", this.invoice.files)
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
  back() {
    this.location.back();
  }


}

