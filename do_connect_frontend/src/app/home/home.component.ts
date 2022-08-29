import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { QuestionType } from '../constants/constants';
import { UserService } from '../service/user.service';
import { isUserAdmin, isUserLoggedIn } from '../utils/util';

const OPEN_CHAT_BUTTON_LABEL = 'Chat';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})

export class HomeComponent implements OnInit {

  questionList: QuestionType[] = [];
  search: string = '';
  chatbox = 'none';
  isAdmin: boolean = isUserAdmin();
  isUserLoggedIn: boolean = isUserLoggedIn();
  chatButton: string = OPEN_CHAT_BUTTON_LABEL;

  constructor(private _userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this._userService.getApprovedQuestions().subscribe({
      next: (result) => (this.questionList = result as QuestionType[]),
      error: (error: HttpErrorResponse) => {
        if (error.status === 400) {
          alert(error.error);
        }
        else {
          this.router.navigate(['/error/' + error.status]);
        }
      },
    });
  }

  toggleChatBox() {
    this.chatbox = this.chatbox === 'block' ? 'none' : 'block';
  }

  openQuestion(id: number) {
    this.router.navigate([`/question/${id}`]);
  }

  onSearch() {
    this._userService.searchQuestion(this.search).subscribe({
      next: (result) => (this.questionList = result as QuestionType[]),
      error: (err) => this.router.navigate(['/error/' + err.status]),
    });
  }
}
