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

    // this.http.post<WallPost>(environment.context + 'wall/post/' + targetId, json, {withCredentials: true})
    // .subscribe( (success) => {
    //   return success;
    // }, (error) => {
    //   console.log('error adding wall post');
    //   alert('failed to add wall post');
    //   return null;
    // });
  }

}
