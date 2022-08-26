import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  username = '';
  password = '';
  warning = '';
  constructor(private roter: Router, private _userService: UserService) {}

  ngOnInit(): void {}
  onLogin() {
    if (this.username === '' || this.password === '') {
      this.warning = 'All field required';
      return;
    }
    this._userService
      .login({ username: this.username, password: this.password })
      .subscribe((res) => {
        console.log(res);
        // alert('Invalid Credentials');
      });
  }
}
