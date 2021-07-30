import { Component, OnInit } from '@angular/core';
import { UploadFilesService } from '../_services/upload-file.service';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { InvoiceService } from '../_services/invoices.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-upload-files',
  templateUrl: './upload-files.component.html',
  styleUrls: ['./upload-files.component.css']
})
export class UploadFilesComponent implements OnInit {

  selectedFiles: FileList;
  progressInfos = [];
  message = '';
  id: number;

  fileInfos: Observable<any>;

  constructor(private uploadService: UploadFilesService, private invoiceService: InvoiceService, private router: Router, private route: ActivatedRoute,) { }

  ngOnInit() {
    // this.fileInfos = this.uploadService.getFiles();
  }

  selectFiles(event) {
    this.progressInfos = [];
    this.selectedFiles = event.target.files;
    console.log("filesss", this.selectedFiles)

  }

  upload(idx, file) {
    this.id = this.route.snapshot.params['id'];
    this.progressInfos[idx] = { value: 0, fileName: file.name };
    this.invoiceService.getInvoice(this.id)
      .subscribe(data => {
        console.log(data.id)
        this.uploadService.upload(file).subscribe(
          event => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progressInfos[idx].value = Math.round(100 * event.loaded / event.total);
              console.log("fileeeeeeee", file)

            } else if (event instanceof HttpResponse) {
              // this.fileInfos = this.uploadService.getFiles();
            }
          },
          err => {
            this.progressInfos[idx].value = 0;
            this.message = 'Could not upload the file:' + file.name;
          });
        })

      }
  uploadFiles() {
        this.message = '';

        for(let i = 0; i< this.selectedFiles.length; i++) {
      this.upload(i, this.selectedFiles[i]);
      console.log("files", this.selectedFiles[i])
    }
  }
}