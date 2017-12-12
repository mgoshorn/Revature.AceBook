import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { environment } from '../../environments/environment';
import { Credentials } from '../models/credentials';
import { StorageService } from './storage.service';
import { User } from '../models/user';


@Injectable()
export class LoginService {

  user: User;

  constructor(private http: HttpClient, private router: Router, private storageService: StorageService) { }

  login(credentials: Credentials) {
    this.http.post(environment.context + 'users/login', credentials, {withCredentials: true})
    .subscribe( (success) => {
      if (success !== '') {
        this.storageService.getCredentialsFromLoginService(credentials);

        this.user = Object.assign(new User, success);
        this.storageService.setUser(this.user);

        this.router.navigateByUrl('profile');
      }
    }, (error) => {
      alert('failed to login');
    });
  }

}
