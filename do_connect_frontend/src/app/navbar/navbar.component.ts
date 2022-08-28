import { HttpErrorResponse } from '@angular/common/http';
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

  ngOnInit(): void {
    this.router.events.subscribe(event => {
      if (event.constructor.name === "NavigationEnd") {
        this.isAdmin = isUserAdmin();
        this.isLoggedIn = isUserLoggedIn();
      }
    });
  }

  onLogout() {
    this._userService.logout().subscribe({
      next: (result) => {
        localStorage.clear();
        alert(result);
        this.router.navigate(['/login']);
      },
      error: (error: HttpErrorResponse) => {
        if (error.status === 400) {
          alert(error.error);
        }
        else {
          this.router.navigate(['/error/' + error.status]);
        }
      },
    });
  }
}
