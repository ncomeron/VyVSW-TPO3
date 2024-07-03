package test;

import trenesSubterraneos.*;
import utiles.TecladoIn;
import java.io.*;
import java.util.Arrays;

public class MiSubte {

    public static void main(String[] args) throws IOException {
        TrenesSubterraneos miSubte = new TrenesSubterraneos();
        FileWriter archivo = new FileWriter("log.txt");
        PrintWriter escritor = new PrintWriter(archivo);
        int op;

        do {
            op = menu();
            switch (op) {
                case 1: //1.Crear sistema de prueba.
                    cargarSistemaTrenesSubterraneos(miSubte, escritor);
                    break;
                case 2: //2.Insertar estacion.
                    insertarEstacion(miSubte, escritor);
                    break;
                case 3: //3.Insertar vinculo estaciones.
                    insertarTramoDeRieles(miSubte, escritor);
                    break;
                case 4: //4.Insertar tren.
                    insertarTren(miSubte, escritor);
                    break;
                case 5: //5.Eliminar estacion.
                    eliminarEstacion(miSubte, escritor);
                    break;
                case 6: //6.Eliminar vinculo estaciones.
                    eliminarTramoDeRieles(miSubte, escritor);
                    break;
                case 7: //7.Eliminar tren.
                    eliminarTren(miSubte, escritor);
                    break;
                case 8: //8.Modificar linea tren.
                    modificarLineaTren(miSubte, escritor);
                    break;
                case 9: //9.Buscar tren y mostrar su informacion.
                    buscarTren(miSubte, escritor);
                    break;
                case 10: //10.Buscar estar estacion y mostrar su informacion.
                    buscarEstacion(miSubte, escritor);
                    break;
                case 11: //11.Buscar y devolver estaciones que comiencen con una determinada cadena.
                    estacionIniciaCon(miSubte, escritor);
                    break;
                case 12: //12.Mostrar camino que pasa por menos estaciones.
                    mostrarCaminoMenosEstaciones(miSubte, escritor);
                    break;
                case 13: //13.Mostrar camino que pasa por menos estaciones.
                    mostrarCaminoMenorTiempo(miSubte, escritor);
                    break;
                case 14: //14.Mostrar estaciones.
                    System.out.println("---------------------------------------------------------------");
                    miSubte.mostrarEstaciones();
                    System.out.println("---------------------------------------------------------------");
                    break;
                case 15: //15.Mostrar subte.
                    System.out.println("---------------------------------------------------------------");
                    miSubte.mostrarSubte();
                    System.out.println("---------------------------------------------------------------");
                    break;
                case 16: //16.Mostrar lineas.
                    System.out.println("---------------------------------------------------------------");
                    miSubte.mostrarLineas();
                    System.out.println("---------------------------------------------------------------");
                    break;
                case 17: //17.Mostrar trenes.
                    System.out.println("---------------------------------------------------------------");
                    miSubte.mostrarTrenes();
                    System.out.println("---------------------------------------------------------------");
                    break;
                case 18: //18.Mostrar sistema.
                    System.out.println("---------------------------------------------------------------");
                    miSubte.mostrarEstructuras();
                    System.out.println("---------------------------------------------------------------");
                    break;
            }
        } while (op != 0);
        archivo.close();
    }

