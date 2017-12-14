import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { HttpClient, HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { User } from '../models/user';
import { Observable } from 'rxjs/Observable';
import { last } from '@angular/router/src/utils/collection';

@Injectable()
export class FriendService {

  user: User;
  friends: User[];
  tempArray: any;

  constructor(private storageService: StorageService, private http: HttpClient) { }

  getFriends() {
    const user = this.storageService.getUser();
    return this.http.get(environment.context + 'users/friends/' + user.user_id.toString(), {withCredentials: true});
  }


}
