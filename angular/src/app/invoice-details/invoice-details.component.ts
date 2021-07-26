import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from '../models/item';
import { ItemService } from '../_services/items.service';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { UploadFilesService } from '../_services/upload-file.service';
import { HttpErrorResponse, HttpEvent, HttpEventType } from '@angular/common/http';
import { saveAs } from 'file-saver';


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
  invoices: Observable<Invoice[]>;

  filenames: string[] = [];
  fileStatus = { status: '', requestType: '', percent: 0 };


  constructor(private route: ActivatedRoute, private router: Router,
    private invoiceService: InvoiceService, private itemService: ItemService, private location: Location, private uploadService: UploadFilesService) { }

  ngOnInit() {
    this.invoice = new Invoice();
    // this.invoices = this.invoiceService.getInvoicesList();

    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {

        console.log("data", data)
        this.invoice = data;
        this.invoices = data
        // this.items.push(this.invoices["items"]);
        // console.log("items", this.invoice.items)
        // console.log("files", this.invoice.files)

        // console.log("start");

        console.log(this.invoices)
        // console.log("end");

      })
    error => console.log(error);
  }

  list() {
    this.location.back();
  }






//  // define a function to upload files
//  onUploadFiles(files: File[]): void {
//   const formData = new FormData();
//   for (const file of files) { formData.append('files', file, file.name); }
//   this.uploadService.upload(formData).subscribe(
//     event => {
//       console.log(event);
//       this.resportProgress(event);
//     },
//     (error: HttpErrorResponse) => {
//       console.log(error);
//     }
//   );
// }

// // define a function to download files
// onDownloadFile(filename: string): void {
//   this.uploadService.download(filename).subscribe(
//     event => {
//       console.log(event);
//       this.resportProgress(event);
//     },
//     (error: HttpErrorResponse) => {
//       console.log(error);
//     }
//   );
// }

private resportProgress(httpEvent: HttpEvent<string[] | Blob>): void {
  switch(httpEvent.type) {
    case HttpEventType.UploadProgress:
      this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Uploading... ');
      break;
    case HttpEventType.DownloadProgress:
      this.updateStatus(httpEvent.loaded, httpEvent.total!, 'Downloading... ');
      break;
    case HttpEventType.ResponseHeader:
      console.log('Header returned', httpEvent);
      break;
    case HttpEventType.Response:
      if (httpEvent.body instanceof Array) {
        this.fileStatus.status = 'done';
        for (const filename of httpEvent.body) {
          this.filenames.unshift(filename);
        }
      } else {
        saveAs(new File([httpEvent.body!], httpEvent.headers.get('File-Name')!, 
                {type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}));
        // saveAs(new Blob([httpEvent.body!], 
        //   { type: `${httpEvent.headers.get('Content-Type')};charset=utf-8`}),
        //    httpEvent.headers.get('File-Name'));
      }
      this.fileStatus.status = 'done';
      break;
      default:
        console.log(httpEvent);
        break;
    
  }
}

private updateStatus(loaded: number, total: number, requestType: string): void {
  this.fileStatus.status = 'progress';
  this.fileStatus.requestType = requestType;
  this.fileStatus.percent = Math.round(100 * loaded / total);
}
}