    public static int menu() {
        int op;

        System.out.println("---------------------------------------------------------------");
        System.out.println("|1.Crear un sistema de trenes subterraneos precargado.        |");
        System.out.println("|2.Insertar una nueva estacion.                               |");
        System.out.println("|3.Insertar un nuevo tramo entre dos estaciones.              |");
        System.out.println("|4.Insertar un nuevo tren.                                    |");
        System.out.println("|5.Eliminar una estacion existente.                           |");
        System.out.println("|6.Eliminar un tramo ya existente entre dos estaciones.       |");
        System.out.println("|7.Eliminar un tren existente.                                |");
        System.out.println("|8.Modificar linea de un tren existente.                      |");
        System.out.println("|-------------------------------------------------------------|");
        System.out.println("|Consultas sobre Trenes:                                      |");
        System.out.println("|9.Buscar tren y mostrar su informacion.                      |");
        System.out.println("|-------------------------------------------------------------|");
        System.out.println("|Consultas sobre Estaciones:                                  |");
        System.out.println("|10.Buscar estacion y mostrar su informacion.                 |");
        System.out.println("|11.Estaciones cuyo nombre comienzan con una cadena dada.     |");
        System.out.println("|-------------------------------------------------------------|");
        System.out.println("|Consultas sobre Viajes:                                      |");
        System.out.println("|12.Camino de A a B que pase por menos cantidad de estaciones.|");
        System.out.println("|13.Camino de A a B que tarde menor cantidad de tiempo.       |");
        System.out.println("|-------------------------------------------------------------|");
        System.out.println("|14.Mostrar estaciones (Diccionario/AVL)                      |");
        System.out.println("|15.Mostrar subte (Grafo)                                     |");
        System.out.println("|16.Mostrar lineas (HashMap)                                  |");
        System.out.println("|17.Mostrar trenes (HashMap)                                  |");
        System.out.println("|18.Mostrar sistema.                                          |");
        System.out.println("|0.Salir                                                      |");
        System.out.println("---------------------------------------------------------------");

        do {
            op = TecladoIn.readLineInt();
            if (op < 0 || op > 18) {
                System.out.println("---------------------------------------------------------------");
                System.out.println("El valor ingresado no es válido");
                System.out.println("Reeingrese valor.");
                System.out.println("---------------------------------------------------------------");
            }
        } while (op < 0 || op > 18);
        return op;
    }

