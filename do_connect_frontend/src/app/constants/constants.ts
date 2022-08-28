import { JwtPayload } from "jwt-decode";

export const BASE_URL = 'http://localhost:9090/api/v1';

export interface JWTCustomPayload extends JwtPayload {
    isAdmin: boolean;
    email: string;
    name: string;
}

export interface UserRegisterType {
    username: string;
    name: string;
    email: string;
    password: string;
    isAdmin: boolean;
}

export interface UserLoginType {
    username: string;
    password: string;
}

export interface QuestionPostType {
    question: string;
    topic: string;
    images: string[];
}

export interface AnswerPostType {
    answer: string;
    images: string[];
}
