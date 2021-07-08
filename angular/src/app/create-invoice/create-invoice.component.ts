import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { ItemService } from '../_services/items.service';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.css']
})
export class CreateInvoiceComponent implements OnInit {

  invoice: Invoice = new Invoice();
  submitted = false;
  invoices: Invoice;
  i: any;
  item: Item = new Item();
  // item2:Item[]
  
  // submitted = false;
  id: number;

  constructor(private invoiceService: InvoiceService,
    private router: Router, private itemService: ItemService, private route: ActivatedRoute) { }

  ngOnInit() {

  }

  newInvoice(): void {
    this.submitted = false;
    this.invoice = new Invoice();
  }


  saveInvoice() {



    this.invoiceService
      .createInvoice(this.invoice).subscribe(data1 => {
        console.log(data1)


        this.invoice = new Invoice();

//         this.itemService.createItem(this.item).subscribe(data=>{
// console.log(data)
//           this.item = new Item();  
//         })
        //   this.invoiceService
        //     .getLastInvoice().subscribe(data1 => {
        //       console.log(data1.id);
        //       this.itemService.getLastItem().subscribe(data => {
        //         console.log(data)

        //         data.invoiceID = data1.id;
        //         console.log(data.invoiceID)
        //         this.itemService.createItem(data).subscribe(data => {
        //           console.log(data)
        //         })

              
        // }

        // )

      })
  }
  saveItem() {
    this.itemService.createItem(this.item).subscribe(data => {
      console.log(data);
      this.item = new Item();
    })
  }
  onSubmit() {
    this.invoice.items = [];
    this.invoice.items.push(this.item);

    this.submitted = true;
    this.saveInvoice();
    console.log("invoice created");
    // this.saveItem();
    

  }

  gotoList() {
    this.router.navigate(['/invoices']);
  }

  getInv() {
    this.invoiceService
      .getLastInvoice().subscribe(data => {
        console.log(data.id + 1);
        console.log(data);




      },
        error => console.log(error));
  }

  newItem(): void {
    this.submitted = false;
    this.item = new Item();

  }

  createItem() {
    this.router.navigate(['addItem']);
  }

}