import { Injectable } from '@angular/core';
import { Credentials } from '../models/credentials';

import { StorageService } from '../services/storage.service';

import { environment } from '../../environments/environment';
import { User } from '../models/user';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class FriendRequestService {

  credentials: Credentials = this.storageService.getCredentials();
  user: User = this.storageService.getUser();

  constructor(private storageService: StorageService, private http: HttpClient) { }

  getStoredCredentials() {
  }

  getFriendRequests() {
    // const user = this.storageService.getUser();
    return this.http.get(environment.context + 'users/friendrequests/' + this.user.user_id.toString(), {withCredentials: true});
  }

  acceptRequest(userId) {
    return this.http.post(environment.context + 'friendrequest/accept/' + userId, this.credentials, {withCredentials: true});
  }

  sendFriendRequest(userId) {
    return this.http.post(environment.context + 'friendrequest/request/' + userId, this.credentials, {withCredentials: true});
  }
}
