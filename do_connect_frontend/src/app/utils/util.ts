import { HttpHeaders } from '@angular/common/http';
import jwtDecode from 'jwt-decode';
import { JWTCustomPayload } from '../constants/constants';

export const isUserLoggedIn = () => {
  const token = localStorage.getItem('token');
  return (token !== null && token !== '' && token.split('.').length === 3);
};

export const isUserAdmin = () => {
  if (isUserLoggedIn()) {
    const token = localStorage.getItem('token');
    if (token) {
      const jwtData: JWTCustomPayload = jwtDecode(token);
      return jwtData.isAdmin;
    }
    return false;
  }
  return false;
};

export const getCurrentUsername = () => {
  if (isUserLoggedIn()) {
    const token = localStorage.getItem('token');
    if (token) {
      const jwtData: JWTCustomPayload = jwtDecode(token);
      return jwtData.sub;
    }
    return undefined;
  }
  return undefined;
};
