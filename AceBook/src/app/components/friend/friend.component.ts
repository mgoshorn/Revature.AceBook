import { Component, OnInit } from '@angular/core';
import { FriendService } from '../../services/friend.service';
import { StorageService } from '../../services/storage.service';
import { FriendRequestService } from '../../services/friend-request.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

  constructor(private friendService: FriendService, private storageService: StorageService,
  private friendRequestService: FriendRequestService) {
    this.setFriends();
    this.getFriendRequests();
  }
  friends: User[];
  friend: User;
  user: User;
  friendRequests: User[];
  showFriends = true;
  buttonText = true;


  ngOnInit() {
  }

  setFriends() {
    this.friendService.getFriends().subscribe( (response) => {
      this.friends = Object.assign(new Array<User>(), response);
    });
  }

  getFriends() {
    // console.log(this.friends);
    return this.friends;
  }

  getFriendRequests() {
    this.friendRequestService.getFriendRequests()
    .subscribe ( (response) => {
      this.friendRequests = Object.assign(new Array<User>(), response);
      // console.log(this.friendRequests);
    });
  }

  showFriendsToggle() {
    this.showFriends = !this.showFriends;
  }

  acceptFriendRequest(userId) {
    this.friendRequestService.acceptRequest(userId)
    .subscribe( (response) => {
      console.log(response);
    });
  }
}
