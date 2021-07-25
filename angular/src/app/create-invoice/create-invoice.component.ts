import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { UploadFilesService } from '../_services/upload-file.service';
import { File } from "../models/file"
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-invoice',
  templateUrl: './create-invoice.component.html',
  styleUrls: ['./create-invoice.component.css']
})
export class CreateInvoiceComponent implements OnInit {
  dynamicForm: FormGroup;
  submitted = false;

  invoice: Invoice = new Invoice();
  item: Item = new Item();
  location: any;
  array: [];

  constructor(private formBuilder: FormBuilder, private invoiceService: InvoiceService,
    private router: Router, private route: ActivatedRoute, private uploadService: UploadFilesService) { }

  ngOnInit() {
    this.dynamicForm = this.formBuilder.group({
      numberOfItems: ['', Validators.required],
      items: new FormArray([])
    });

  }
  get f() {
    return this.dynamicForm.controls;
  }
  get t() {
    return this.f.items as FormArray;

  }
  saveInvoice() {
    this.invoiceService.createInvoice(this.invoice).subscribe(data1 => {
      console.log(data1)
      this.invoice = new Invoice();
    })
  }

  onChangeItems(e) {
    const numberOfItems = e.target.value || 0;
    if (this.t.length < numberOfItems) {
      for (let i = this.t.length; i < numberOfItems; i++) {
        this.t.push(this.formBuilder.group({
          name: ['', Validators.required],
          description: ['', Validators.required],
          price: ['', Validators.required],
          currency: ['', Validators.required],
          quantity: ['', Validators.required],
        }));
      }
      this.invoice.items = [];
      for (let i = this.t.length; i < numberOfItems; i++) {
        this.invoice.items.push(this.dynamicForm.value.items[i]);

      }
      
    } else {
      for (let i = this.t.length; i >= numberOfItems; i--) {
        this.t.removeAt(i);
      }
    }
  }


  onSubmit() {
    this.submitted = true;
    this.invoice.items = [];
    for (let i = 0; i < 10; i++) {
      this.invoice.items.push(this.dynamicForm.value.items[i]);
    }

    console.log("items", this.invoice);
    console.log("values", this.dynamicForm.value.items);


    this.saveInvoice();
    console.log("invoice created");
    if (this.dynamicForm.invalid) {
      return;
    }

    // display form values on success
    alert('SUCCESS!! :-)\n\n' + JSON.stringify(this.dynamicForm.value, null, 4));


  }

  onReset() {
    // reset whole form back to initial state
    this.submitted = false;
    this.dynamicForm.reset();
    this.t.clear();
  }

  onClear() {
    // clear errors and reset ticket fields
    this.submitted = false;
    this.t.reset();
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


}

