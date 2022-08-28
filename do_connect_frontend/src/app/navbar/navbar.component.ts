import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  user = {
    isLoggedIn: false,
    isAdmin: false,
  };

  constructor(private _userService: UserService, private router: Router) {}

  ngOnInit(): void {
    this._userService.currentUser.subscribe((user) => {
      this.user = user;
      console.log(user);
    });
  }
  onLogout() {
    this._userService.setUser({
      isLoggedIn: false,
      isAdmin: false,
    });
    this._userService.logout().subscribe({
      next: (res) => {
        localStorage.clear();
        alert('Logged Out Successfully!');
        this.router.navigate(['/']);
      },
      error: (err) => {
        if (err.status === 200) {
          localStorage.clear();
          alert('Logged Out Successfully!');
          this.router.navigate(['/']);
        } else this.router.navigate(['/error/' + err.status]);
      },
    });
  }
}
