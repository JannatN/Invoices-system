import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Invoice } from 'src/app/core/models/invoice';
import { Item } from 'src/app/core/models/item';
import { InvoiceService } from 'src/app/core/services/invoices.service';
import { UploadFilesService } from 'src/app/core/services/upload-file.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-add-edit',
  templateUrl: './add-edit.component.html',
  styleUrls: ['./add-edit.component.css']
})
export class AddEditComponent implements OnInit {

  dynamicForm: FormGroup;
  submitted = false;
  invoice: Invoice = new Invoice();
  item: Item = new Item();
  selectedFiles: FileList;
  progressInfos = [];
  message = '';
  id: number;
  isAddMode: boolean;

  constructor(private formBuilder: FormBuilder, private invoiceService: InvoiceService,
    private router: Router, private route: ActivatedRoute, private uploadService: UploadFilesService, private location: Location) { }

  ngOnInit() {
    this.id = this.route.snapshot.params['id'];
    this.isAddMode = !this.id;

    this.dynamicForm = this.formBuilder.group({
      numberOfItems: ['', Validators.required],
      items: new FormArray([])
    });
    if (!this.isAddMode) {
      this.invoice = new Invoice();


      this.invoiceService.getInvoice(this.id)
        .subscribe(data => {
          console.log("before", data)
          this.invoice = data;
        }, error => console.log(error));
    }
  }
  get f() {
    return this.dynamicForm.controls;
  }
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
  updateInvoice() {
    this.invoiceService.updateInvoice(this.id, this.invoice)
      .subscribe(data => {
        console.log(data);
        this.invoice = new Invoice();

        this.back();
      }, error => console.log(error));
  }

  saveInvoice() {
    this.invoiceService.createInvoice(this.invoice).subscribe(data1 => {
      console.log(data1['id'])

      this.invoice = new Invoice();
      this.message = '';
      for (let i = 0; i < this.selectedFiles.length; i++) {
        this.upload(i, this.selectedFiles[i], data1['id']);
      }
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
    this.invoice.items = [];
    this.invoice.files = [];
    for (let i = 0; i < 10; i++) {
      this.invoice.items.push(this.dynamicForm.value.items[i]);

    }
    // this.invoice.files.push(this.selectedFiles[0]);

    console.log("values", this.dynamicForm.value.items);
    console.log("invoiceeee", this.invoice);

    // this.file = this.selectedFiles
    // this.file.name = this.selectedFiles[0].name
    // this.file.type = this.selectedFiles[0].type
    // this.file.data = this.selectedFiles[0].datac

    // this.invoice.files.push(this.file);
    // console.log("filee", this.file);



    console.log("invoice", this.invoice);

    console.log("invoice created");
    if (this.dynamicForm.invalid) {
      return;
    }
    if (this.isAddMode) {
      this.saveInvoice();
    } else {
      this.updateInvoice();
    }
    // this.saveInvoice();
    alert('SUCCESS!! \n\n The Invoice is created ! :-)\n\n');
    this.back();

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
    this.location.back();
  }

}
