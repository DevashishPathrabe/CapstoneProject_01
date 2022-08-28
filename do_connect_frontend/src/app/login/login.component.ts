import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import jwtDecode from 'jwt-decode';
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
          console.log(jwtDecode(res.token));
          const jwtData: any = jwtDecode(res.token);
          localStorage.setItem('isAdmin', jwtData.isAdmin);
          localStorage.setItem('isLoggedIn', 'true');
          localStorage.setItem('token', res.token);
          if (jwtData.isAdmin) {
            this._userService.setUser({
              isLoggedIn: true,
              isAdmin: true,
            });
            this.router.navigate(['/dashboard']);
          } else {
            this._userService.setUser({
              isLoggedIn: true,
              isAdmin: false,
            });
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
