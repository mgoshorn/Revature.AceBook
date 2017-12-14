import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StorageService } from './storage.service';
import { environment } from '../../environments/environment';

@Injectable()
export class FriendService {

  constructor(private storageService: StorageService, private http: HttpClient) { }

  getFriends() {
    const user = this.storageService.getUser();
    return this.http.get(environment.context + 'users/friends/' + user.user_id.toString(), {withCredentials: true});
  }


}
