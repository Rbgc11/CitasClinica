package org.iesalandalus.programacion.citasclinica;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.citasclinica.modelo.Cita;
import org.iesalandalus.programacion.citasclinica.modelo.Citas;
import org.iesalandalus.programacion.citasclinica.modelo.Paciente;
import org.iesalandalus.programacion.citasclinica.vista.Consola;
import org.iesalandalus.programacion.citasclinica.vista.Opciones;

public class MainApp {

	// 8.1 Atributos

		public static final int NUM_MAX_CITAS = 12;
		private static Citas citasClinica = new Citas(NUM_MAX_CITAS);
		
		//8.8 Método main
		
		public static void main(String[] args) throws OperationNotSupportedException {

			Consola.mostrarMenu();
			ejecutarOpcion(Consola.elegirOpcion());
		}
		// 8.7 Método ejecutarOpción

		private static void ejecutarOpcion(Opciones opcion) throws OperationNotSupportedException {
			switch (opcion) {
			case INSERTAR_CITA:
				System.out.println("---INSERTAR CITA ---");
				insertarCita();
				break;
			case BUSCAR_CITA:
				System.out.println("--- BUSCAR CITA ---");
				buscarCita();
				break;
			case BORRAR_CITA:
				System.out.println("--- BORRAR CITA ---");
				borrarCita();
				break;
			case MOSTRAR_CITAS_DIA:
				System.out.println("--- MOSTRAR CITA DIA ---");
				mostrarCitasDia();
				break;
			case MOSTRAR_CITAS:
				System.out.println("--- MOSTRAR CITA ---");
				mostrarCitas();
				break;
				
			case SALIR:
				break;

			}
			}
			
		// 8.2 Insertar cita

		private static void insertarCita() {

			try {
				Cita cita = Consola.leerCita();
				citasClinica.insertar(cita);
				System.out.println("La cita fue asignada correctamente.");

			} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException ex) {
				System.out.println("Se ha producido este error: " + ex.getMessage());
			}

		}
		
		// 8.3 Método buscarCita

		private static void buscarCita() throws OperationNotSupportedException {

			DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
			LocalDateTime fecha = Consola.leerFechaHora();

			Paciente paciente = new Paciente("Nombre", "73669103B", "689383982");
			Cita cita = new Cita(paciente, fecha);

			if ((cita = citasClinica.buscar(cita)) == null) {
				System.out.println("No existen citas para esa fecha: " + fecha.format(formato));
			} else {
				System.out.println(cita);
					} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException ex) {
						System.out.println("Se ha producido este error: " + ex.getMessage());
						ejecutarOpcion(Consola.elegirOpcion());
		}
		
		// 8.4 Método borrarCita

		private static void borrarCita() throws OperationNotSupportedException {

			LocalDateTime fecha = Consola.leerFechaHora();
			Paciente paciente = new Paciente("Nombre", "73669103B", "689383982");
			Cita cita = new Cita(paciente, fecha);

			try {
				citasClinica.borrar(cita);
				System.out.println("Cita borrada correctamente.");

			} catch (OperationNotSupportedException | IllegalArgumentException | NullPointerException ex) {
				System.out.println("Se ha producido este error: " + ex.getMessage());
				ejecutarOpcion(Consola.elegirOpcion());
			}
		}
		
		// 8.5 Método mostrarCitas

		private static void mostrarCitas() throws OperationNotSupportedException {
			boolean existenCitas = false;

			try {
				for (int i = 0; i < citasClinica.getTamano(); i++) {
					System.out.println(citasClinica.getCitas()[i]);
					existenCitas = true;
				}
				if (hayCitas == false) {
					System.out.println("No existen citas");
				}

			} catch (NullPointerException nul) {
				System.out.println(nul.getMessage());
			}

		}

		
		// 8.6. Método mostrarCitasDia

		private static void mostrarCitasDia() throws OperationNotSupportedException {
			LocalDate fecha = Consola.leerFecha();
			Cita[] citasFecha = citasClinica.getCitas(fecha);
			try {
				boolean existenCitas = false;
				for (int i = 0; i < citasClinica.getTamano(); i++) {
					if (citasFecha[i].getFechaHora().toLocalDate().equals(fecha)) {
						System.out.println(citasFecha()[i]);
						existenCitas = true;
					}
				}
				if (existenCitas == false) {
					System.out.println("No existen citas de ese día.");
				}
			} catch (NullPointerException nul) {
				System.out.println(nul.getMessage());
			}
		}
		
	

}
	

