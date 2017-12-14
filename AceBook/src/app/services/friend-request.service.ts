import { Injectable } from '@angular/core';
import { Credentials } from '../models/credentials';
import { HttpClient } from '@angular/common/http';
import { StorageService } from './storage.service';
import { environment } from '../../environments/environment';
import { User } from '../models/user';

@Injectable()
export class FriendRequestService {

  credentials: Credentials;
  user: User;

  constructor(private http: HttpClient, private storageService: StorageService) { }

}
