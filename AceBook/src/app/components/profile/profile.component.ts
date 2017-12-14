import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { MessageService } from '../../services/message.service';
import { ActivatedRoute } from '@angular/router';
import { StorageService } from '../../services/storage.service';
import { ProfileImageUploadService } from '../../services/profile-image-upload.service';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  ownerId: number;
  private sub: any;
  private profileUploadLocation: string;
  user: User = this.storageService.getUser();
  firstName = this.user.firstName;
  lastName = this.user.lastName;
  conversationStart: String;
  profileImageLoc: string;

  constructor(private storageService: StorageService, private route: ActivatedRoute, private imageUpload: ProfileImageUploadService,
    private messageService: MessageService) {
    this.sub = this.route.params.subscribe(params => {
      this.ownerId = +params['id'];
      this.profileImageLoc = environment.context + 'users/profile/' + this.ownerId;
    });
  }


  ngOnInit() {
  }

  convStart() {
    this.messageService.startConversation(this.conversationStart).subscribe(
        (data) => {
          //this.updateMessages.next(data);
          alert('Conversation Started');
        }, (error) => {
          console.log('error Starting Conversation');
          alert('failed to Start Conversation');
        });
  }
  isOwnProfile() {
    const currentUserId: Number = this.storageService.getUser().user_id;
    return currentUserId === this.ownerId;
  }

  uploadProfileImage(event) {
    this.imageUpload.resolution$.subscribe(
      (res) => {
        if (res) {
          console.log('Success!');
        } else {
          console.log('Failure ;(');
        }
      });

    this.imageUpload.upload(event);
  }

}
