import { Component, OnInit } from '@angular/core';
import { environment } from '../../../environments/environment';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private storageService: StorageService) { }

  name: string;
  userId: number;
  feedLink: string;

  ngOnInit() {
    this.name = this.storageService.getUser().firstName;
    this.userId = this.storageService.getUser().user_id;
    this.feedLink = environment.context + 'feed';
  }
}
