import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { StorageService } from './storage.service';
import { Message } from '../models/Message';
import { environment } from '../../environments/environment';
import { Conversation } from '../models/conversation';

@Injectable()
export class MessageService {

  constructor(private http: HttpClient, private storageService: StorageService) {}

  sendMessage(text, targetId) {

    const json = {
      'credentials': this.storageService.getCredentials(),
      'postbody': text
    };

    console.log(json);

    return this.http.post<Message>(environment.context + 'message/send/' + targetId, json, {withCredentials: true});

    // this.http.post<WallPost>(environment.context + 'wall/post/' + targetId, json, {withCredentials: true})
    // .subscribe( (success) => {
    //   return success;
    // }, (error) => {
    //   console.log('error adding wall post');
    //   alert('failed to add wall post');
    //   return null;
    // });
  }

  startConversation(targetId) {
    return this.http.post<Conversation>(environment.context + 'message/startconversation/' + targetId,
    this.storageService.getCredentials(), {withCredentials: true});
  }

}
