package org.iesalandalus.programacion.citasclinica.vista;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	// 7.1 Contructor

		private Consola() {
		}

		// 7.2 Método mostrarMenu

		public static void mostrarMenu() {
			System.out.println("----------------------------------------------------");
			System.out.println("             GESTION DE CITAS DE LA CLÍNICA         ");
			System.out.println("----------------------------------------------------");
			System.out.println("1 - INSERTAR CITA NUEVA ");
			System.out.println("2 - BUSCAR CITA");
			System.out.println("3 - BORRAR CITA");
			System.out.println("4 - MOSTRAR TODAS LAS CITAS");
			System.out.println("5 - Mostrar LAS CITAS DE UNA FECHA ");
			System.out.println("6 - SALIR");
			System.out.println("----------------------------------------------------");
		}
		
		// 7.3 Método elegirOpcion

		public static Opciones elegirOpcion() {
			int opcion = 0;
			do {
				System.out.println("¿Qué opción prefieres?");
				opcion = Entrada.entero();
			} while (opcion > 0 || opcion < 7);

			switch (opcion) {
			case 1:
				return Opciones.INSERTAR_CITA;
			case 2:
				return Opciones.BUSCAR_CITA;
			case 3:
				return Opciones.BORRAR_CITA;
			case 4:
				return Opciones.MOSTRAR_CITAS;
			case 5:
				return Opciones.MOSTRAR_CITAS_DIA;
			case 6:
				return Opciones.SALIR;
			default:
				return null;
			}
		}
		
		// 7.4 Método leerPaciente
		public static Paciente leerPaciente() {
			String nombre, dni, telefono;
			System.out.println( "DATOS DEL PACIENTE");
			do {
				System.out.println("Introduce el nombre del paciente");
				nombre = Entrada.cadena();
			} while (nombre.length() < 2);

			do {
				System.out.println("Introduce el DNI del paciente");
				dni = Entrada.cadena();
			} while (dni.length() < 9);
			
			do {
				System.out.println("Introduce el telefono del paciente");
				telefono = Entrada.cadena();
			} while (telefono.length() < 9);


			Paciente paciente = new Paciente(nombre, dni, telefono);

			return paciente;
		}
		
		// 7.5 Método leerFechaHora

		public static LocalDateTime leerFechaHora() {
			LocalDateTime localDateTime;
			int dia, mes, annio, hora, min;

			System.out.println(" FECHA ");
			do {
				System.out.println("Introduce el año");
				annio = Entrada.entero();
			} while (annio < 2019);

			do {
				System.out.println("Introduce el mes");
				mes = Entrada.entero();
			} while (mes < 1 || mes > 12);

			do {
				System.out.println("Introduce el dia");
				dia = Entrada.entero();
			} while (dia < 1 || dia > 31);

			System.out.println("** HORA **");
			do {
				System.out.println("Introduce la hora");
				hora = Entrada.entero();
			} while ((hora < 0 || hora > 23));

			do {
				System.out.println("Introduce los minutos");
				min = Entrada.entero();
			} while (min < 0 || min > 59);

			return localDateTime = LocalDateTime.of(annio, mes, dia, hora, min);
		}

		
		// 7.6. Método leerCita

		public static Cita leerCita() {
			Paciente paciente = leerPaciente();
			LocalDateTime fechaHora = leerFechaHora();
			
			Cita cita = new Cita(paciente, fechaHora);

			return cita;
		}
		
		// 7.7 Método leerFecha
		public static LocalDate leerFecha() {
			LocalDate localDate;
			int dia, mes, anio;

			System.out.println(" FECHA ");
			do {
				System.out.println("Introduce el año");
				anio = Entrada.entero();
			} while (anio < 2022);

			do {
				System.out.println("Introduce el mes");
				mes = Entrada.entero();
			} while (mes < 1 || mes > 12);

			do {
				System.out.println("Introduce el dia");
				dia = Entrada.entero();
			} while (dia < 1 || dia > 31);

			return localDate = LocalDate.of(anio, mes, dia);
		}
	}
