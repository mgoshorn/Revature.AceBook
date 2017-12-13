import { Injectable } from '@angular/core';
import { Credentials } from '../models/credentials';

@Injectable()
export class FriendRequestService {

  credentials: Credentials;

  constructor() { }

  getStoredCredentials() {
  }

}
