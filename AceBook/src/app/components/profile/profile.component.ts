import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
import { HttpClient } from '@angular/common/http';
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
  owner: User;
  private sub: any;
  private profileUploadLocation: string;
  user: User = this.storageService.getUser();
  firstName = this.user.firstName;
  lastName = this.user.lastName;
  conversationStart: String;
  profileImageLoc: string;

  constructor(private http: HttpClient, private storageService: StorageService, private route: ActivatedRoute,
    private imageUpload: ProfileImageUploadService, private messageService: MessageService) {
    this.sub = this.route.params.subscribe(params => {
      this.ownerId = +params['id'];
      this.profileImageLoc = environment.context + 'users/profile/' + this.ownerId;
    });
  }

  profileUpdate() {
    this.user = this.storageService.getOwner();
  }

  ngOnInit() {
    this.user = this.storageService.getOwner();
    /*this.http.get<User>(environment.context + 'users/profile/owner/' + this.ownerId, {withCredentials: true})
    .subscribe( (success) => {
      this.user = Object.assign(new User, success);
      this.storageService.setUser(this.user);
      console.log('profile/' + success.user_id);
    }, (error) => {
      console.log('error');
      alert('failed to load owner');
    });*/
  }

  convStart() {
    this.messageService.startConversation(this.conversationStart).subscribe(
        (data) => {
          // this.updateMessages.next(data);
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
