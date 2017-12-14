import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { WallPostService } from '../../services/wall-post.service';
import { ActivatedRoute } from '@angular/router';
import { WallPost } from '../../models/WallPost';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-wall-new-post',
  templateUrl: './wall-new-post.component.html',
  styleUrls: ['./wall-new-post.component.css']
})
export class WallNewPostComponent implements OnInit {
  @Output() updatePosts = new EventEmitter<WallPost>();

  targetId: number;
  private sub: any;
  inputText: String;

  constructor(private route: ActivatedRoute, private wallPostService: WallPostService, private storageService: StorageService) { }
  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.targetId = +params['id'];
      if (isNaN(this.targetId)) {
        this.targetId = this.storageService.getUser().user_id;
      }
    });
  }

  createPost() {
    this.wallPostService.postToWall(this.inputText, this.targetId).subscribe(
      (data) => {
        this.updatePosts.next(data);
      }, (error) => {
        console.log('error adding wall post');
        alert('failed to add wall post');
      });
  }

}
