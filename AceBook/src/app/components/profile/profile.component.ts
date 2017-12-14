import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user';
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
  profileImageLoc: string;

  constructor(private storageService: StorageService, private route: ActivatedRoute, private imageUpload: ProfileImageUploadService) {
    this.sub = this.route.params.subscribe(params => {
      this.ownerId = +params['id'];
      this.profileImageLoc = environment.context + 'users/profile/' + this.ownerId;
    });
  }


  ngOnInit() {
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
