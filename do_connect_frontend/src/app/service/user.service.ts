import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConditionalExpr } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseURL = BASE_URL;

  constructor(private http: HttpClient) {}

  register(user: any) {
    console.warn(user);
    return this.http.post(`${this.baseURL}/register`, user);
  }
  login(user: any) {
    console.warn(user);
    return this.http.post(`${this.baseURL}/login`, user);
  }
  getUsers() {
    return this.http.get(`${this.baseURL}/users`);
  }
  setUser(user: any) {}

  getQuestion(id: any) {
    return this.http.get(this.baseURL + '/questions');
  }

  getApprovedQuestions() {
    return this.http.get(this.baseURL + '/questions');
  }

  postQuestion(question: any) {
    console.log(question);
    return this.http.post(this.baseURL + '/questions', question);
  }
}
