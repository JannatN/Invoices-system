import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';
import { TokenStorageService } from './token-storage.service';

const AUTH_API = 'http://localhost:8080/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  token: string; 
  constructor(private http: HttpClient,private router: Router) { }

  authenticate(){
    this.token = sessionStorage.get("auth-token");
    this.http.post<any>("auth-token",{token: this.token})
    .subscribe((data)=>{
        //do nothing        
    },
    (err)=>{
        this.router.navigate(["/"]);   
        sessionStorage.removeItem("auth-token")
    })          
}   


  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      email: user.email,
      firstname: user.firstname,
      lastname: user.lastname,
      address: user.address,
      phoneNumber: user.phoneNumber,
      password: user.password
    }, httpOptions);
  }
}