    public static void cargarSistemaTrenesSubterraneos(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        unSisTrenSub.altaEstacion("LAS HERAS", "Av Pueyrredon", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        unSisTrenSub.altaEstacion("SANTA FE", "Av Santa Fe", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("CORRIENTES", "Av Corrientes", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("ONCE", "Av de Mayo", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("HUMBERTO 1°", "Av Jujuy", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("HOSPITALES", "Sanchez de Loria", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        
        unSisTrenSub.altaEstacion("BOLIVAR", "Diag Sur", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        unSisTrenSub.altaEstacion("BELGRANO", "Av Belgrano", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("INDEPENDENCIA", "Av Independencia", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("JUJUY", "Av Varela", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("BOEDO", "Av Boedo", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("PLAZA DE LOS VIRREYES", "Av San Pedrito", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        
        unSisTrenSub.altaEstacion("CATEDRAL", "Diag Norte", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        unSisTrenSub.altaEstacion("9 DE JULIO", "Diag Norte", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("CALLAO", "Av Callao", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("PUEYRREDON", "Av Pueyrredon", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("PALERMO", "Av Juan B Justo", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("CONGRESO DE TUCUMAN", "Av Congreso", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        
        unSisTrenSub.altaEstacion("RETIRO", "Av R Mejia", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        unSisTrenSub.altaEstacion("DIAGONAL NORTE", "Diag Norte", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("AVENIDA DE MAYO", "Av de Mayo", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("MORENO", "Lima", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("SAN JUAN", "Av San Juan", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("CONSTITUCION", "B de Irigoyen", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        
        unSisTrenSub.altaEstacion("LEANDRO ALEM", "Av L N Alem", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        unSisTrenSub.altaEstacion("CARLOS PELLEGRINI", "Carlos Pellegrini", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("URUGUAY", "Av Corrientes", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("CARLOS GARDEL", "Av Corrientes", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("DORREGO", "Av Dorrego", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("JUAN MANUEL DE ROSAS", "Av Triunvirato", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        
        unSisTrenSub.altaEstacion("PLAZA DE MAYO", "Paseo Colon", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
        unSisTrenSub.altaEstacion("PERU", "Av de Mayo", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("LIMA", "Lima", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("PLAZA MISERERE", "Av Jujuy", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("PRIMERA JUNTA", "Rojas", "05:00 a 23:00hs", "{Rampas, Ascensor}", false);
        unSisTrenSub.altaEstacion("SAN PEDRITO", "Av Rivadavia", "05:00 a 23:00hs", "{Rampas, Ascensor}", true);
       
        unSisTrenSub.altaTramoDeRieles("SAN PEDRITO", "PRIMERA JUNTA", 30);
        unSisTrenSub.altaTramoDeRieles("PRIMERA JUNTA", "PLAZA MISERERE", 30);
        unSisTrenSub.altaTramoDeRieles("PLAZA MISERERE", "ONCE", 5);
        unSisTrenSub.altaTramoDeRieles("PLAZA MISERERE", "LIMA", 10);
        unSisTrenSub.altaTramoDeRieles("LIMA", "AVENIDA DE MAYO", 5);
        unSisTrenSub.altaTramoDeRieles("LIMA", "PERU", 20);
        unSisTrenSub.altaTramoDeRieles("PERU", "CATEDRAL", 5);
        unSisTrenSub.altaTramoDeRieles("PERU", "BOLIVAR", 5);
        unSisTrenSub.altaTramoDeRieles("PERU", "PLAZA DE MAYO", 10);
        
        unSisTrenSub.altaTramoDeRieles("JUAN MANUEL DE ROSAS", "DORREGO", 30);
        unSisTrenSub.altaTramoDeRieles("DORREGO", "CARLOS GARDEL", 20);
        unSisTrenSub.altaTramoDeRieles("CARLOS GARDEL", "CORRIENTES", 5);
        unSisTrenSub.altaTramoDeRieles("CARLOS GARDEL", "URUGUAY", 15);
        unSisTrenSub.altaTramoDeRieles("URUGUAY", "CARLOS PELLEGRINI", 10);
        unSisTrenSub.altaTramoDeRieles("CARLOS PELLEGRINI", "9 DE JULIO", 5);
        unSisTrenSub.altaTramoDeRieles("CARLOS PELLEGRINI", "LEANDRO ALEM", 10);
        
        unSisTrenSub.altaTramoDeRieles("CONSTITUCION", "SAN JUAN", 10);
        unSisTrenSub.altaTramoDeRieles("SAN JUAN", "INDEPENDENCIA", 5);
        unSisTrenSub.altaTramoDeRieles("SAN JUAN", "MORENO", 10);
        unSisTrenSub.altaTramoDeRieles("MORENO", "AVENIDA DE MAYO", 5);
        unSisTrenSub.altaTramoDeRieles("AVENIDA DE MAYO", "LIMA", 5);
        unSisTrenSub.altaTramoDeRieles("AVENIDA DE MAYO", "DIAGONAL NORTE", 10);
        unSisTrenSub.altaTramoDeRieles("DIAGONAL NORTE", "9 DE JULIO", 5);
        unSisTrenSub.altaTramoDeRieles("DIAGONAL NORTE", "RETIRO", 25);
        
        unSisTrenSub.altaTramoDeRieles("CONGRESO DE TUCUMAN", "PALERMO", 30);
        unSisTrenSub.altaTramoDeRieles("PALERMO", "PUEYRREDON", 20);
        unSisTrenSub.altaTramoDeRieles("PUEYRREDON", "SANTA FE", 5);
        unSisTrenSub.altaTramoDeRieles("PUEYRREDON", "CALLAO", 10);
        unSisTrenSub.altaTramoDeRieles("CALLAO", "9 DE JULIO", 15);
        unSisTrenSub.altaTramoDeRieles("9 DE JULIO", "CARLOS PELLEGRINI", 5);
        unSisTrenSub.altaTramoDeRieles("9 DE JULIO", "DIAGONAL NORTE", 5);
        unSisTrenSub.altaTramoDeRieles("9 DE JULIO", "CATEDRAL", 10);
        unSisTrenSub.altaTramoDeRieles("CATEDRAL", "BOLIVAR", 5);
        
        unSisTrenSub.altaTramoDeRieles("PLAZA DE LOS VIRREYES", "BOEDO", 40);
        unSisTrenSub.altaTramoDeRieles("BOEDO", "JUJUY", 10);
        unSisTrenSub.altaTramoDeRieles("JUJUY", "HUMBERTO 1°", 5);
        unSisTrenSub.altaTramoDeRieles("JUJUY", "INDEPENDENCIA", 10);
        unSisTrenSub.altaTramoDeRieles("INDEPENDENCIA", "SAN JUAN", 5);
        unSisTrenSub.altaTramoDeRieles("INDEPENDENCIA", "BELGRANO", 15);
        unSisTrenSub.altaTramoDeRieles("BELGRANO", "BOLIVAR", 10);
        
        unSisTrenSub.altaTramoDeRieles("HOSPITALES", "HUMBERTO 1°", 30);
        unSisTrenSub.altaTramoDeRieles("HUMBERTO 1°", "JUJUY", 5);
        unSisTrenSub.altaTramoDeRieles("HUMBERTO 1°", "ONCE", 25);
        unSisTrenSub.altaTramoDeRieles("ONCE", "PLAZA MISERERE", 5);
        unSisTrenSub.altaTramoDeRieles("ONCE", "CORRIENTES", 15);
        unSisTrenSub.altaTramoDeRieles("CORRIENTES", "CARLOS GARDEL", 5);
        unSisTrenSub.altaTramoDeRieles("CORRIENTES", "SANTA FE", 20);
        unSisTrenSub.altaTramoDeRieles("SANTA FE", "PUEYRREDON", 5);
        unSisTrenSub.altaTramoDeRieles("SANTA FE", "LAS HERAS", 10);
        
        unSisTrenSub.altaLinea("A", "Celeste", "SAN PEDRITO", "PLAZA DE MAYO");
        unSisTrenSub.altaLinea("B", "Rojo", "JUAN MANUEL DE ROSAS", "LEANDRO ALEM");
        unSisTrenSub.altaLinea("C", "Azul", "CONSTITUCION", "RETIRO");
        unSisTrenSub.altaLinea("D", "Verde", "CONGRESO DE TUCUMAN", "CATEDRAL");
        unSisTrenSub.altaLinea("E", "Violeta", "PLAZA DE LOS VIRREYES", "BOLIVAR");
        unSisTrenSub.altaLinea("H", "Amarillo", "HOSPITALES", "LAS HERAS");
        
        unSisTrenSub.altaTren("001", "A");
        unSisTrenSub.altaTren("002", "B");
        unSisTrenSub.altaTren("003", "C");
        unSisTrenSub.altaTren("004", "D");
        unSisTrenSub.altaTren("005", "E");
        unSisTrenSub.altaTren("006", "H");
        System.out.println("---------------------------------------------------------------");
        System.out.println("Sistema cargado correctamente."); 
        escritor.println("Sistema cargado correctamente.");
        System.out.println("---------------------------------------------------------------");
    }

    public static void insertarEstacion(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String nombre, direccion, horario, accesibilidad;
        boolean esExtremo, salida;

        System.out.println("Ingrese el nombre de la estacion:");
        nombre = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la direccion donde se encuentra ubicada:");
        direccion = TecladoIn.readLine();
        System.out.println("Ingrese el horario de funcionamiento (hh:mm a hh:mm):");
        horario = TecladoIn.readLine();
        System.out.println("Ingrese las facilidades de accesibilidad (si posee):");
        accesibilidad = TecladoIn.readLine();
        System.out.println("Ingrese si la estacion forma parte de algunos de los extremos del recorrido (true/false):");
        esExtremo = TecladoIn.readLineBoolean();
        salida = unSisTrenSub.altaEstacion(nombre, direccion, horario, accesibilidad, esExtremo);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("Estacion creada correctamente.");
            escritor.println("Estacion creada correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void eliminarEstacion(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String nombre;
        boolean salida;
        
        System.out.println("Ingrese el nombre de la estacion:");
        nombre = TecladoIn.readLine().toUpperCase();
        salida = unSisTrenSub.bajaEstacion(nombre);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("Estacion eliminada correctamente.");
            escritor.println("Estacion eliminada correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void insertarTramoDeRieles(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String origen, destino; 
        int tiempo;
        boolean salida;

        System.out.println("Ingrese la estacion a vincular:");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la segunda estacion a vincular:");
        destino = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el tiempo de viaje:");
        tiempo = TecladoIn.readLineInt();
        salida = unSisTrenSub.altaTramoDeRieles(origen, destino, tiempo);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("Vinculo entre estaciones creado correctamente.");
            escritor.println("Vinculo entre estaciones creado correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void eliminarTramoDeRieles(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String origen, destino;
        boolean salida;

        System.out.println("Ingrese una de las estaciones vinculadas:");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la estacion faltante vinculada con la anterior:");
        destino = TecladoIn.readLine().toUpperCase();
        salida = unSisTrenSub.bajaTramoDeRieles(origen, destino);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("Vinculo entre estaciones eliminado correctamente.");
            escritor.println("Vinculo entre estaciones eliminado correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void insertarLinea(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String linea, color, estacionIni, estacionFin;
        boolean salida;

        System.out.println("Ingrese nombre/numero de la linea:");
        linea = TecladoIn.readLine();
        System.out.println("Ingrese el color por el cual se encuentra representada en el mapa:");
        color = TecladoIn.readLine();
        System.out.println("Ingrese el nombre de la estacion donde comienza su recorrido:");
        estacionIni = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese el nombre de la estacion donde finaliza su recorrido:");
        estacionFin = TecladoIn.readLine().toUpperCase();
        salida = unSisTrenSub.altaLinea(linea, color, estacionIni, estacionFin);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("Linea creada correctamente.");
            escritor.println("Linea creada correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void insertarTren(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String id, linea;
        boolean salida;

        System.out.println("Ingrese numero de identificacion del tren:");
        id = TecladoIn.readLine();
        System.out.println("Ingrese el nombre/numero de la linea a la cual va a ser asignado:");
        linea = TecladoIn.readLine();
        salida = unSisTrenSub.altaTren(id, linea);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("Tren creado correctamente.");
            escritor.println("Tren creado correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void eliminarTren(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String id;
        boolean salida;
        
        System.out.println("Ingrese numero de identificacion del tren:");
        id = TecladoIn.readLine();
        salida = unSisTrenSub.bajaTren(id);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("Tren eliminado correctamente.");
            escritor.println("Tren eliminado correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void modificarLineaTren(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String id, linea;
        boolean salida;
        
        System.out.println("Ingrese numero de identificacion del tren:");
        id = TecladoIn.readLine();
        System.out.println("Ingrese el nombre/numero de la linea a la cual va a ser asignado:");
        linea = TecladoIn.readLine();
        salida = unSisTrenSub.modificarTrenExistente(id, linea);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        if (salida) {
            System.out.println("La operacion se concreto correctamente.");
            escritor.println("La operacion se concreto correctamente.");
        }
        else {
            System.out.println("ERROR: Ha ocurrido un error al realizar la operacion.");
            escritor.println("ERROR: Ha ocurrido un error al realizar la operacion.");
        }
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void buscarTren(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String id, salida;
        
        System.out.println("Ingrese numero de identificacion del tren:");
        id = TecladoIn.readLine();
        salida = unSisTrenSub.mostrarDatosTren(id);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        System.out.println("Datos del tren "+id+": "+salida);
        escritor.println("Datos del tren "+id+": "+salida);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void buscarEstacion(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String nombreEstacion, salida;
        
        System.out.println("Ingrese el nombre de la estacion:");
        nombreEstacion = TecladoIn.readLine().toUpperCase();
        salida = unSisTrenSub.mostrarDatosEstacion(nombreEstacion);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        System.out.println("Datos de la estacion: "+salida);
        escritor.println("Datos de la estacion: "+salida);
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void estacionIniciaCon(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String cadena;
        
        System.out.println("Ingrese la cadena por la cual comienza el nombre de la estacion:");
        cadena = TecladoIn.readLine().toUpperCase();
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        System.out.println("Estacion/es que contienen a la cadena '"+cadena+"': "+unSisTrenSub.estacionSegunCadena(cadena).toString());
        escritor.println("Estacion/es que contienen a la cadena '"+cadena+"': "+unSisTrenSub.estacionSegunCadena(cadena).toString());
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void  mostrarCaminoMenosEstaciones(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String origen, destino;

        System.out.println("Ingrese una de las estaciones:");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la estacion faltante:");
        destino = TecladoIn.readLine().toUpperCase();
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        System.out.println("A continuacion se indica el camino de "+origen+" a "+destino+" que pasa por la menor cantidad de estaciones: "+unSisTrenSub.caminoMenosEstaciones(origen, destino).toString());
        escritor.println("A continuacion se indica el camino de "+origen+" a "+destino+" que pasa por la menor cantidad de estaciones: "+unSisTrenSub.caminoMenosEstaciones(origen, destino).toString());
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
    
    public static void  mostrarCaminoMenorTiempo(TrenesSubterraneos unSisTrenSub, PrintWriter escritor) {
        String origen, destino;

        System.out.println("Ingrese una de las estaciones:");
        origen = TecladoIn.readLine().toUpperCase();
        System.out.println("Ingrese la estacion faltante:");
        destino = TecladoIn.readLine().toUpperCase();
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
        Object[] array = unSisTrenSub.caminoMenorTiempo(origen, destino);
        System.out.println("A continuacion se indica el camino de "+origen+" a "+destino+" que demora la menor cantidad de tiempo: "+Arrays.toString(array));
        escritor.println("A continuacion se indica el camino de "+origen+" a "+destino+" que demora la menor cantidad de tiempo: "+Arrays.toString(array));
        System.out.println("---------------------------------------------------------------");
        escritor.println("---------------------------------------------------------------");
    }
}
    
