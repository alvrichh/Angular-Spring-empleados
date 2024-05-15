import { Component, OnInit } from '@angular/core';
import { Empleado } from '../empleado';
import { ActivatedRoute } from '@angular/router';
import { EmpleadoService } from '../empleado.service';

@Component({
  selector: 'app-detalles-empleado',
  standalone: true,
  imports: [],
  templateUrl: './detalles-empleado.component.html',
  styleUrl: './detalles-empleado.component.css'
})
export class DetallesEmpleadoComponent implements OnInit {

  id:number;
  empleado:Empleado;


  constructor(private route:ActivatedRoute, private empleadoServicio:EmpleadoService){}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.empleado = new Empleado();
    this.empleadoServicio.obtenerEmpleadoPorId(this.id).subscribe(dato =>{
      this.empleado = dato;
    })

  }
}
