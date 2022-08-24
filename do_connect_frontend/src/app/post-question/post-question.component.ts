import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-post-question',
  templateUrl: './post-question.component.html',
  styleUrls: ['./post-question.component.css'],
})
export class PostQuestionComponent implements OnInit {
  question = '';
  topic = 'java';
  warning = '';
  constructor() {}

  ngOnInit(): void {}

  onSubmit() {
    if (this.question === '') {
      this.warning = 'All fields are required!';
    }
  }
}
