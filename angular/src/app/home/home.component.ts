import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Validators, AbstractControl, FormBuilder, FormGroup, FormControl , Validator , FormsModule} from '@angular/forms';
import { Observable, Subject , Subscription, BehaviorSubject } from 'rxjs';
// import { CheckRequiredField } from '../../_shared/helpers/form.helper';
import { Invoice } from '../models/invoice';

// import { ItemsService } from '../_services/items.service';
import { InvoiceService } from "../_services/invoices.service";
// import { FormControl } from '@angular/forms';

// import { ItemModel } from '../_models/item.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
  
})
export class HomeComponent implements OnInit { 







    @Input() item: Invoice;
    @Output() formSubmitEvent = new EventEmitter<string>();
  
    itemForm: FormGroup;
  
    isProcessing: Boolean = false;
    CheckRequiredField(field: AbstractControl): boolean {
        return field.hasError('required') && (field.dirty || field.touched);
    }
    checkField  = this.CheckRequiredField;
  
    constructor(
      private itemsService: InvoiceService
    ) { }
  
    ngOnInit() {
      this.initForm();
    }
  
    onSubmit($event) {
  
      this.isProcessing  = true;
  
      if (this.itemForm.valid) {
          if (!this.item) {
            this.doAddItem();
          } else {
            this.doUpdateItem();
          }
      }
    }
  
    getButtonText(): string {
      return this.item ? 'Update' : 'Add';
    }
  
    private doAddItem() {
      this.itemsService.createInvoice(this.itemForm.value).subscribe(
        (result) => {
          this.itemForm.reset();
          this.formSubmitEvent.next('add');
          this.isProcessing  = false;
        }
      );
    }
  
    private doUpdateItem() {
      this.itemsService.updateInvoice(this.itemForm.value.id , this.itemForm.value).subscribe(
        (result) => {
          if (result) {
            this.formSubmitEvent.next('update');
            this.reset();
          }
          this.isProcessing  = false;
        }
      );
    }
  
    private reset() {
      this.item  = null;
      this.itemForm.reset();
      this.initForm();
    }
  
    private initForm() {
      this.itemForm = new FormGroup({
        company: new FormControl(this.item ? this.item.company : '', Validators.required),
        type: new FormControl(this.item ? this.item.type : ''),
        id: new FormControl(this.item ? this.item.id : null),
      });
    }
  
  }