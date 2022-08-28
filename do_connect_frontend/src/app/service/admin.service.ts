import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})

export class AdminService {

  constructor(private http: HttpClient) {}

  getUnapprovedQuestions() {
    return this.http.get(BASE_URL + '/questions?status=unapproved');
  }

  getUnapprovedAnswers() {
    return this.http.get(BASE_URL + '/answers?status=unapproved');
  }

  getUsers() {
    return this.http.get(BASE_URL + '/users');
  }

  deleteUser(id: any) {
    return this.http.delete(BASE_URL + '/users/' + id);
  }

  approveQuestion(id: any) {
    return this.http.put(BASE_URL + '/questions/' + id, { isApproved: true });
  }

  approveAnswer(answer: any) {
    return this.http.put(BASE_URL + '/questions/' + answer.question.id + '/answers/' + answer.id, { isApproved: true });
  }

  deleteQuestion(id: any) {
    return this.http.delete(BASE_URL + '/questions/' + id);
  }

  deleteAnswer(answer: any) {
    return this.http.delete(BASE_URL + '/questions/' + answer.question.id + '/answers/' + answer.id);
  }

}
