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

        this.itemService.createItem(this.item).subscribe(data2 => {
          console.log(data2)

          this.item = new Item();




          this.invoiceService
            .getLastInvoice().subscribe(data1 => {
              console.log(data1.id);


              this.itemService.getLastItem().subscribe(data => {
                console.log(data)

                data.invoiceID = data1.id;
                console.log(data.invoiceID)
                this.itemService.createItem(data).subscribe(data => {
                  console.log(data)
                })

              })
            }

            )
        })

      })
  }

  onSubmit() {
    this.submitted = true;
    this.saveInvoice();
    console.log("invoice created");

  }

  gotoList() {
    this.router.navigate(['/invoices']);
  }

  getInv() {
    this.invoiceService
      .getLastInvoice().subscribe(data => {
        console.log(data.id + 1);
        console.log(data);


        // this.invoiceService.getInvoice(data.id).subscribe(data1 => {

        //   console.log(data1)

        // })
        // this.invoice = new Invoice();

        // console.log("sssssss"+data.length)

      },
        error => console.log(error));
  }

  newItem(): void {
    this.submitted = false;
    this.item = new Item();
    this
  }
  // $last_insert = mysql_insert_id();

  // save() {
  //   this.invoiceService
  //     .getLastInvoice().subscribe(data1 => {
  //       console.log(  data1.id+1);


  //   this.itemService
  //     .createItem(this.item,data1.id).subscribe(data => {
  //       console.log(data)

  //       this.item = new Item();
  //     },
  //       error => console.log(error));


  //   })
  // }
  createItem() {
    this.router.navigate(['addItem']);
  }

}