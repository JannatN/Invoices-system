<<<<<<< HEAD:angular/src/app/_services/track.service.ts
// import { HttpClient, HttpHeaders } from "@angular/common/http";
// import { Injectable } from "@angular/core";
// import { Observable } from "rxjs";
=======
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { environment } from "src/environments/environment";
>>>>>>> 502195c3a1a2a417c791635125017299352a52ff:angular/src/app/core/services/track.service.ts

// @Injectable({
//     providedIn: 'root'
// })
// export class trackService {

<<<<<<< HEAD:angular/src/app/_services/track.service.ts
//     private baseUrl = 'http://localhost:8080/do/track';
=======
    private baseUrl = environment.history;
>>>>>>> 502195c3a1a2a417c791635125017299352a52ff:angular/src/app/core/services/track.service.ts

//     constructor(private http: HttpClient) { }

<<<<<<< HEAD:angular/src/app/_services/track.service.ts
//     getTracksList(): Observable<any> {
//         return this.http.get(`${this.baseUrl}`);
//     }
//     //  op = {
//     //     headers: new HttpHeaders({
//     //       'Accept': 'text/html',
//     //       'Content-Type': 'text/plain; charset=utf-8'
//     //     }),
//     //     responseType: 'text' as 'json'
//     //   };

//     createTrack(track: Object): Observable<Object> {
//         // let headers = new HttpHeaders({ 'Content-Type': 'application/json' });
//         return this.http.post(`${this.baseUrl}`, track)
//     }
=======
    getTracksList(): Observable<any> {
        return this.http.get(`${this.baseUrl}`);
    }
    createTrack(track: Object): Observable<Object> {
        return this.http.post(`${this.baseUrl}`, track)
    }
>>>>>>> 502195c3a1a2a417c791635125017299352a52ff:angular/src/app/core/services/track.service.ts

// }