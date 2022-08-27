import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';
import { getHeaders } from '../utils/util';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  constructor(private http:HttpClient) { }
  baseUrl = BASE_URL
  createChat(data:any){
    const headers = getHeaders();
    return this.http.post(this.baseUrl+"/messages",data,{headers});
  }
  getChatList(){
    const headers = getHeaders();
    return this.http.get(this.baseUrl+"/messages",{headers});
  }
}
