import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { _MatPaginatorBase } from '@angular/material/paginator';
import { Invoice } from '../models/invoice';
import { Item } from '../models/item';
import { FormArray, FormBuilder } from '@angular/forms';
import { catchError, map } from 'rxjs/operators';

@Injectable({
    providedIn: 'root'
})
export class InvoiceService {

    private baseUrl = 'http://localhost:8080/api/invoices';
    item;
    formData: FormData = new FormData();

    constructor(private http: HttpClient) { }

    getInvoice(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    getInvoiceAud(): Observable<any> {
        return this.http.get(`${this.baseUrl}/Aud`);
    }
    getInvoiceAudById(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/Aud/${id}`);
    }

    updateInvoice(id: number, value: any): Observable<Object> {
        return this.http.put(`${this.baseUrl}/${id}`, value);
    }

    getInvoicesList(): Observable<any> {
        return this.http.get(`${this.baseUrl}/All`);
    }

    createInvoice(invoice: Object): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, invoice);
    }

    // listInv(request) {
    //     const params = request;
    //     return this.http.get(`${this.baseUrl}`, { params });
    // }
    listInv(request) {
        const params = request;
        return this.http.get(`${this.baseUrl}`, { params });
    }
    
    paginate(page: number, size: number, key: string): Observable<object> {
        let params = new HttpParams();
    
        params = params.append('page', String(page));
        params = params.append('limit', String(size));
        params = params.append('key', key);
    
        return this.http.get<Invoice>(`${this.baseUrl}`, { params });
      }

    deleteInvoice(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
    }



    getFiles(invoiceID: number): Observable<File[]> {
        return this.http.get<File[]>(`${this.baseUrl}/files/${invoiceID}`);
    }

    getFile(id: string): Observable<any> {
        return this.http.get(`${this.baseUrl}/file/${id}`);
    }

    upload(file: File): Observable<HttpEvent<any>> {
        const formData: FormData = new FormData();

        formData.append('file', file);

        const req = new HttpRequest('POST', `${this.baseUrl}/upload`, formData, {
            reportProgress: true,
            responseType: 'json'
        });

        return this.http.request(req);
    }

}
