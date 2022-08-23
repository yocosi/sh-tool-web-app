import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  baseUrl: string = 'http://localhost:8080/api/v1/registration';
  constructor(private httpClient: HttpClient) { }

  registerUser(user: User): Observable<Object> {
    console.log(user);
    let httpOptions = {
      headers: new HttpHeaders({
        "Content-Type": "application/json",
      })
    };
    return this.httpClient.post(this.baseUrl, user, httpOptions);
  }

}
