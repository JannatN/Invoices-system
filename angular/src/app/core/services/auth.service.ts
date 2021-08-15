import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

const AUTH_API = environment.auth;

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private currentUserSubject: BehaviorSubject<User>;
  public currentUser: Observable<User>;

  constructor(private http: HttpClient) { 
    this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(sessionStorage.getItem('auth-user')));
    this.currentUser = this.currentUserSubject.asObservable();
  }
  

  public get currentUserValue(): User {
    return this.currentUserSubject.value;
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
   isLoggedIn() {
    return !!sessionStorage.getItem('auth-user');
}
}
