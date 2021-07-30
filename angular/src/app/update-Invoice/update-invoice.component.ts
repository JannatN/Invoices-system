import { Component, OnInit } from '@angular/core';
import { Invoice } from '../models/invoice';
import { ActivatedRoute, Router } from '@angular/router';
import { InvoiceService } from '../_services/invoices.service';
import {Location} from '@angular/common';
import { Item } from '../models/item';
import { TokenStorageService } from '../_services/token-storage.service';


@Component({
  selector: 'app-update-invoice',
  templateUrl: './update-invoice.component.html',
  styleUrls: ['./update-invoice.component.css']
})
export class UpdateInvoiceComponent implements OnInit {

  id: number;
  invoice: Invoice;
  item: Item;

  constructor(private route: ActivatedRoute,private router: Router,
    private invoiceService: InvoiceService,  private location: Location, private tokenStorageService: TokenStorageService) { }
    
  ngOnInit() {
//     console.log(this.tokenStorageService.getUser().username);
//     this.name=this.tokenStorageService.getUser().username
// this.tracke.auditorName=this.name

    this.invoice = new Invoice();

    this.id = this.route.snapshot.params['id'];
    
    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log("before",data)
      //   this.test=data
      // this.tracke.invoiceBefore=this.test;
      // console.log("name",this.tracke.auditorName)
      // console.log("tracke before",this.tracke.invoiceBefore)
      
        this.invoice = data;
        // console.log(this.invoice.items)
      }, error => console.log(error));
  }

  updateInvoice() {
    this.invoiceService.updateInvoice(this.id, this.invoice)
      .subscribe(data => {
        console.log(data);
//         console.log("tracke before",this.tracke.invoiceBefore)
//         console.log("this.tracke.invoiceAfter",this.tracke.invoiceAfter)
//         console.log("invoice after",this.invoice)
//         // this.tracke.invoiceAfter=this.invoice
// this.str=JSON.stringify(this.invoice)
// console.log("strrrrrrrrrrrrrrrrrrrrrrrrrrrrr",this.str)
//     this.track.createTrack(this.tracke).subscribe(data3=>{
//       console.log(data3)
//     })
//     console.log("etstttt",this.test)
      
        this.invoice = new Invoice();
       
        this.list();
      }, error => console.log(error));
  }

  onSubmit() {
  
   
    this.updateInvoice();    
  }

  list(){
    this.location.back();  }
}
