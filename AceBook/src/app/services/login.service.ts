import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { environment } from '../../environments/environment';

@Injectable()
export class LoginService {

  constructor(private http: HttpClient, private router: Router) { }

  login(credentials) {
    this.http.post(environment.context + 'users/login', credentials, {withCredentials: true})
    .subscribe( (success) => {
      if (success !== '') {
        this.router.navigateByUrl('home');
      } else {
        alert('failed to login');
      }
    });
  }


}
