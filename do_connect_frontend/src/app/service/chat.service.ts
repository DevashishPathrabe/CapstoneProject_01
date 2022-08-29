import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})

export class ChatService {

<<<<<<< HEAD
  constructor(private http:HttpClient) { }
  baseUrl = BASE_URL
  createChat(data:any){
    const headers = getHeaders();
    return this.http.post(this.baseUrl+'/messages',data,{headers});
=======
  baseUrl = BASE_URL;

  constructor(private http: HttpClient) {}

  createChat(data: any) {
    return this.http.post(this.baseUrl + '/messages', data);
>>>>>>> b2d856484c80090035edd85e6485363fa11457f6
  }

  getChatMessagesList() {
    return this.http.get(this.baseUrl + '/messages');
  }
}
