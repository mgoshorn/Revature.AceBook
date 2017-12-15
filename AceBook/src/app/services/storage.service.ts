import { Injectable } from '@angular/core';
import { Credentials } from '../models/credentials';
import { User } from '../models/user';

@Injectable()
export class StorageService {

  credentials: Credentials;
  user: User;
  owner: User;

  constructor() { }

  setCredentials(credentials: Credentials) {
    this.credentials = credentials;
    console.log(this.credentials);
  }

  getCredentials() {
    return this.credentials;
  }

  setUser(user: User) {
    this.user = user;
    console.log(this.user);
  }

  getUser() {
    return this.user;
  }

  setOwner(owner: User) {
    this.owner = owner;
    console.log(this.owner);
  }

  getOwner() {
    return this.owner;
  }

}
