import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})
export class UploadFilesService {
  private baseURL = BASE_URL;
  constructor(private http: HttpClient) {}
  // Returns an observable
  upload(file: any): Observable<any> {
    // Create form data
    const formData = new FormData();

    // Store form name as "file" with file data
    formData.append('image', file, file.name);

    // Make http post request over api
    // with formData as req
    return this.http.post(`${this.baseURL}/images`, formData);
  }
  getFiles(): Observable<any> {
    return this.http.get(`${this.baseURL}/images`);
  }
}
