import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Route, Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css'],
})
export class QuestionComponent implements OnInit {
  data: any;
  answer = '';
  constructor(
    private _userService: UserService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let urlParams = this.activatedRoute.snapshot.params;
    let questionId = urlParams['id'];
    console.log(questionId);
    this.data = this._userService.getQuestion(questionId).subscribe((res) => {
      this.data = res;
      console.log(this.data);
    });
  }

  onSubmit() {
    this._userService.postQuestion(this.answer);
  }
}
