import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConditionalExpr } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';
import { getHeaders } from '../utils/util';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseURL = BASE_URL;

  constructor(private http: HttpClient) {}

  register(user: any) {
    // console.warn(user);
    return this.http.post(`${this.baseURL}/register`, user);
  }
  login(user: any) {
    // console.warn(user);
    return this.http.post(`${this.baseURL}/login`, user);
  }

  setUser(user: any) {}

  getQuestion(id: any) {
    const headers = getHeaders();
    return this.http.get(this.baseURL + '/questions/' + id, {
      headers
    });
  }

  getApprovedQuestions() {
    return this.http.get(this.baseURL + '/questions');
  }

  postQuestion(question: any) {
    console.log(question);
    const headers = getHeaders();
    console.log(headers);
    return this.http.post(this.baseURL + '/questions', question, {
      headers: headers,
    });
  }
}
