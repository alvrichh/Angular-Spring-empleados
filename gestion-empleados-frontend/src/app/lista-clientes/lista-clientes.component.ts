import { Component, OnInit } from '@angular/core';
import { ClienteService } from '../cliente.service';
import { Router } from '@angular/router';
import { Cliente } from '../cliente'; // Importa la clase Cliente
import { Empleado } from '../empleado'; // Importa la clase Empleado
import { CommonModule } from '@angular/common';
import swal from 'sweetalert2';

@Component({
  selector: 'app-lista-clientes',
  standalone: true,
  imports: [CommonModule], // No necesitas importar Empleado aquí
  templateUrl: './lista-clientes.component.html',
  styleUrls: ['./lista-clientes.component.css']
})
export class ListaClientesComponent implements OnInit {

  clientes: Cliente[] = [];

  constructor(private clienteService: ClienteService, private router: Router) { }

  ngOnInit(): void {
    this.obtenerClientes();
  }

  private obtenerClientes() {
    this.clienteService.obtenerListaDeClientes().subscribe(data => {
      this.clientes = data;
    });
  }

  verCliente(id: number) {
    this.router.navigate(['ver-cliente', id]);
  }

  actualizarCliente(id: number) {
    this.router.navigate(['actualizar-cliente', id]);
  }

  eliminarCliente(id: number) {
    swal({
      title: '¿Estas seguro?',
      text: "Confirma si deseas eliminar al cliente",
      type: 'warning',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Si, elimínalo',
      cancelButtonText: 'No, cancelar',
      confirmButtonClass: 'btn btn-success',
      cancelButtonClass: 'btn btn-danger',
      buttonsStyling: true
    }).then((result) => {
      if (result.value) {
        this.clienteService.eliminarCliente(id).subscribe(data => {
          console.log(data);
          this.obtenerClientes();
          swal(
            'Cliente eliminado',
            'El cliente ha sido eliminado con éxito',
            'success'
          )
        });
      }
    });
  }
}
