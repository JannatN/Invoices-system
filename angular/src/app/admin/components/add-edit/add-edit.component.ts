import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Invoice } from 'src/app/core/models/invoice';
import { Item } from 'src/app/core/models/item';
import { InvoiceService } from 'src/app/core/services/invoices.service';
import { UploadFilesService } from 'src/app/core/services/upload-file.service';
import { Location } from '@angular/common';
import { first } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { UserService } from 'src/app/core/services/users.service';

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditComponent implements OnInit {
  form: FormGroup;
  id: any;
  isAddMode: boolean;
  loading = false;
  submitted = false;
  // invoice: Invoice = new Invoice();
  // dynamicForm: FormGroup;
  users = new BehaviorSubject([]);
  users$ = this.users.asObservable();
  item: Item = new Item();

  selectedFiles: FileList;
  progressInfos = [];
  message = '';
  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private invoiceService: InvoiceService,
    // private invoiceService: InvoiceService,
    private uploadService: UploadFilesService,
    private userService: UserService
    //  ,  private location: Location
    // private alertService: AlertService
  ) { }

  ngOnInit() {
    this.userService.getUserList().subscribe(e => {
      debugger
      this.users.next(e.content)
    }
    );
    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;
    this.form = this.formBuilder.group({
      company: ['', Validators.required],
      type: ['', Validators.required],
      due_date: ['', Validators.required],
      userid: ['', Validators.required],
      numberOfItems: ['', Validators.required],
      items: new FormArray([])
    }

    );

    if (!this.isAddMode) {
      this.invoiceService.getInvoice(this.id)
        .pipe(first())
        .subscribe(x => {
          x.numberOfItems = x.items.length
          for (let i = 0; i < x.numberOfItems; i++) {
            this.t.push(this.formBuilder.group({
              id: [''],
              name: ['', Validators.required],
              description: ['', Validators.required],
              price: ['', Validators.required],
              currency: ['', Validators.required],
              quantity: ['', Validators.required],

            }));
            // console.log("num", numberOfItems);

          }
          debugger
          this.form.patchValue(x)
        });
    }



  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  // get l() {
  //   return this.dynamicForm.controls;
  // }
  get t() {
    return this.f.items as FormArray;

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
      this.form.value.items = [];
      this.form.value.files = [];
      for (let i = 0; i < 10; i++) {
        console.log("line 175   mmmmmm")
        this.form.value.items.push(this.form.value.items[i]);

      }
      console.log("this.invoice.items", this.form.value.items)
      if (this.form.invalid) {
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
    this.invoiceService.createInvoice(this.form.value)
      .pipe(first())
      .subscribe(data => {
        // this.invoice = new Invoice();
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
    this.invoiceService.updateInvoice(this.id, this.form.value)
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
    this.form.reset();
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