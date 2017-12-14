import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../environments/environment';
import { WallPost } from '../../models/WallPost';
import { WallData } from '../../models/wall-data';
import { HttpClient } from '@angular/common/http';
import { Credentials } from '../../models/credentials';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-feed',
  templateUrl: './feed.component.html',
  styleUrls: ['./feed.component.css']
})
export class FeedComponent implements OnInit {

  id: number;
  private sub: any;
  wallData: WallData = new WallData;

  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private storageService: StorageService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];

      this.httpClient
      .post<Array<WallPost>>(environment.context + 'feed', this.storageService.getCredentials(), {'withCredentials': true})
      .subscribe(data => {
        this.wallData.data = data;
      });
   });
  }

  addPost(post: WallPost) {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];

      this.httpClient
      .post<Array<WallPost>>(environment.context + 'feed', this.storageService.getCredentials(), {'withCredentials': true})
      .subscribe(data => {
        this.wallData.data = data;
      });
   });
  }

}
