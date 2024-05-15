import { Component, OnInit } from '@angular/core';
import { Empleado } from '../empleado';
import { CommonModule } from '@angular/common';
import { EmpleadoService } from '../empleado.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lista-empleados',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './lista-empleados.component.html',
  styleUrl: './lista-empleados.component.css'
})
export class ListaEmpleadosComponent implements OnInit{

  empleados:Empleado[];
empleado: any;

  constructor(private empleadoServicio:EmpleadoService, private router:Router){}
  ngOnInit(): void {
    this.obtenerEmpleados();    
  }

  actualizarEmpleado(id:number){
    this.router.navigate(['actualizar-empleado', id]);
  }

  eliminarEmpleado(id:number){
    this.empleadoServicio.eliminarEmpleado(id).subscribe(dato =>{
      console.log(dato);
      this.obtenerEmpleados();
    })
  }
  verEmpleado(id:number){
    this.router.navigate(['detalles-empleado', id]);

  }

  private obtenerEmpleados(){
    this.empleadoServicio.obtenerListaDeEmpleados().subscribe(dato =>{
      this.empleados = dato;
      
    });
    


}
}
