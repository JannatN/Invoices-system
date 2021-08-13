import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = 'http://localhost:8080/api/users';


  constructor(private http: HttpClient) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.baseUrl);
  }

  getUser(id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${id}`);
  }

  updateUser(id: number, value: any): Observable<Object> {
    return this.http.put(`${this.baseUrl}/${id}`, value);
  }

  getUserList(): Observable<any> {
    return this.http.get(`${this.baseUrl}`);
  }

  listUsers(request) {
    const params = request;
    return this.http.get(`${this.baseUrl}`, { params });
  }

  findByUsername(username: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}?username=${username}`);
  }
  deleteUser(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${id}`, { responseType: 'text' });
  }

}
