import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { UploadFilesService } from '../_services/upload-file.service';
import { FileUp } from "../models/file"
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';

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
  file: FileUp = new FileUp();

  // location: any;
  idx:number
  selectedFiles: FileList;
  selectedFiles2: File;

  progressInfos = [];
  message = '';
  id: number;
  // fileInfos: Observable<any>;
locations :Location;
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

  // selectFiles(event) {
  //   this.progressInfos = [];
  //   this.selectedFiles = event.target.files;
  //   // this.selectedFiles2=event.target.files;
  //   console.log(this.selectedFiles)

  // }

  // upload(idx, file) {
  //   this.progressInfos[idx] = { value: 0, fileName: file.name };

  //   this.uploadService.upload(file,this.invoice).subscribe(
  //     event => {
  //       // this.invoice = new Invoice();
  //       if (event.type === HttpEventType.UploadProgress) {
  //         this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
  //       } else if (event instanceof HttpResponse) {
  //         // this.fileInfos = this.uploadService.getFiles();
  //       }
  //     },
  //     err => {
  //       this.progressInfos[idx].value = 0;
  //       this.message = 'Could not upload the file:' + file.name;
  //     });
  // }

  //   uploadFiles() {
  //     this.message = '';
  //     console.log("this.selectedFiles",this.selectedFiles)
  //     for (let i = 0; i < 100; i++) {

  //       this.upload(i, this.selectedFiles[i]);
  //       // this.upload(i,this.selectedFiles2[i])
  //     }
  //     console.log("this.selectedFiles",this.selectedFiles)

  //   }
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
    // this.invoice.files.push(this.file);
    // console.log("filee", this.file);

    this.saveInvoice();
    console.log("invoice created");

console.log("after save invoice ",this.invoice)

    if (this.dynamicForm.invalid) {
      return;
    }



// this. uploadFiles();



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
    this.router.navigate(['/admin']);
  }

  createItem() {
    this.router.navigate(['addItem']);
  }


  // list() {
  //   this.location.back();
  // }


}