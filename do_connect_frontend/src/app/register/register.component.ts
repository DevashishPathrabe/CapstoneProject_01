import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  username = '';
  name = '';
  email = '';
  password = '';
  role = 'User';
  warning = '';
  constructor(private _userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  onRegister() {
    console.log(this.role);
    if (this.name === '' || this.email === '' || this.password === '') {
      this.warning = 'All field required';
      return;
    }
    let isAdmin = this.role === 'User' ? false : true;
    console.log({
      name: this.name,
      email: this.email,
      password: this.password,
      username: this.username,
      isAdmin: isAdmin,
    });
    this._userService
      .register({
        name: this.name,
        email: this.email,
        password: this.password,
        username: this.username,
        isAdmin: isAdmin,
      })
      .subscribe({
        next: (res) => this.router.navigate(['/login']),
        error: (err) => {
          if (err.status === 200) this.router.navigate(['/login']);
          else this.router.navigate(['/error/' + err.status]);
        },
      });
  }
}
