import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from '../models/item';
import { ItemService } from '../_services/items.service';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { invoices_aud } from '../models/invoices_aud';
import { UploadFilesService } from '../_services/upload-file.service';
import {FileUp} from '../models/file'
@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrls: ['./invoice-details.component.css']
})
export class InvoiceDetailsComponent implements OnInit {
  istrue
  id: number;
  invoice: Invoice;
  // invoiceList: Observable<Invoice[]>
  invoiceAud: Observable<invoices_aud[]>;
  items: Item[];
  files: FileUp[];
  fileInfos: Observable<FileUp[]>;

  invoices: Observable<Invoice[]>;
  constructor(private route: ActivatedRoute, private router: Router,
    private invoiceService: InvoiceService, private itemService: ItemService,
    private location: Location, private uploadService: UploadFilesService) { }

  ngOnInit() {

    this.invoice = new Invoice();


    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {


        console.log("invoiceee ", data)
        this.invoice = data;
        this.invoices = data
        console.log("this.invoice   ", this.invoice)

      })

    this.invoiceService.getInvoiceAudById(this.id).subscribe(data => {

      this.invoiceAud = data
      console.log("invioces aud", this.invoiceAud)

    })

    error => console.log(error);
    this.fileInfos = this.uploadService.getFiles(this.id);
    console.log(this.fileInfos);
    this.uploadService.getFiles(this.id).subscribe(data=>{
      console.log(data)
    });
  }

  // getInvoicesAud(){
  //   this.invoiceService.getInvoiceAud().subscribe(data=>{
  //     console.log("invioces aud" , data)
  //     this.invoiceAud=data
  //     console.log("invoices Aud ", this.invoiceAud)
  //   })
  // }

  list() {
    this.location.back();
  }
  public deleteFile = (id: string) => {
    this.uploadService.deleteFile(id)
      .subscribe(
        data => {
          console.log(data);
          // this.reloadData();
        },
        error => console.log(error));
  }

  download(id,type) {

      this.uploadService.getFile(id).subscribe(data => {


          const blob = new Blob([data], { type: type });
           const url = window.URL.createObjectURL(blob);
           console.log("jpeg   " , url)
           window.open(url);
 


      })

  
    }
  }
