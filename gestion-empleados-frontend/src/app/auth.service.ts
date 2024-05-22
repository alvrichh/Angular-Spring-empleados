import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1/auth'; // URL de la API externa

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    const body = { username, password }; // Datos del formulario
    return this.http.post(`${this.apiUrl}/signin`, body);
  }
}