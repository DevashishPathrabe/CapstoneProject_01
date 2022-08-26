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

  createUser(user: any) {
    return this.http.post(`${this.baseURL}/users`, user);
  }
  getUsers() {
    return this.http.get(`${this.baseURL}/users`);
  }
  setUser(user: any) {
    this.user = {
      id: user.id,
      name: user.name,
      email: user.email,
    };
  }

  getQuestion(id: any) {
    return this.http.get(this.baseURL + '/questions');
  }

  getApprovedQuestions() {
    return this.http.get(this.baseURL + '/questions');
  }

  postQuestion(answer: any) {}
}
