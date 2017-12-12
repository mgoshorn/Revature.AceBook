import { Injectable } from '@angular/core';
import { Credentials } from '../models/credentials';

@Injectable()
export class StorageService {

  credentials: Credentials;

  constructor() { }

  storeCredentials(credentials: Credentials) {
    this.credentials = credentials;
    console.log(this.credentials);
  }

  getCredentials() {
    return this.credentials;
  }

}
