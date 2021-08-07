import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class ItemService {
    invoiceID: number
    private baseUrl = 'http://localhost:8080/api/items';


    constructor(private http: HttpClient) { }

    getItem(id: number): Observable<any> {
        return this.http.get(`${this.baseUrl}/${id}`);
    }

    createItem(item: Object): Observable<Object> {

        return this.http.post(`${this.baseUrl}`, item);
    }
    createItem2(item: Object, id: number): Observable<Object> {

        return this.http.post(`${this.baseUrl}/${id}`, item);
    }


}
