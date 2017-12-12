import { Injectable } from '@angular/core';
import { StorageService } from './storage.service';

@Injectable()
export class FriendService {

  constructor(private storageService: StorageService) { }


}
