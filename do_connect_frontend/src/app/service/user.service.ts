import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConditionalExpr } from '@angular/compiler';
import { Injectable } from '@angular/core';
import { AnswerPostType, BASE_URL, QuestionPostType, UserLoginType, UserRegisterType } from '../constants/constants';
import { getHeaders } from '../utils/util';

@Injectable({
  providedIn: 'root',
})

export class UserService {

  constructor(private http: HttpClient) {}

  register(user: UserRegisterType) {
    return this.http.post(`${BASE_URL}/register`, user);
  }

  login(user: UserLoginType) {
    return this.http.post(`${BASE_URL}/login`, user);
  }

  getQuestion(id: number) {
    const headers = getHeaders();
    return this.http.get(BASE_URL + '/questions/' + id, { headers });
  }

  getApprovedQuestions() {
    return this.http.get(BASE_URL + '/questions');
  }

  postQuestion(question: QuestionPostType) {
    const headers = getHeaders();
    return this.http.post(BASE_URL + '/questions', question, { headers });
  }

  postAnswer(questionId: number, answer: AnswerPostType) {
    const headers = getHeaders();
    return this.http.post(BASE_URL + '/questions/' + questionId + '/answers', answer, { headers });
  }

  searchQuestion(query: string) {
    const headers = getHeaders();
    return this.http.get(BASE_URL + '/questions?search=' + query, { headers });
  }

  getAnswers(quesId: number) {
    const headers = getHeaders();
    return this.http.get(BASE_URL + '/questions/' + quesId + '/answers', { headers });
  }

  logout() {
    const headers = getHeaders();
    return this.http.get(BASE_URL + '/signout', { headers });
  }
}
