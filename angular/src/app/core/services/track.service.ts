import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";

@Injectable({
    providedIn: 'root'
})
export class trackService {

    private baseUrl = environment.history;

    constructor(private http: HttpClient) { }

    getTracksList(): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
    }
    //  op = {
    //     headers: new HttpHeaders({
    //       'Accept': 'text/html',
    //       'Content-Type': 'text/plain; charset=utf-8'
    //     }),
    //     responseType: 'text' as 'json'
    //   };

    createTrack(track: Object): Observable<Object> {
        // let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
        return this.http.post(`${this.baseUrl}`, track)
    }

}