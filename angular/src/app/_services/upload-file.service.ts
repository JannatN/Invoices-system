import { Injectable } from '@angular/core';
import { HttpClient, HttpRequest, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
// import {File} from "../models/file"
@Injectable({
  providedIn: 'root'
})
export class UploadFilesService {

  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  // upload(file: File): Observable<object> {
  //   // const formData: FormData = new FormData();

  //   // formData.append('file', file);

  //   const req = new HttpRequest('POST', `${this.baseUrl}/upload`, file, {
  //     reportProgress: true,
  //     responseType: 'json'
  //   });

  //   return this.http.request(req);
  //   // return this.http.post(`${this.baseUrl}/upload`,file);
  // }
  upload(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }
  uploadFile(file: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();

    formData.append('file', file);

    const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
      reportProgress: true,
      responseType: 'json'
    });

    return this.http.request(req);
  }


  getFiles(): Observable<File[]> {
    return this.http.get<File[]>(`${this.baseUrl}/files`);
  }

  getFile(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/files/${id}`);
  }

}