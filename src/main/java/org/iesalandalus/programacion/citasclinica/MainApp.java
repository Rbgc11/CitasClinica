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

			Opciones opcion;
			
			do {
				System.out.println("Programa para gestionar las citas de la clínica.");
				Consola.mostrarMenu();				
				opcion = Consola.elegirOpcion();
				ejecutarOpcion(opcion);
				
			} while (opcion != Opciones.SALIR);
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
			
		
		try {
			LocalDateTime fecha = Consola.leerFechaHora();
			Paciente paciente = new Paciente("Nombre", "73669103B", "689383982");
			Cita cita = new Cita(paciente, fecha);

			Cita citaBuscada = citasClinica.buscar(cita);
			
			if (citaBuscada == null)
				System.out.println(" No existe la cita ");
			else 
				System.out.println(citaBuscada);
			
		} catch (IllegalArgumentException | NullPointerException e) {
			System.out.println(e.getMessage());
		}
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
			Cita[] citas = citasClinica.getCitas();
			int citasMostrar = 0;
			
			for (int i = 0; i < citas.length; i++) {
				if(citas[i] != null) {
					System.out.println(citas[i]);
					citasMostrar++;
				}
			}
			if (citasMostrar == 0) {
				System.out.println("No hay citas para ese dia");
			} else  {
				System.out.println("");
			
		}
	}

		
		// 8.6. Método mostrarCitasDia

		private static void mostrarCitasDia() throws OperationNotSupportedException {
				
				Cita[] citasDia	 = citasClinica.getCitas(Consola.leerFecha());
				int citasMostrar = 0;
				
				for (int i = 0; i < citasDia.length; i++) {
					if(citasDia[i] != null) {
						System.out.println(citasDia[i]);
						citasMostrar++;
					}
				}
				if (citasMostrar == 0) {
					System.out.println("No hay citas para ese dia");
				} else  {
					System.out.println("");
				}
			}
	
}
	

