import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

import { environment } from '../../environments/environment';
import { Credentials } from '../models/credentials';
import { User } from '../models/User';
import { StorageService } from './storage.service';

@Injectable()
export class LoginService {


  constructor(private http: HttpClient, private router: Router, private storageService: StorageService) { }

  login(credentials) {
    this.http.post<User>(environment.context + 'users/login', credentials, {withCredentials: true})
    .subscribe( (success) => {
      this.storageService.storedCredentials(credentials);
      console.log(success);
      this.storageService.storedUser(success);
      this.router.navigateByUrl('profile/' + success);
    }, (error) => {
      alert('failed to login');
    });
  }

}
