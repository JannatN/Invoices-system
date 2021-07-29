import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { _MatPaginatorBase } from '@angular/material/paginator';
import { Invoice } from '../models/invoice';

@Injectable({
    providedIn: 'root'
})
export class InvoiceService {

    private baseUrl = 'http://localhost:8080/api/invoices';

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

    listInv(request) {
        const params = request;
        console.log('hhho', params);
        return this.http.get(`${this.baseUrl}`, { params });
    }
    filter(): Observable<Invoice[]> {
        return this.http.get<Invoice[]>(`${this.baseUrl}`);
    }

    deleteInvoice(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
    }
    uploadFile(file: File): Observable<HttpEvent<any>> {
        const formData: FormData = new FormData();
    
        formData.append('file', file);
    
        const req = new HttpRequest('POST', `${this.baseUrl}`, formData, {
          reportProgress: true,
          responseType: 'json'
        });
    
        return this.http.request(req);
      }
}
