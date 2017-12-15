import { Component, OnInit, Input} from '@angular/core';
import { User } from '../../../models/User';

@Component({
  selector: 'app-friend-list',
  templateUrl: './friend-list.component.html',
  styleUrls: ['./friend-list.component.css']
})
export class FriendListComponent implements OnInit {

  @Input() friends: User[];

  constructor() { }

  ngOnInit() {
  }

}
