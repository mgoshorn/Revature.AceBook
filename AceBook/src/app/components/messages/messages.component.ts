import { Component, OnInit, Input } from '@angular/core';
import { Message } from '../../models/message';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.css']
})
export class MessagesComponent implements OnInit {

  @Input() messages: Array<Message> = [];

  profileImageLoc: string;
  constructor() { }

  ngOnInit() {
    this.profileImageLoc = environment.context + 'users/profile/';
  }

}
