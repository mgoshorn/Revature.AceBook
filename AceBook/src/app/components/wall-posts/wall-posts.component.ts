import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { WallPost } from '../../models/WallPost';
import { environment } from '../../../environments/environment';
import { User } from '../../models/user';
import { HttpClient } from '@angular/common/http';
import { StorageService } from '../../services/storage.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-wall-posts',
  templateUrl: './wall-posts.component.html',
  styleUrls: ['./wall-posts.component.css']
})

export class WallPostsComponent implements OnInit {
  user: User;
  private sub: any;
  //ownerId: number;

  @Input() posts: Array<WallPost> = [];
  @Output() updateProfile = new EventEmitter();

  profileImageLoc: string;
  constructor(private http: HttpClient, private storageService: StorageService, private route: ActivatedRoute) {
    this.sub = this.route.params.subscribe(params => {
     // this.ownerId = +params['id'];
    });
   }

  ngOnInit() {
    this.profileImageLoc = environment.context + 'users/profile/';
    console.log(this.posts);
  }

  getOwner(ownerId) {
    this.http.get<User>(environment.context + 'users/profile/owner/' + ownerId, {withCredentials: true})
    .subscribe( (success) => {
      this.user = Object.assign(new User, success);
      this.storageService.setOwner(this.user);
      this.updateProfile.emit();
      console.log('profile/' + success.user_id);
      //alert('You are now on the profile page for ' + this.user.firstName);
    }, (error) => {
      console.log('error');
      alert('failed to load owner');
    });
  }

}
