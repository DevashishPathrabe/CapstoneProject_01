import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http:HttpClient) { }
  baseUrl = BASE_URL
  createChat(data:any){
    return this.http.post(this.baseUrl+"/messages",data);
  }
  getChatList(){
    return this.http.get(this.baseUrl+"/messages");
  }
}
