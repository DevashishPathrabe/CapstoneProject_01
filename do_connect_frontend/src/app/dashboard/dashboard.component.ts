import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
})
export class DashboardComponent implements OnInit {
  mode = 'Answers';
  data = [1, 1, 1, 1, 1, 2, 1, 1];
  constructor(private _userService: UserService) {}

  ngOnInit(): void {
    // this.cart = this._userService.getCartProducts();
    // this.wishlist = this._userService.getWishlistProducts();
  }

  getUnapprovedQuestions() {
    this.mode = 'Questions';
  }

  getUnapprovedAnswers() {
    this.mode = 'Answers';
  }

  getUsers() {
    this.mode = 'Users';
  }
}
