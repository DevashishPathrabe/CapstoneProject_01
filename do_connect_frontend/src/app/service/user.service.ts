import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConditionalExpr } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { BASE_URL } from '../constants/constants';
import { getHeaders, isUserAdmin, isUserLoggedIn } from '../utils/util';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseURL = BASE_URL;
  user = {
    isLoggedIn: false,
    isAdmin: false,
  };

  constructor(private http: HttpClient) {
    this.user.isLoggedIn = isUserLoggedIn();
    this.user.isAdmin = isUserAdmin();
  }

  private intialUser = new BehaviorSubject(this.user);
  currentUser = this.intialUser.asObservable();

  register(user: any) {
    // console.warn(user);
    return this.http.post(`${this.baseURL}/register`, user);
  }
  login(user: any) {
    // console.warn(user);
    return this.http.post(`${this.baseURL}/login`, user);
  }

  setUser(user: any) {
    this.intialUser.next(user);
  }

  getQuestion(id: any) {
    const headers = getHeaders();
    return this.http.get(this.baseURL + '/questions/' + id, { headers });
  }

  getApprovedQuestions() {
    return this.http.get(this.baseURL + '/questions');
  }

  postQuestion(question: any) {
    console.log(question);
    const headers = getHeaders();
    console.log(headers);
    return this.http.post(this.baseURL + '/questions', question, {
      headers,
    });
  }

  postAnswer(quesId: any, answer: any) {
    console.log(quesId, answer);
    const headers = getHeaders();
    console.log(headers);
    return this.http.post(
      this.baseURL + '/questions/' + quesId + '/answers',
      answer,
      {
        headers,
      }
    );
  }

  searchQuestion(query: any) {
    const headers = getHeaders();
    console.log(headers);
    return this.http.get(this.baseURL + '/questions?search=' + query, {
      headers: headers,
    });
  }

  getAnswers(quesId: any) {
    const headers = getHeaders();
    return this.http.get(this.baseURL + '/questions/' + quesId + '/answers', {
      headers,
    });
  }

  logout() {
    const headers = getHeaders();
    return this.http.get(this.baseURL + '/signout', {
      headers,
    });
  }
}
