import { Component, OnInit, Input} from '@angular/core';
import { WallPost } from '../../models/WallPost';
import { environment } from '../../../environments/environment';

@Component({
  selector: 'app-wall-posts',
  templateUrl: './wall-posts.component.html',
  styleUrls: ['./wall-posts.component.css']
})

export class WallPostsComponent implements OnInit {

  @Input() posts: Array<WallPost> = [];

  profileImageLoc: string;
  constructor() { }

  ngOnInit() {
    this.profileImageLoc = environment.context + 'users/profile/';
    console.log(this.posts);
  }

}
