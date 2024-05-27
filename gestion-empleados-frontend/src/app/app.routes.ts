import { Routes } from '@angular/router';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';
import { RegistrarEmpleadoComponent } from './registrar-empleado/registrar-empleado.component';
import { ActualizarEmpleadoComponent } from './actualizar-empleado/actualizar-empleado.component';
import { DetallesEmpleadoComponent } from './detalles-empleado/detalles-empleado.component';
import { LoginComponent } from './login/login.component';
import { RegistrarClienteComponent } from './registrar-cliente/registrar-cliente.component';
import { ListaClientesComponent } from './lista-clientes/lista-clientes.component'; // Importa el componente ListaClientesComponent

export const routes: Routes = [
    // Redirecciona al componente
    { path: 'empleados', component: ListaEmpleadosComponent, data: { role: 'ADMIN' } },
    { path: '', redirectTo: 'login', pathMatch: 'full' },
    { path: 'login', component: LoginComponent },
    { path: 'registrar-empleado', component: RegistrarEmpleadoComponent },
    { path: 'actualizar-empleado/:id', component: ActualizarEmpleadoComponent },
    { path: 'detalles-empleado/:id', component: DetallesEmpleadoComponent },
    { path: 'registrar-cliente', component: RegistrarClienteComponent },
    { path: 'lista-clientes', component: ListaClientesComponent }, // Agrega la ruta para ListaClientesComponent
];
