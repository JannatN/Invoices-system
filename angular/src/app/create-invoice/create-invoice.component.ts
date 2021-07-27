import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { concat, Observable } from 'rxjs';
import { HttpClient, HttpEventType, HttpResponse } from '@angular/common/http';
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



  location: any;
  // files: File[];
  selectedFiles: File[];
  selectedFiles2:FileUp[];
  idx: number
  progressInfos = [];
  message = '';
  id: number;
  selectedFile: File;
  imgURL: any;
  retrievedImage: any;
  base64Data: any;
  retrieveResonse: any;
  message2: string;
  imageName: any;

  fileInfos: Observable<any>;
  constructor(private invoiceService: InvoiceService,
    private router: Router, private route: ActivatedRoute, private uploadService: UploadFilesService, private httpClient: HttpClient) { }



  saveInvoice() {

    console.log("invoice",this.invoice)

    this.invoiceService.createInvoice(this.invoice).subscribe(data1 => {
    
    
      console.log(data1)
      this.invoice = new Invoice();
    })
  }

  onSubmit() {
    this.invoice.items = [];
    this.invoice.items.push(this.item);

    // this.file=this.selectedFiles2;
    var a = Array.from(this.selectedFiles);
    // var b=Array.from(this.selectedFiles2);


    // // this.file=a;
    console.log(a)
    // console.log(a[0])

    // this.file=a[0]
console.log(this.selectedFiles[0])
console.log(this.selectedFiles2["name"])
console.log(this.file.name)


//  blob : Blob[];

// for (let i = 0; i < 10; i++) {
 
console.log( this.selectedFiles2[0].data)

    this.file.name=this.selectedFiles2[0].name
    this.file.data=this.selectedFiles2[0].data
    // response: Blob
    this.file.type=this.selectedFiles2[0].type
// }

console.log(this.file)

    this.invoice.files = [];
    this.invoice.files.push(this.file)



    //un
    // console.log(this.file)

    // console.log(this.invoice.files)


    // console.log(this.selectedFiles)
    //  console.log( this.selectedFiles2)
    //  this.upload(this.selectedFiles2)
    this.submitted = true;
    this.saveInvoice();

console.log( this.file.data)
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






  ngOnInit() {
    // this.fileInfos = this.uploadService.getFiles();
  }

  selectFiles(event) {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
    this.selectedFiles2 = event.target.files;
    // responseType: Blob

  }

  upload(idx, file) {
    this.progressInfos[idx] = { value: 0, fileName: file.name };

    this.uploadService.upload(file).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          // this.fileInfos = this.uploadService.getFiles();
          console.log("error")
        }
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
    }
  }


}

