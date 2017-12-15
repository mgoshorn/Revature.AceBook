import { Component, OnInit, Input} from '@angular/core';
import { WallPost } from '../../models/WallPost';
import { environment } from '../../../environments/environment';
import { WallPostService } from '../../services/wall-post.service';

@Component({
  selector: 'app-wall-posts',
  templateUrl: './wall-posts.component.html',
  styleUrls: ['./wall-posts.component.css']
})

export class WallPostsComponent implements OnInit {

  @Input() posts: Array<WallPost> = [];

  profileImageLoc: string;

  constructor(private wallPostService: WallPostService) { }

  ngOnInit() {
    this.profileImageLoc = environment.context + 'users/profile/';
    console.log(this.posts);
  }

  updateCommentVal(event, post: WallPost) {
    console.log('running');
    console.log(event);
  }

  sendComment(post: WallPost) {
    console.log(post.commentField);
    console.log(post.wallPostId);
    this.wallPostService.postComment(post.commentField, post.wallPostId).subscribe(
      (success) => {
        post.comments.unshift(success);
      }, (error) => {
        console.log(error);
        alert('Problem posting comment:(');
      }
    );
  }

}
