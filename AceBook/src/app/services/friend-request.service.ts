import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';
import { Credentials } from '../models/credentials';

@Injectable()
export class FriendRequestService {

  credentials: Credentials;

  constructor(private storageService: StorageService) { }

  getStoredCredentials() {
    this.credentials = this.storageService.getCredentials();
    console.log('credentails: ' + this.credentials);
  }

}
