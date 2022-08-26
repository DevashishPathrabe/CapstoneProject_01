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
  constructor(private _userService: UserService, private roter: Router) {}

  ngOnInit(): void {
    this._userService.getApprovedQuestions().subscribe((res) => {
      this.data = res;
      console.log(res);
    });
  }

  openQuestion(id: any) {
    this.roter.navigate([`/question/${id}`]);
  }
}
