import { Component, OnInit } from '@angular/core';
import { ChatService } from '../service/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  


  constructor(private _chatservice:ChatService) { }
  message=''
  data:any
  chatList:any
  ngOnInit(): void {
    this._chatservice.getChatList().subscribe(res=>{
      this.chatList = res;
      
    })
  }
  onclick(){
    this._chatservice.createChat(this.message).subscribe(res=>{
      this.data = res;
      console.log(this.data)
    })
    console.log(this.message);
  }


}
