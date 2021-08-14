import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { Location } from '@angular/common';
import { Invoice } from 'src/app/core/models/invoice';
import { Item } from 'src/app/core/models/item';
import { ItemService } from 'src/app/core/services/items.service';
import { InvoiceService } from 'src/app/core/services/invoices.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

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
    private router: Router, private route: ActivatedRoute, private location: Location) { }

 
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

  // onChangeItems(e) {
  //   const numberOfItems = e.target.value || 0;
  //   if (this.t.length < numberOfItems) {
  //     for (let i = this.t.length; i < numberOfItems; i++) {
  //       this.t.push(this.formBuilder.group({
  //         name: ['', Validators.required],
  //         description: ['', Validators.required],
  //         price: ['', Validators.required],
  //         currency: ['', Validators.required],
  //         quantity: ['', Validators.required],

  //       }));
  //       console.log("num", numberOfItems);

  //     }

  //   } else {
  //     for (let i = this.t.length; i >= numberOfItems; i--) {
  //       this.t.removeAt(i);
  //     }
  //   }
  // }

  save() {
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
      console.log('ss',this.dynamicForm.getRawValue())
  }

  onSubmit() {
    this.submitted = true;
    this.save();
    
    if (this.dynamicForm.invalid) {
      return;
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

  onClear() {
    this.submitted = false;
    this.t.reset();
  }

  createItem() {
    this.router.navigate(['addItem']);
  }

  back() {
    this.location.back();
  }
}


