import { Component, OnInit } from '@angular/core';
import { LoginService } from '../../../services/login.service';
import { Credentials } from '../../../models/credentials';
import { StorageService } from '../../../services/storage.service';

@Component({
  selector: 'app-friend-request',
  templateUrl: './friend-request.component.html',
  styleUrls: ['./friend-request.component.css']
})
export class FriendRequestComponent implements OnInit {

  pending_requests: any[];
  credentials: Credentials;

  constructor(private storageService: StorageService) { }

  ngOnInit() {
    this.getCredentials();
  }

  getCredentials() {
    this.credentials = this.storageService.getCredentials();
  }

}
