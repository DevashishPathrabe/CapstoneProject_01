import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  email = '';
  password = '';
  warning = '';
  constructor(private roter: Router, private _userService: UserService) {}

  ngOnInit(): void {}
  onLogin() {
    if (this.email === '' || this.password === '') {
      this.warning = 'All field required';
      return;
    }
    this._userService.getUsers().subscribe((res: any) => {
      res.forEach((user: any) => {
        if (user.email === this.email && user.password === this.password) {
          this._userService.setUser(res);
          alert('Successfully Loggedin');
          this.roter.navigate(['/dashboard']);
        }
      });
      alert('Invalid Credentials');
    });
  }
}
