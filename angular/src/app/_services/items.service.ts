import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ItemService {
invoiceID:number
    private baseUrl = 'http://localhost:8080/api/v1/items';
    private baseUrl2 = 'http://localhost:8080/api/v1/items/last';


    constructor(private http: HttpClient) { }

    getItem(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }
    getLastItem(): Observable<any> {
        return this.http.get(`${this.baseUrl2}`);
    }
    updateItem(id: number, value: any): Observable<Object> {
        return this.http.put(`${this.baseUrl}/${id}`, value);
    }

    getItemsList(): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
    }

    createItem(item: Object): Observable<Object> {
       
        return this.http.post(`${this.baseUrl}`, item);
    }
    
    createItem2(item: Object,id: number): Observable<Object> {
       
        return this.http.post(`${this.baseUrl}/${id}`, item);
    }

    deleteItem(id: number): Observable<any> {
        return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
    }
}
