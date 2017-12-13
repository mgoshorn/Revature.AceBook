import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';

import { StorageService } from '../../services/storage.service';



@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user: User = this.storageService.getUser();
  firstName = this.user.firstName;
  lastName = this.user.lastName;

  constructor(private storageService: StorageService) { }


  ngOnInit() {
  }

}
