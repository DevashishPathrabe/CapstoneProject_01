import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  baseURL = 'http://localhost:8181/api/v1';

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
