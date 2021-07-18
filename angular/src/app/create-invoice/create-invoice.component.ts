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
  file :File = new File();
  location: any;
  // files: File[];
  selectedFiles: FileList;
  selectedFiles2: File[];
  idx:number
  progressInfos = [];
  message = '';
  id: number;

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
    })
  }

  onSubmit() {
    this.invoice.items = [];
    this.invoice.items.push(this.item);
    this.invoice.files = [] ;
       this.invoice.files.push(this.file)
    console.log(this.invoice.files)
   console.log( this.selectedFiles2)
//  this.upload(this.selectedFiles2)
    this.submitted = true;
    this.saveInvoice();


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


  selectFiles(event) {
    this.progressInfos = [];
    
    this.selectedFiles = event.target;
    
  }

  upload(file) {
    this.progressInfos[this.idx] = { value: 0, fileName: file.name };

    this.uploadService.upload(file).subscribe(
      event => {
     
        if (event.type === HttpEventType.UploadProgress) {
          this.progressInfos[this.idx].value = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.fileInfos = this.uploadService.getFiles();
        }
      },
      err => {
        this.progressInfos[this.idx].value = 0;
        this.message = 'Could not upload the file:' + file.name;
      });
  }

  uploadFiles() {
    this.message = '';

    for (let i = 0; i < this.selectedFiles.length; i++) {
      this.upload(this.selectedFiles[i]);   
      // this.upload(i, this.selectedFiles2[i]);   

      // console.log(this.file)
   

    }
  }
}

