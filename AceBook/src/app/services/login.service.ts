import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { environment } from '../../environments/environment';
import { Credentials } from '../models/credentials';
import { User } from '../models/User';
import { StorageService } from './storage.service';


@Injectable()
export class LoginService {

  user: User;

  constructor(private http: HttpClient, private router: Router, private storageService: StorageService) { }

  login(credentials) {
    this.http.post<User>(environment.context + 'users/login', credentials, {withCredentials: true})
    .subscribe( (success) => {
      if (success !== '') {
        this.storageService.setCredentials(credentials);

        this.user = Object.assign(new User, success);
        this.storageService.setUser(this.user);

        this.router.navigateByUrl('profile/' + this.user.user_id);
      }
    }, (error) => {
      console.log('error');
      alert('failed to login');
    });
  }

}
