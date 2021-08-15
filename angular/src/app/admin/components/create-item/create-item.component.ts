import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Location } from '@angular/common';
import { Invoice } from 'src/app/core/models/invoice';
import { Item } from 'src/app/core/models/item';
import { ItemService } from 'src/app/core/services/items.service';
import { InvoiceService } from 'src/app/core/services/invoices.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TokenStorageService } from 'src/app/core/services/token-storage.service';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {
  dynamicForm: FormGroup;
  invoice: Invoice;
  item: Item = new Item();
  submitted = false;
  id: number;
  constructor(private formBuilder: FormBuilder, private invoiceService: InvoiceService, private itemService: ItemService,
    private router: Router, private route: ActivatedRoute, private location: Location, private token: TokenStorageService) { }


  ngOnInit() {
 
    this.dynamicForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      price: ['', Validators.required],
      currency: ['', Validators.required],
      quantity: ['', Validators.required],

    });

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
    console.log('ss', this.dynamicForm.getRawValue())
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

    alert('SUCCESS!! \n\n The item is added ! :-)\n\n');
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


