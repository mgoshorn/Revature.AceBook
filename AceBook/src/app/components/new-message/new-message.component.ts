import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MessageService } from '../../services/message.service';
import { ActivatedRoute } from '@angular/router';
import { Message } from '../../models/Message';

@Component({
  selector: 'app-new-message',
  templateUrl: './new-message.component.html',
  styleUrls: ['./new-message.component.css']
})
export class NewMessageComponent implements OnInit {
  @Output() updateMessages = new EventEmitter<Message>();

  targetId: number;
  private sub: any;
  inputText: String;

  constructor(private route: ActivatedRoute, private messageService: MessageService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      // this.targetId = +params['id'];
    });
  }


  sendMessage() {
    this.messageService.sendMessage(this.inputText, this.targetId).subscribe(
      (data) => {
        this.updateMessages.next(data);
      }, (error) => {
        console.log('error adding message');
        alert('failed to add message');
      });
  }
}
