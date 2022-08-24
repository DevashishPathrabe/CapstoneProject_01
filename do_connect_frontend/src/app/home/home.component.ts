import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  data = [1, 2, 2, 2, 2, 2];
  constructor(private _userService: UserService, private roter: Router) {}

  ngOnInit(): void {
    // this._userService.getAllProducts().subscribe((data) => {
    //   this.data = data;
    //   console.log(this.data);
    // });
  }

  // addToWishlist(product: any) {
  //   if (this._userService.user.name !== '') {
  //     this._userService.addTowishlist(product);
  //   } else {
  //     alert('Please Login');
  //     this.roter.navigate(['/login']);
  //   }
  // }

  // addToCart(product: any) {
  //   if (this._userService.user.name !== '') {
  //     this._userService.addToCart(product);
  //   } else {
  //     alert('Please Login');
  //     this.roter.navigate(['/login']);
  //   }
  // }
}
