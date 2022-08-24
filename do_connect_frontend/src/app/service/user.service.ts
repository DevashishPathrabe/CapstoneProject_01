import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseURL = 'http://localhost:8181/api/v1';
  user: any = {
    id: 0,
    name: '',
    email: '',
  };
  constructor(private http: HttpClient) {}

  registerUser(user: any) {
    return this.http.post(`${this.baseURL}/auth/register`, user);
  }
  loginUser(user: any) {
    return this.http.post(`${this.baseURL}/auth/login`, user);
  }
  setUser(user: any) {
    this.user = {
      id: user.id,
      name: user.name,
      email: user.email,
    };
  }
  // addTowishlist(product: any) {
  //   let currentWishlist = this.user.wishlist;
  //   currentWishlist.push(product);
  //   this.user = { ...this.user, wishlist: currentWishlist };
  //   // console.log('wish', this.user);
  // }
  // addToCart(product: any) {
  //   let currentCart = this.user.cart;
  //   currentCart.push(product);
  //   this.user = { ...this.user, cart: currentCart };
  //   // console.log(this.user);
  // }

  // getAllProducts() {
  //   return this.http.get('https://fakestoreapi.com/products');
  // }

  // getCartProducts() {
  //   return this.user.cart;
  // }

  // getWishlistProducts() {
  //   return this.user.wishlist;
  // }
}
