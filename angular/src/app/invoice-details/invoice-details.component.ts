import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { Router, ActivatedRoute } from '@angular/router';
import { Item } from '../models/item';
import { ItemService } from '../_services/items.service';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { invoices_aud } from '../models/invoices_aud';

@Component({
  selector: 'app-invoice-details',
  templateUrl: './invoice-details.component.html',
  styleUrls: ['./invoice-details.component.css']
})
export class InvoiceDetailsComponent implements OnInit {

  id: number;
  invoice: Invoice;
  // invoiceList: Observable<Invoice[]>
  invoiceAud: Observable<invoices_aud[]>;
  items: Item[];
  files: File[];
  invoices: Observable<Invoice[]>;
  constructor(private route: ActivatedRoute, private router: Router,
    private invoiceService: InvoiceService, private itemService: ItemService, private location: Location) { }

  ngOnInit() {
    // this.getInvoicesAud();
    this.invoice = new Invoice();


    this.id = this.route.snapshot.params['id'];

    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
// this.invoiceList=data

        // console.log("this.invoiceList", this.invoiceList)

        console.log("invoiceee ",data)
        this.invoice = data;
        this.invoices = data


        console.log(this.invoices)

      })

    this.invoiceService.getInvoiceAudById(this.id).subscribe(data => {
      // console.log("invioces aud" , data)
      this.invoiceAud = data
      console.log("invioces aud", this.invoiceAud)

    })
    // console.log("invoices Aud ", this.invoiceAud)

    error => console.log(error);
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
}