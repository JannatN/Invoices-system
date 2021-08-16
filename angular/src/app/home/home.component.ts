import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Validators, AbstractControl, FormBuilder, FormGroup, FormControl , Validator , FormsModule, FormArray} from '@angular/forms';
import { Observable, Subject , Subscription, BehaviorSubject } from 'rxjs';
import { Invoice } from '../models/invoice';

import { InvoiceService } from "../_services/invoices.service";
import { Router, ActivatedRoute } from '@angular/router';
import { first } from 'rxjs/operators';
import { Item } from '../models/item';
import { UploadFilesService } from '../_services/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
  
})
export class HomeComponent implements OnInit { 



    form: FormGroup;
    id: any;
    isAddMode: boolean;
    loading = false;
    submitted = false;
    invoice: Invoice = new Invoice();
    dynamicForm: FormGroup;
 
    item: Item = new Item();
  
    selectedFiles: FileList;
    progressInfos = [];
    message = '';
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService: InvoiceService,
      private invoiceService: InvoiceService,
     private uploadService: UploadFilesService
    //  ,  private location: Location
        // private alertService: AlertService
    ) {}

    ngOnInit() {

        this.dynamicForm = this.formBuilder.group({
            numberOfItems: ['', Validators.required],
            items: new FormArray([])
          });
        this.id = this.route.snapshot.params['id'];
        this.isAddMode = !this.id;
        
        // password not required in edit mode
        // const passwordValidators = [Validators.minLength(6)];
        // if (this.isAddMode) {
        //     passwordValidators.push(Validators.required);
        // }
      



        this.form = this.formBuilder.group({
            company: ['', Validators.required],
            type: ['', Validators.required],
            due_date:['', Validators.required]
            // lastName: ['', Validators.required],
            // email: ['', [Validators.required, Validators.email]],
            // role: ['', Validators.required],
            // password: ['', [Validators.minLength(6), this.isAddMode ? Validators.required : Validators.nullValidator]],
            // confirmPassword: ['', this.isAddMode ? Validators.required : Validators.nullValidator]
        }
        // , {
        //     validator: MustMatch('password', 'confirmPassword')
        // }
        
        );

        if (!this.isAddMode) {
            this.userService.getInvoice(this.id)
                .pipe(first())
                .subscribe(x => this.form.patchValue(x));
        }
    }

    // convenience getter for easy access to form fields
    get f() { return this.form.controls; }

    get l() {
        return this.dynamicForm.controls;
      }
      get t() {
        return this.l.items as FormArray;
    
      }
    

      selectFiles(event) {
        this.progressInfos = [];
        this.selectedFiles = event.target.files;
        console.log(this.selectedFiles)
    
      }
      upload(idx, file, id) {
        this.progressInfos[idx] = { value: 0, fileName: file.name };
        this.uploadService.upload(file, id).subscribe(
          event => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              // this.fileInfos = this.uploadService.getFiles();
            }
          },
          err => {
            this.progressInfos[idx].value = 0;
            this.message = 'Could not upload the file:' + file.name;
          });
    
      }

      onChangeItems(e) {
        const numberOfItems = e.target.value || 0;
        this.dynamicForm = this.formBuilder.group({
            numberOfItems: ['', Validators.required],
            items: new FormArray([])
          });
        if (this.t.length < numberOfItems) {
          for (let i = this.t.length; i < numberOfItems; i++) {
            this.t.push(this.formBuilder.group({
              name: ['', Validators.required],
              description: ['', Validators.required],
              price: ['', Validators.required],
              currency: ['', Validators.required],
              quantity: ['', Validators.required],
    
            }));
            console.log("num", numberOfItems);
    
          }
    
        } else {
          for (let i = this.t.length; i >= numberOfItems; i--) {
            this.t.removeAt(i);
          }
        }
      }


    onSubmit() {
        this.submitted = true;

        // reset alerts on submit
        // this.alertService.clear();

        // stop here if form is invalid
      

        this.loading = true;
        if (this.isAddMode) {
            this.createUser();
              if (this.form.invalid) {
            return;
        }
            this.invoice.items = [];
            this.invoice.files = [];
            for (let i = 0; i < 10; i++) {
                console.log("line 175   mmmmmm")
              this.invoice.items.push(this.dynamicForm.value.items[i]);
        
            }
            console.log("this.invoice.items",this.invoice.items)
            if (this.dynamicForm.invalid) {
                return;
              }
          
        } else {
            this.updateUser();
        }


        // this.submitted = true;
       
    
        // console.log("values", this.dynamicForm.value.items);
        // console.log("invoiceeee", this.invoice);
    
     
    
    
    
        // console.log("invoice", this.invoice);
    
        // console.log("invoice created");
      
        // alert('SUCCESS!! \n\n The Invoice is created ! :-)\n\n');
        this.back();

    }

    private createUser() {
        this.userService.createInvoice(this.form.value)
            .pipe(first())
            .subscribe(data=>{
                this.invoice = new Invoice();
                this.message = '';
                for (let i = 0; i < this.selectedFiles.length; i++) {
                  this.upload(i, this.selectedFiles[i], data['id']);
                }
                
                // next: () => {
                    
                //     // this.alertService.success('User added', { keepAfterRouteChange: true });
                //     this.router.navigate(['../'], { relativeTo: this.route });

                // }
                
                // ,
                error: error => {
                    // this.alertService.error(error);
                    this.loading = false;
                }
            });
    }

    private updateUser() {
        this.userService.updateInvoice(this.id, this.form.value)
            .pipe(first())
            .subscribe({
                next: () => {
                    // this.alertService.success('User updated', { keepAfterRouteChange: true });
                    this.router.navigate([''], { relativeTo: this.route });
                },
                error: error => {
                    // this.alertService.error(error);
                    this.loading = false;
                }
            });
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
    
      gotoList() {
        this.router.navigate(['/invoices']);
      }
    
      createItem() {
        this.router.navigate(['addItem']);
      }
      back() {
        this.router.navigate(['/invoices']);
      }
}
