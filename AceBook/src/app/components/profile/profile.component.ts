import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { MessageService } from '../../services/message.service';
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
  user_id = this.user.user_id;
  conversationStart: String;

  constructor(private storageService: StorageService, private messageService: MessageService) { }


  ngOnInit() {
  }

  convStart() {
    this.messageService.startConversation(this.conversationStart).subscribe(
        (data) => {
          //this.updateMessages.next(data);
        }, (error) => {
          console.log('error Starting Conversation');
          alert('failed to Start Conversation');
        });
  }
}
