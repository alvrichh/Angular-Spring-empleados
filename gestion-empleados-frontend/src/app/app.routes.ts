import { Routes } from '@angular/router';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { ActualizarEmpleadoComponent } from './actualizar-empleado/actualizar-empleado.component';
import { DetallesEmpleadoComponent } from './detalles-empleado/detalles-empleado.component';


export const routes: Routes = [
    //redirecciona al componente
    {path : 'empleados', component: ListaEmpleadosComponent},
    {path: '', redirectTo:'empleados', pathMatch: 'full'},
    {path : 'registrar-empleado', component: RegistrarEmpleadoComponent},
    {path : 'actualizar-empleado/:id', component: ActualizarEmpleadoComponent},
    {path : 'detalles-empleado/:id', component: DetallesEmpleadoComponent}

];
