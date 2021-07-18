import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { UploadFilesService } from '../_services/upload-file.service';
import { File } from "../models/file"
import { FormArray, FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.css']
})
export class CreateInvoiceComponent implements OnInit {

  invoice: Invoice = new Invoice();
  submitted = false;
  isShow = false;
  // invoices: Invoice;
  item: Item = new Item();
  // item2: Item = new Item();
  location: any;
  files: File[];


  public addresses: FormArray;
  public addressForm: FormGroup;
  constructor(private fb: FormBuilder, private invoiceService: InvoiceService,
    private router: Router, private route: ActivatedRoute, private uploadService: UploadFilesService) {
    this.addressForm = this.fb.group({
      addresses: this.fb.array([this.createAddress()])
    });
  }
  ngOnInit() {
  
  }

  get addressControls() {
    return this.addressForm.get('addresses')['controls'];
  }

  createAddress(): FormGroup {
    return this.fb.group({
      name: this.item.name,
      description: this.item.description,
      price: this.item.price,
      currency: this.item.currency,
      quantity: this.item.quantity
    });
  }

  addAddress(): void {
    this.addresses = this.addressForm.get('addresses') as FormArray;
    this.addresses.push(this.createAddress());
    console.log(this.addresses.value);
  }

  removeAddress(i: number) {
    this.addresses.removeAt(i);
  }

  logValue() {
    console.log(this.addresses.value);
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
    // console.log(this.addresses.value);


    // this.loop();

    // console.log("array item", this.item)
    // console.log("array file", this.invoice.files)
    this.submitted = true;
    this.saveInvoice();
    console.log("invoice created");

  }
  toggleDisplay() {
    this.isShow = !this.isShow;
  }
  // loop(){
  //   for (let i = 0; i < this.invoice.items.length -1; i++) {
  //     this.invoice.items.push(this.item);
  //   }
  // }

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

