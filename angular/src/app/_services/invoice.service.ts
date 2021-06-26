

import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Invoice } from '../models/invoice.model';


@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  private baseUrl = 'http://localhost:8080/api/invoices';


  constructor(private http: HttpClient) { }


  getInvList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }



  // getAll(): Observable<Invoice[]> {
  //   return this.http.get<Invoice[]>(baseUrl);
  // }
}