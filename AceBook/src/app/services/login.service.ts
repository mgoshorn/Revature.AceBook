import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class LoginService {

  body: any;

  constructor(private http: HttpClient) { }

  login(credentials) {
    this.body = JSON.stringify(credentials);
    this.http.post('localhost:/Acebook/users/login', this.body).subscribe(res => {
      console.log(res);
    });
  }


}
