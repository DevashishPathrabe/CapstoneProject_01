import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { isUserAdmin } from '../utils/util';

const OPEN_CHAT_BUTTON_LABEL = 'Chat';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})

export class HomeComponent implements OnInit {
  data: any;
  search: any;
  chatbox = 'none';
  isAdmin: boolean = isUserAdmin();
  chatButton: string = OPEN_CHAT_BUTTON_LABEL;

  constructor(private _userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this._userService.getApprovedQuestions().subscribe({
      next: (res) => (this.data = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }

  toggleChatBox() {
    this.chatbox = this.chatbox === 'block' ? 'none' : 'block';
    var elem: any = document.getElementById('msgbox');
    elem.scrollTop = elem.scrollHeight;
  }

  openQuestion(id: any) {
    this.router.navigate([`/question/${id}`]);
  }

  onSearch() {
    this._userService.searchQuestion(this.search).subscribe({
      next: (res) => (this.data = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }
}
