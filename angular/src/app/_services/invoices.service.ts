import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { _MatPaginatorBase } from '@angular/material/paginator';
import { Invoice } from '../models/invoice';
import { Item } from '../models/item';
import { FormArray, FormBuilder } from '@angular/forms';

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

    createInvoice(invoice: Invoice): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, invoice);
    }

    listInv(request) {
        const params = request;
        return this.http.get(`${this.baseUrl}`, { params });
    }

    filter(page: number, size: number, keyword: Object): Observable<Invoice[]> {
        let params = new HttpParams();
        return this.http.get<Invoice[]>(`${this.baseUrl}`);
    }

    deleteInvoice(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
    }


    createInv(  invoice: Invoice): Observable<Object> {


        return this.http.post(`${this.baseUrl}`,invoice);
    }

    getFiles(invoiceID: number): Observable<File[]> {
        return this.http.get<File[]>(`${this.baseUrl}/files/${invoiceID}`);
    }

    getFile(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/files/${id}`);
    }

}
