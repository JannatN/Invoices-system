import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Location } from '@angular/common';
import { Invoice } from 'src/app/core/models/invoice';
import { Item } from 'src/app/core/models/item';
import { ItemService } from 'src/app/core/services/items.service';
import { InvoiceService } from 'src/app/core/services/invoices.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditComponent implements OnInit {
  @Input() dynamicForm: FormGroup;
  isAddMode: boolean;
  invoice: Invoice;
  item: Item = new Item();
  submitted = false;
  id: number;


  constructor(private formBuilder: FormBuilder, private invoiceService: InvoiceService, private itemService: ItemService,
    private router: Router, private route: ActivatedRoute, private location: Location) { }


  ngOnInit() {
    this.isAddMode = !this.id;
    this.id = this.route.snapshot.params['id'];
    if (this.isAddMode) {
    this.dynamicForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      currency: ['', Validators.required],
      quantity: ['', Validators.required],

    });
  }
  if (!this.isAddMode) {
    this.dynamicForm = this.formBuilder.group({
      name: ['name', Validators.required],
      description: ['description', Validators.required],
      price: ['price', Validators.required],
      currency: ['currency', Validators.required],
      quantity: ['quantity', Validators.required],

    });
  }
      this.invoice = new Invoice();
      this.invoiceService.getInvoice(this.id)
        .subscribe(data => {
          console.log("before", data)
          this.invoice = data;
        }, error => console.log(error));
    
        console.log(this.isAddMode)
    
  }
  get f() {
    return this.dynamicForm.controls;
  }
  get t() {
    return this.f.items as FormArray;

  }

  addItem() {
    this.id = this.route.snapshot.params['id'];
    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log(data.id)
        this.itemService
          .createItem2(this.dynamicForm.value, data.id).subscribe(data => {
            console.log(data)
            this.item = new Item();
          },
            error => console.log(error));

      })
  }
  updateInvoice() {
    this.invoiceService.updateInvoice(this.id, this.invoice)
      .subscribe(data => {
        console.log(data);
        this.invoice = new Invoice();

        this.back();
      }, error => console.log(error));
  }

  onSubmit() {
    this.submitted = true;
    this.addItem();

    if (this.dynamicForm.invalid) {
      return;
    }
    if (this.isAddMode) {
      this.addItem();
    } else {
      this.updateInvoice();
    }

    alert('SUCCESS!! \n\n The Invoice is created ! :-)\n\n');
    this.back();
    console.log(this.dynamicForm.value);

  }
  onReset() {
    this.submitted = false;
    this.dynamicForm.reset();
    this.t.clear();
  }


  createItem() {
    this.router.navigate(['addItem']);
  }

  back() {
    this.location.back();
  }
}


