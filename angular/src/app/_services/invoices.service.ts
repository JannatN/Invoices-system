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


    // createInv(file: File, invoice: Invoice): Observable<HttpEvent<any>> {
    //     // const formArray = new FormArray(null);
    //     // formData.append('invoice', new Blob([JSON.stringify(invoice)], {
    //     //     type: "application/json"
    //     // formData.append('type', JSON.stringify(invoice));
    //     // }));
    //     // formData.append('invoice', JSON.stringify(invoice))

    //     // this.formData.append('type', JSON.stringify(invoice.type));

    //     // this.item=JSON.stringify(invoice)
    //     // this.item={"invoice":invoice.items}
    //     // this.formData.set('invoice[items]',JSON.stringify(invoice.items));

    //     //    this. formData.append('items', JSON.stringify(invoice.items));

    //     this.formData.append('type', JSON.stringify(invoice.type));

    //     // formData.append('due_date', JSON.stringify(invoice.due_date));
    //     // formData.append('date_created', JSON.stringify(invoice.date_created));


    //     this.formData.append('company', JSON.stringify(invoice.company));
    //     this.formData.append('userid', JSON.stringify(invoice.userid));




    //     this.formData.append('file', file)


    //     const req = new HttpRequest('POST', `${this.baseUrl}`, this.formData, {
    //         reportProgress: true,
    //         responseType: 'json',


    //     });
    //     return this.http.request(req);
    // }

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
