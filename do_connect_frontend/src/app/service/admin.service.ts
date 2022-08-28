import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';
import { getHeaders } from '../utils/util';

@Injectable({
  providedIn: 'root',
})
export class AdminService {
  baseURL = BASE_URL;

  constructor(private http: HttpClient) {}

  getUnapprovedQuestions() {
    const headers = getHeaders();
    return this.http.get(this.baseURL + '/questions?status=unapproved', {
      headers: headers,
    });
  }
  getUnapprovedAnswers() {
    const headers = getHeaders();
    console.log(headers);
    return this.http.get(this.baseURL + '/answers?status=unapproved', {
      headers: headers,
    });
  }

  getUsers() {
    const headers = getHeaders();
    return this.http.get(this.baseURL + '/users', {
      headers: headers,
    });
  }

  deleteUser(id: any) {
    const headers = getHeaders();
    return this.http.delete(this.baseURL + '/users/' + id, {
      headers: headers,
    });
  }

  approveQuestion(id: any) {
    const headers = getHeaders();
    return this.http.put(
      this.baseURL + '/questions/' + id,
      {
        isApproved: true,
      },
      { headers: headers }
    );
  }
  approveAnswer(answer: any) {
    const headers = getHeaders();
    return this.http.put(
      this.baseURL +
        '/questions/' +
        answer.question.id +
        '/answers/' +
        answer.id,
      {
        isApproved: true,
      },
      { headers: headers }
    );
  }
  deleteQuestion(id: any) {
    const headers = getHeaders();
    return this.http.delete(this.baseURL + '/questions/' + id, {
      headers: headers,
    });
  }
  deleteAnswer(answer: any) {
    const headers = getHeaders();
    return this.http.delete(
      this.baseURL +
        '/questions/' +
        answer.question.id +
        '/answers/' +
        answer.id,
      {
        headers: headers,
      }
    );
  }
}
