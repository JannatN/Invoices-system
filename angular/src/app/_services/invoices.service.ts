import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { _MatPaginatorBase } from '@angular/material/paginator';
import {FileUp} from "../models/file"
@Injectable({
    providedIn: 'root'
})
export class InvoiceService {

    private baseUrl = 'http://localhost:8080/api/invoices';

    constructor(private http: HttpClient) { }

    getInvoice(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    updateInvoice(id: number, value: any): Observable<Object> {
        return this.http.put(`${this.baseUrl}/${id}`, value);
    }

    // getInvoicesList(): Observable<any> {
    //     return this.http.get(`${this.baseUrl}`);
    // }

    createInvoice(invoice: Object): Observable<object> {
      
        // const formData: FormData = new FormData();
    
        // formData.append('file', file);

        return this.http.post(`${this.baseUrl}`, invoice );

        // const req = new HttpRequest('POST',`${this.baseUrl}`, invoice, formData );
      
        //   return this.http.request(req);
    }


    // getInvoices(page: number) {
    //     return this.http.get(`${this.baseUrl + page}`);
    // }
    // findPaginated(){
    //     return this.http.get(`${this.baseUrl}`);
    //   }
    listInv(request) {
        const params = request;
        return this.http.get(`${this.baseUrl}`, { params });
      }

    deleteInvoice(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
    }
}
