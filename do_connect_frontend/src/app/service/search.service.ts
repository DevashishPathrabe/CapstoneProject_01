import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BASE_URL } from '../constants/constants';
import { getHeaders } from '../utils/util';

@Injectable({
  providedIn: 'root',
})
export class SearchService {
  searchActive = 'none';
  baseurl = BASE_URL
  constructor(private http:HttpClient) {}

  onOpen(data:any) {
    const headers = getHeaders();
    return this.http.get(this.baseurl+`/questions/${data}`,{headers});
  }
}
