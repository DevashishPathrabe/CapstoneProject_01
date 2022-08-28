import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
import { JWTCustomPayload } from '../constants/constants';
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
  constructor(private router: Router, private _userService: UserService) {}

  ngOnInit(): void {}

  onLogin() {
    if (this.username === '' || this.password === '') {
      this.warning = 'All field required';
      return;
    }
    this._userService
      .login({ username: this.username, password: this.password })
      .subscribe({
        next: (res: any) => {
          const jwtData: JWTCustomPayload = jwtDecode(res.token);
          localStorage.setItem('token', res.token);
          if (jwtData.isAdmin) {
            this.router.navigate(['/dashboard']);
          } else {
            this.router.navigate(['/']);
          }
        },
        error: (err) => {
          alert('Invalid Credentials');
          // this.router.navigate(['/error/' + err.status]);
        },
      });
  }
}