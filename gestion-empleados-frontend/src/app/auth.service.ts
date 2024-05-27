import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/v1/auth'; // URL de la API externa
  private jwtHelper = new JwtHelperService();

  constructor(private http: HttpClient) {}

  login(username: string, password: string) {
    const body = { username, password }; // Datos del formulario
    this.getEmpleadoId();
    return this.http.post(`${this.apiUrl}/login`, body);
  }

  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string {
    return localStorage.getItem('token');
  }

  getEmpleadoId(): number {
    const token = this.getToken();
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      const empleadoId = decodedToken.empleadoId;
      console.log('ID del empleado:', empleadoId);
      return decodedToken.empleadoId; // Asegúrate de que el token contenga el ID del empleado con esta clave
    }
    console.log("error");
    return null; // O lanza un error si el ID no se encuentra
  }

  isAdmin(): boolean {
    const token = this.getToken();
    if (token) {
      const decodedToken = this.jwtHelper.decodeToken(token);
      return decodedToken.role === 'ADMIN'; // Asegúrate de que el token contenga la clave de rol
    }
    return false;
  }
}
