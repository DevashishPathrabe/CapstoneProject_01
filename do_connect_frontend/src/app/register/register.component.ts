import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  name = '';
  email = '';
  password = '';
  warning = '';
  constructor(private _userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  onRegister() {
    if (this.name === '' || this.email === '' || this.password === '') {
      this.warning = 'All field required';
      return;
    }
    this._userService
      .registerUser({
        name: this.name,
        email: this.email,
        password: this.password,
      })
      .subscribe((res) => {
        console.log(res);
        this.router.navigate(['/login']);
      });
  }
}
