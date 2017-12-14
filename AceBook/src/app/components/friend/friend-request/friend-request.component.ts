import { Component, OnInit, Input } from '@angular/core';
import { FriendRequestService } from '../../../services/friend-request.service';
@Component({
  selector: 'app-friend-request',
  templateUrl: './friend-request.component.html',
  styleUrls: ['./friend-request.component.css']
})
export class FriendRequestComponent implements OnInit {

  @Input() pendingRequests: any[];

  constructor(private friendRequestService: FriendRequestService) { }

  ngOnInit() {

  }

  acceptFriendRequest(userId) {
    this.friendRequestService.acceptRequest(userId)
    .subscribe( (response) => {
      console.log(response);
    });
  }

}
