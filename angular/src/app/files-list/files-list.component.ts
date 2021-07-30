import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UploadFilesService } from '../_services/upload-file.service';

@Component({
  selector: 'app-files-list',
  templateUrl: './files-list.component.html',
  styleUrls: ['./files-list.component.css']
})
export class FilesListComponent implements OnInit {
  fileInfos: Observable<File[]>;
  file: File[]
  constructor(private uploadService: UploadFilesService) { }

  ngOnInit() {
    this.fileInfos = this.uploadService.getFiles();
    console.log(this.fileInfos)
  }

}
