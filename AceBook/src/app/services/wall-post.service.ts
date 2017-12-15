import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StorageService } from './storage.service';
import { WallPost } from '../models/WallPost';
import { environment } from '../../environments/environment';

@Injectable()
export class WallPostService {

  constructor(private http: HttpClient, private storageService: StorageService) {}

  postToWall(text, targetId) {

    const json = {
      'credentials': this.storageService.getCredentials(),
      'postbody': text
    };

    console.log(json);

    return this.http.post<WallPost>(environment.context + 'wall/post/' + targetId, json, {withCredentials: true});
  }

  postComment(text, wallPostId) {
    console.log(text);
    console.log(wallPostId);

    const json = {
      'credentials': this.storageService.getCredentials(),
      'postbody': text
    };
    return this.http.post<Comment>(environment.context + 'wall/comment/' + wallPostId, json, {withCredentials: true});
  }

}
