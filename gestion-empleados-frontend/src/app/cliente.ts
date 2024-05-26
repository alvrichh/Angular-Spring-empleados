export class Cliente {
[x: string]: any;
    id: number;
    nombreCompleto: string;
    dni: string;
    comercializadora: string;
    email: string;
    telefono: string;
    numeroCUPS: string;
    empleado: Empleado; // Propiedad para almacenar el empleado asociado

  }
  export class Empleado {
    // Otras propiedades del empleado
    usuario: string; // Propiedad para almacenar el usuario del empleado
  }
  