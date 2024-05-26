import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cliente';
import { FormsModule } from '@angular/forms';
import { CommonModule, NgIf } from '@angular/common';
import { ClienteService } from '../cliente.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-registrar-cliente',
  standalone: true,
  imports: [FormsModule, NgIf, CommonModule],
  templateUrl: './registrar-cliente.component.html',
  styleUrls: ['./registrar-cliente.component.css']
})
export class RegistrarClienteComponent implements OnInit {

  cliente: Cliente = new Cliente();

  constructor(private clienteService: ClienteService, private router: Router) { }

  ngOnInit(): void {
    console.log(this.cliente);
  }

  onSubmit(): void {
    console.log(this.cliente);
    this.guardarCliente();
  }

  guardarCliente(): void {
    this.clienteService.registrarCliente(this.cliente).subscribe(data => {
      console.log(data);
      this.verListaClientes();
    }, error => console.log(error));
  }

  verListaClientes(): void {
    this.router.navigate(['/clientes']);
  }
}
