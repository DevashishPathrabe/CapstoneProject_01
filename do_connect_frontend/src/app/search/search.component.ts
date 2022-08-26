import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
})
export class SearchComponent implements OnInit {
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

  onClose() {
    this.roter.navigate(['/']);
  }
}
