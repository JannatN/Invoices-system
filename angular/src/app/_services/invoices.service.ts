import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

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

    getInvoicesList(): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
    }

    createInvoice(invoice: Object): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, invoice);
    }

    // getInvoices(page: number) {
    //     return this.http.get(`${this.baseUrl + page}`);
    // }
    findPaginated(request) {
        const params = request;
        return this.http.get(`${this.baseUrl}/${params}`);
      }

    deleteInvoice(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
    }
}
