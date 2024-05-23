import { Routes } from '@angular/router';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { ActualizarEmpleadoComponent } from './actualizar-empleado/actualizar-empleado.component';
import { DetallesEmpleadoComponent } from './detalles-empleado/detalles-empleado.component';
import { LoginComponent } from './login/login.component';


export const routes: Routes = [
    //redirecciona al componente
    {path : 'empleados', component: ListaEmpleadosComponent},
    {path: '', redirectTo:'index', pathMatch: 'full'},
    {path : 'login', component: LoginComponent},
    {path : 'registrar-empleado', component: RegistrarEmpleadoComponent},
    {path : 'actualizar-empleado/:id', component: ActualizarEmpleadoComponent},
    {path : 'detalles-empleado/:id', component: DetallesEmpleadoComponent}
];
