import { Component, OnInit, Input, Output } from '@angular/core';
import { FriendRequestService } from '../../../services/friend-request.service';
import { EventEmitter } from '@angular/core';
@Component({
  selector: 'app-friend-request',
  templateUrl: './friend-request.component.html',
  styleUrls: ['./friend-request.component.css']
})
export class FriendRequestComponent implements OnInit {

  @Input() pendingRequests: any[];
  @Output() acceptFriendEvent = new EventEmitter<number>();
  friendId: number;

  constructor(private friendRequestService: FriendRequestService) { }

  ngOnInit() {

  }

  acceptFriendRequest(userId) {
    this.friendRequestService.acceptRequest(userId)
    .subscribe( (response) => {
      console.log(response);
      this.friendId = userId;
      console.log(this.friendId);
      this.acceptFriendEvent.emit(this.friendId);
    });
  }

  sendFriendRequest(userId) {
    this.friendRequestService.sendFriendRequest(userId)
    .subscribe( (response) => {
      console.log(response);
    });
  }

}
