import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';
import { isUserAdmin, isUserLoggedIn } from '../utils/util';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {

  isAdmin = isUserAdmin();
  isLoggedIn = isUserLoggedIn();

  constructor(private _userService: UserService, private router: Router) {}

  ngOnInit(): void {}

  onLogout() {
    this._userService.logout().subscribe({
      next: (result) => {
        localStorage.clear();
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
