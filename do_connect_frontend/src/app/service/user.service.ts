import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  baseURL = 'http://localhost:3000';
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

  getQuestion(id: any) {
    return this.http.get(this.baseURL + '/questions/' + id);
  }

  getApprovedQuestions() {
    return this.http.get(this.baseURL + '/questions');
  }

  postQuestion(answer: any) {}
}
