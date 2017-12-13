import { Component, OnInit } from '@angular/core';
@Component({
  selector: 'app-friend-request',
  templateUrl: './friend-request.component.html',
  styleUrls: ['./friend-request.component.css']
})
export class FriendRequestComponent implements OnInit {

  pending_requests: any[];

  constructor() { }

  ngOnInit() {
    this.getCredentials();
  }

  getCredentials() {
  }

}
