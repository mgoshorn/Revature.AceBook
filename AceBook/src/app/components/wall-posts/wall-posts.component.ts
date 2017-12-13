import { Component, OnInit, Input} from '@angular/core';
import { ProfileWallResolver } from '../../services/profile-wall-resolver.service';
import { WallPost } from '../../models/WallPost';

@Component({
  selector: 'app-wall-posts',
  templateUrl: './wall-posts.component.html',
  styleUrls: ['./wall-posts.component.css']
})

export class WallPostsComponent implements OnInit {

  @Input() posts: Array<WallPost> = [];

  constructor() { }

  ngOnInit() {
    console.log(this.posts);
  }

}
