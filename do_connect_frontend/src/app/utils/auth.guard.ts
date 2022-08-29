import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { isUserAdmin, isUserLoggedIn } from './util';

function getResolvedUrl(route: ActivatedRouteSnapshot): string {
  return route.pathFromRoot
    .map(v => v.url.map(segment => segment.toString()).join('/'))
    .join('/');
}

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  isUserLoggedIn = isUserLoggedIn();
  isUserAdmin = isUserAdmin();

  constructor(public router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const url = getResolvedUrl(route);
    console.log({url});
    if (this.isUserLoggedIn) {
      if (['/register', '/login'].includes(url)) {
        if (this.isUserAdmin) {
          this.router.navigate(['dashboard']);
          return false;
        }
        else {
          this.router.navigate(['']);
          return false;
        }
      }
      else if (['/', '/post-question'].includes(url) || url.startsWith('/question/')) {
        if (this.isUserAdmin) {
          this.router.navigate(['dashboard']);
          return false;
        }
        else {
          return true;
        }
      }
      else if (['/dashboard'].includes(url)) {
        if (this.isUserAdmin) {
          return true;
        }
        else {
          this.router.navigate(['']);
          return false;
        }
      }
      else {
        return true;
      }
    }
    else {
      if (['/dashboard', '/post-question'].includes(url) || url.startsWith('/question/')) {
        this.router.navigate(['']);
        return false;
      }
      else {
        return true;
      }
    }
  }
}
