import { Component, OnInit } from '@angular/core';
import { FriendService } from '../../services/friend.service';
import { User } from '../../models/user';
import { StorageService } from '../../services/storage.service';
import { FriendRequestService } from '../../services/friend-request.service';

@Component({
  selector: 'app-friend',
  templateUrl: './friend.component.html',
  styleUrls: ['./friend.component.css']
})
export class FriendComponent implements OnInit {

  constructor(private friendService: FriendService, private storageService: StorageService) {
    this.setFriends();
  }
  friends: User[];
  friend: User;
  user: User;


  ngOnInit() {
  }

  setFriends() {
    this.friendService.getFriends()
    .subscribe( (response) => {
      this.friends = Object.assign(new Array<User>(), response);
    });
  }

  getFriends() {
    // console.log(this.friends);
    return this.friends;
  }
}
