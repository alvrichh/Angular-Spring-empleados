import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from './cliente';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  // Esta URL obtiene el listado de todos los clientes en el backend
  private baseURL = "http://localhost:8080/api/v1/clientes";

  constructor(private httpClient: HttpClient) { }

  // Este método nos sirve para obtener los clientes
  obtenerListaDeClientes(): Observable<Cliente[]> {
    return this.httpClient.get<Cliente[]>(`${this.baseURL}`);
  }

  // Este método nos sirve para registrar un cliente
  registrarCliente(cliente: Cliente): Observable<Object> {
    return this.httpClient.post(`${this.baseURL}`, cliente);
  }

  // Este método nos sirve para actualizar un cliente
  actualizarCliente(id: number, cliente: Cliente): Observable<Object> {
    return this.httpClient.put(`${this.baseURL}/${id}`, cliente);
  }

  // Este método nos sirve para obtener un cliente por ID
  obtenerClientePorId(id: number): Observable<Cliente> {
    return this.httpClient.get<Cliente>(`${this.baseURL}/${id}`);
  }

  // Este método nos sirve para eliminar un cliente
  eliminarCliente(id: number): Observable<Object> {
    return this.httpClient.delete(`${this.baseURL}/${id}`);
  }
}
