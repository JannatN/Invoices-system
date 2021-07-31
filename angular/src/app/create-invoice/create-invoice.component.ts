import { Invoice } from '../models/invoice';
import { Component, OnInit, Input } from '@angular/core';
import { InvoiceService } from "../_services/invoices.service";
import { ActivatedRoute, Router } from '@angular/router';
import { Item } from '../models/item';
import { UploadFilesService } from '../_services/upload-file.service';
import { File } from "../models/file"
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
  file: File = new File();

  location: any;

  selectedFiles: FileList;
  progressInfos = [];
  message = '';
  id: number;
  // invoiceId:number;
  // fileInfos: Observable<any>;

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

  upload(idx, file,id) {
    // this.id = this.route.snapshot.params['id'];
    this.progressInfos[idx] = { value: 0, fileName: file.name };
    // this.invoiceService.getInvoice(this.id)
    //   .subscribe(data => {
    //     console.log(data.id)
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
        // })

      }
 

  saveInvoice() {
    this.invoiceService.createInvoice(this.invoice).subscribe(data1 => {
      console.log(data1['id'])
      
      this.invoice = new Invoice();
      this.invoiceService.getLastInvoice().subscribe(data2=>{
        console.log("last invoice",data2)
     this.id=data2.id
       
        this.message = '';

        for(let i = 0; i< this.selectedFiles.length; i++) {
      this.upload(i, this.selectedFiles[i],data1['id']);
    }
  
      
      
      })

 } )
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
 

    if (this.dynamicForm.invalid) {
      return;
    }

 this.saveInvoice();

// console.log(this.invoice.id)
// this.uploadFiles();




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




  selectFiles(event) {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
  }

 
  // uploadFiles() {
  //       this.message = '';

  //       for(let i = 0; i< this.selectedFiles.length; i++) {
  //     this.upload(i, this.selectedFiles[i],this.id);
  //   }
  // }

}