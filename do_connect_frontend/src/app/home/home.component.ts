import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  data: any;
  search: any;
  chatbox = 'none';
  isAdmin: any;
  chatButton = 'Open Chat';
  constructor(private _userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this.isAdmin = localStorage.getItem('isAdmin');
    console.log(this.isAdmin === 'true');
    this._userService.getApprovedQuestions().subscribe({
      next: (res) => (this.data = res),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }

  toggleChatBox() {
    this.chatbox = this.chatbox === 'block' ? 'none' : 'block';
    this.chatButton = this.chatbox === 'block' ? 'Close Chat' : 'Open Chat';
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
