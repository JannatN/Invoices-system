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
    createTrack(track: Object): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, track)
    }

}