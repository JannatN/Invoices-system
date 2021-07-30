import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpParams, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { _MatPaginatorBase } from '@angular/material/paginator';
import { Invoice } from '../models/invoice';

@Injectable({
    providedIn: 'root'
})
export class InvoiceService {
    private baseUrl = 'http://localhost:8080/api/v1/invoices';

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

    createInvoice(invoice: Object): Observable<Object> {
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


    createInv( file: File, invoice: Invoice): Observable<HttpEvent<any>> {
        const formData: FormData = new FormData();
        // formData.append('invoice', new Blob([JSON.stringify(invoice)], {
        //     type: "application/json"
        // }));
        // formData.append('invoice', JSON.stringify(invoice))
        formData.append('type', JSON.stringify(invoice.type));
        // // formData.append('due_date', JSON.stringify(invoice.due_date));
        formData.append('company', JSON.stringify(invoice.company));
        formData.append('userid', JSON.stringify(invoice.userid));
        // formData.append('items', JSON.stringify(invoice["items"]));
        // for (var i = 0; i < invoice.items.length; i++) {
        // formData.append('items[]', JSON.stringify(invoice["items"]["description"]));
        // formData.append('items[]', JSON.stringify(invoice["items"]["currency"]));

        
        // formData.append('type', JSON.stringify(invoice.files));
        formData.append('file', file)


        // formData.append('file', new Blob([file], {
        //     type: "application/json"
        // }));
        // console.log('ffff', file);
        // console.log('iiii', invoice);

        const req = new HttpRequest('POST', `${this.baseUrl}`, formData, {
            reportProgress: true,
            responseType: 'json'
        });
        return this.http.request(req);
    }

    getFiles(invoiceID: number): Observable<File[]> {
        return this.http.get<File[]>(`${this.baseUrl}/files/${invoiceID}`);
    }

    getFile(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/files/${id}`);
    }

}
