import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import swal from 'sweetalert2';
4
@Component({
  selector: 'app-signin',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  constructor(private authService: AuthService) {} // Inyecta el servicio AuthService
  username: string = '';
  password: string = '';
  token: string = '';


  login() {
    console.log("Intentando iniciar sesión con:", this.username, this.password);
    this.authService.login(this.username, this.password).subscribe({
      next: (response: any) => {
        this.token = response.token; // Almacena el token en la propiedad token
        localStorage.setItem('token', this.token); // También puedes seguir almacenándolo en el localStorage
        swal('Inicio de sesión exitoso', 'Has iniciado sesión correctamente', 'success');
      },
      error: (error: any) => {
        console.error('Error al iniciar sesión:', error);
        swal('Error al iniciar sesión', 'Usuario no encontrado o credenciales incorrectas', 'error');
      }
    });
  }
}