import { Routes } from '@angular/router';
import { ListaEmpleadosComponent } from './lista-empleados/lista-empleados.component';


export const routes: Routes = [
    //redirecciona al componente
    {path : 'empleados', component: ListaEmpleadosComponent},
    {path: '', redirectTo:'empleados', pathMatch: 'full'}
];
