import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ChatService } from '../service/chat.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css'],
})
export class ChatComponent implements OnInit {
  constructor(private _chatservice: ChatService, private router: Router) {}
  message: any;
  data: any;
  chatList: any;
  ngOnInit(): void {
    if (localStorage.getItem('isAdmin') === 'false') this.getChats();
  }
  getChats() {
    const seconds = 5;
    setInterval(() => {
      if (localStorage.getItem('isAdmin') === null) return;
      this._chatservice.getChatList().subscribe({
        next: (res) => (this.chatList = res),
        error: (err) => console.warn(err),
      });
    }, seconds * 1000);
  }
  onSend() {
    this._chatservice.createChat({ message: this.message }).subscribe({
      next: (res) => {
        this.data = res;
        this.message = '';
      },
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }
}
