export class Empleado {
    id:number;
    nombre:string;
    apellido:string;
    email:string;
    username:string;
    password:string;
    roles:[
        { id: 1, name: 'admin', description: 'Administrator' },
        { id: 2, name: 'editor', description: 'Editor' },];
}
