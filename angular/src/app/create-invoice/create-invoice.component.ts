import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFilesService } from '../_services/upload-file.service';
import { FileUp } from "../models/file"

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
  file: FileUp = new FileUp();
  selectedFiles: FileList;
  progressInfos = [];
  fileInfo = [];
  message = '';
  fileInfos: Observable<any>;


  constructor(private invoiceService: InvoiceService,
    private router: Router, private route: ActivatedRoute, private uploadService: UploadFilesService) { }

  ngOnInit() {
    // this.fileInfos = this.uploadService.getFiles();

  }

  saveInvoice() {
    this.invoiceService.createInvoice(this.invoice).subscribe(data1 => {
      console.log(data1)
      this.invoice = new Invoice();
      this.uploadFiles();



    })
  }

  onSubmit() {
    this.invoice.items = [];
    this.invoice.items.push(this.item);
    this.invoice.files = this.fileInfo;
    this.invoice.files.push(this.file);
    console.log("array item", this.item)
    console.log("array file", this.file)

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
  selectFiles(event) {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
  }

  upload(idx, file) {
    this.progressInfos[idx] = { value: 0, fileName: file.name };
    this.fileInfo[idx] = { fileName: file.name, fileType: file.type, fileSize: file.size, fileInvoice:file.invoiceid }
    this.uploadService.upload(file).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
          console.log("fileinvoice", file.invoiceid)
        }
        // else if (event instanceof HttpResponse) {
        //   // this.fileInfos = this.uploadService.getFiles();
        // }
      },
      err => {
        this.progressInfos[idx].value = 0;
        this.message = 'Could not upload the file:' + file.name;
      });
  }

  uploadFiles() {
    this.message = '';

    for (let i = 0; i < this.selectedFiles.length; i++) {
      this.upload(i, this.selectedFiles[i]);
      console.log("array file", this.file.invoiceid)

    }
  }

}