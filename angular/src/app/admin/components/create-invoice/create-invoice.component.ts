import { Invoice } from '../../../core/models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../../../core/services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../../../core/models/item';
import { UploadFilesService } from '../../../core/services/upload-file.service';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BehaviorSubject, Observable } from 'rxjs';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Location } from '@angular/common';
import { User } from 'src/app/core/models/user';
import { UserService } from 'src/app/core/services/users.service';

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
  selectedFiles: FileList;
  progressInfos = [];
  message = '';
  id: number;
  users = new BehaviorSubject([]);
  users$ = this.users.asObservable();
  user: User = new User();
  // fileInfos: Observable<any>;

  constructor(private formBuilder: FormBuilder, private invoiceService: InvoiceService, private userService: UserService,
    private router: Router, private uploadService: UploadFilesService, private location: Location) { }

  ngOnInit() {
    this.userService.getUserList().subscribe(e => {
      debugger
      this.users.next(e.content)
    }

    );
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
  processSelectedItem(u) {
    this.user.username = u.username;
    this.user.id = u.id;
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
  // uploadFiles() {
  //   this.message = '';
  //   for (let i = 0; i < this.selectedFiles.length; i++) {
  //     this.saveInvoice(i, this.selectedFiles[i]);
  //     console.log("files", this.selectedFiles[i])
  //   }
  // }

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

    this.saveInvoice();
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