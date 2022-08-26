import { HttpHeaders } from '@angular/common/http';

export function getHeaders() {
  const token = localStorage.getItem('token');
  const headers = new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: `Bearer ${token}`,
  });
  return headers;
}
