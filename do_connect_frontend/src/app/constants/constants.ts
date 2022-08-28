import { JwtPayload } from "jwt-decode";

export const BASE_URL = 'http://localhost:9090/api/v1';

export interface JWTCustomPayload extends JwtPayload {
    isAdmin: boolean;
    email: string;
    name: string;
}
