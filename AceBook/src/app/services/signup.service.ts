import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { environment } from '../../environments/environment';
import { FormGroup } from '@angular/forms/src/model';

@Injectable()
export class SignupService {

  constructor(private http: HttpClient, private router: Router) { }

  signup(signup) {
    console.log('in signup() of SignupService');
    console.log(signup);
    this.http.post(environment.context + 'users/signup', signup, {withCredentials: true})
    .subscribe( (success) => {
      if (success !== '') {
        console.log('debug');
        this.router.navigateByUrl('login');
      }
    }, (error) => {
      alert('failed to sign up');
      console.log(error);
    });
  }

}
