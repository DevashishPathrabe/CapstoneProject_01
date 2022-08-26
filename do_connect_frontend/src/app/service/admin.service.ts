import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  baseURL = BASE_URL;

  constructor(private http: HttpClient) {}

  getUnapprovedQuestions() {
    return this.http.get(this.baseURL + '/questions');
  }
  getUnapprovedAnswers() {
    return this.http.get(this.baseURL + '/questions');
  }

  getUsers() {
    return this.http.get(this.baseURL + '/users');
  }

  deleteUser(id: any) {
    return this.http.delete(this.baseURL + '/users/' + id);
  }

  approveQuestion(data: any) {
    return this.http.put(this.baseURL + '/questions/' + data.id, data);
  }
  approveAnswer(data: any) {
    return this.http.put(this.baseURL + '/questions/' + data.id, data);
  }
  deleteQuestion(id: any) {
    return this.http.delete(this.baseURL + '/questions/' + id);
  }
  deleteAnswer(id: any) {
    return this.http.delete(this.baseURL + '/questions/' + id);
  }
}
