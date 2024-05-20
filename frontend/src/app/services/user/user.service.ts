import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User, User2 } from '../../models/User';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private apiUrl: string = 'http://localhost:8080/api/v1';

  constructor(private http: HttpClient) {}

  public getUserById(id: string): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/users/${id}`);
  }

  public saveUser(user: User2): Observable<any> {
    return this.http.post<User2>(`${this.apiUrl}/users/`, user);
  }

  public deleteUserById(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/users/${id}`);
  }
}
