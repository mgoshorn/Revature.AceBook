import { Component, OnInit, Input } from '@angular/core';
import { User } from '../../models/user';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-friend-detail',
  templateUrl: './friend-detail.component.html',
  styleUrls: ['./friend-detail.component.css']
})
export class FriendDetailComponent implements OnInit {

  @Input()
  friends: User[];

  constructor(private storageService: StorageService) { }

  ngOnInit() {
  }


}
