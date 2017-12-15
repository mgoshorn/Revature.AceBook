import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { environment } from '../../../environments/environment';
import { Message } from '../../models/message';
import { MessageData } from '../../models/message-data';
import { HttpClient } from '@angular/common/http';
import { Credentials } from '../../models/credentials';
import { StorageService } from '../../services/storage.service';

@Component({
  selector: 'app-conversation',
  templateUrl: './conversation.component.html',
  styleUrls: ['./conversation.component.css']
})
export class ConversationComponent implements OnInit {

  id: number;
  receiver: String;
  private sub: any;
  messageData: MessageData = new MessageData;

  constructor(private route: ActivatedRoute, private httpClient: HttpClient, private storageService: StorageService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.httpClient
      .post<Array<Message>>(environment.context + 'message/' + this.id, this.storageService.getCredentials(), {'withCredentials': true})
      .subscribe(data => {
        console.log(data);
        this.messageData.data = data;
      });
   });
  }

  addMessage(message: Message) {
    this.sub = this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.httpClient
      .post<Array<Message>>(environment.context + 'message/' + this.id, this.storageService.getCredentials(), {'withCredentials': true})
      .subscribe(data => {
        console.log(data);
        this.messageData.data = data;
      });
   });
  }

}
