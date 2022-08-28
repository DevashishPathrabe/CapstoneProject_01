import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { BASE_URL } from '../constants/constants';

@Injectable({
  providedIn: 'root',
})

export class UploadImageService {

  constructor(private http: HttpClient) {}

  uploadImage(file: any): Observable<any> {
    const formData = new FormData();
    formData.append('image', file, file.name); // Store form name as "file" with file data
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    });
    return this.http.post(`${BASE_URL}/images`, formData, { headers }); // Make http post request over api with formData as req
  }

  getImage(imageName: string): Observable<any> {
    const headers = new HttpHeaders({
      Authorization: `Bearer ${localStorage.getItem('token')}`,
    });
    return this.http.get(`${BASE_URL}/images/${imageName}`, { headers });
  }

}
