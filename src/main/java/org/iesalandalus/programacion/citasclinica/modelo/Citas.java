package org.iesalandalus.programacion.citasclinica.modelo;

import javax.naming.OperationNotSupportedException;
import java.time.LocalDate;

public class Citas {
	//5.1 Atributos
	private int capacidad;
	private int tamano;
	private Cita[] coleccionCitas;
	
	//Constructor
	public Citas(int capacidad) {
		if (capacidad < 1) {
			throw new IllegalArgumentException("ERROR: La capacidad debe ser mayor que cero.");
		}
		coleccionCitas = new Cita[capacidad];
		this.capacidad = capacidad;
		this.tamano = 0;
	}
	
	//5.2 Método get sin parámetros 

	public Cita[] getCitas() {
		return coleccionCitas;
	}
	
	// 5.9 Método getCitas
	public Cita[] getCitas(LocalDate fecha) {
		if (fecha == null) {
			throw new NullPointerException("ERROR: No se pueden devolver las citas para un día nulo.");
		}
		Cita[] fechaCita = new Cita[tamano];
		int citas = 0;
		for (int i = 0; !tamanoSuperado(i); i++) {
			if (coleccionCitas[i].getFechaHora().toLocalDate().equals(fecha)) {
				fechaCita[citas++] = new Cita(coleccionCitas[i]);
			}
		}
		return fechaCita;
	}
	
	// 5.2 Método get sin parámetros

	public int getTamano() {
		return tamano;
	}

	public int getCapacidad() {
		return capacidad;
	}
	
	// 5.5 Método insertar

	public void insertar(Cita cita) throws OperationNotSupportedException {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede insertar una cita nula.");
		}
		
		if (capacidadSuperada(buscarIndice(cita))) {
			throw new OperationNotSupportedException("ERROR: No se aceptan más citas.");
		}
		
		if (buscar(cita) != null) {
			throw new OperationNotSupportedException("ERROR: Ya existe una cita para esa fecha y hora.");
		}

		coleccionCitas[getTamano()] = new Cita(cita);
		tamano++;
	}
	
	// 5.4 Método buscarIndice

		private int buscarIndice(Cita cita) {

			if (cita == null) {
				throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
			}
			
			int indice = 0;
			boolean indiceEncontrado = false;
			while (!tamanoSuperado(indice) && indiceEncontrado == false) {
				if (coleccionCitas[indice].equals(cita)) {
					indiceEncontrado = true;
				} else {
					indice++;
				}

			}
			return indice;
		}
		// 5.3 Métodos capacidadSuperada y tamanoSuperado

		private boolean tamanoSuperado(int indice) {
			return indice >= tamano;

		}

		private boolean capacidadSuperada(int indice) {
			return indice >= capacidad;
		}
		// 5.6 Método buscar

		public Cita buscar(Cita cita) {
			if (cita == null) {
				throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
			}

			int indice = buscarIndice(cita);
			if (!tamanoSuperado(indice)) {
				return new Cita(coleccionCitas[indice]);
			} else {
				return null;
			}

		}
		
		// 5.8 Método borrar

		public void borrar(Cita cita) throws OperationNotSupportedException {
			if (cita == null) {
				throw new IllegalArgumentException("ERROR: No se puede borrar una cita nula.");
			}

			if (!tamanoSuperado (buscarIndice(cita))) {
				desplazarUnaPosicionHaciaIzquierda(buscarIndice(cita));
			} else {
				throw new OperationNotSupportedException("ERROR: No existe ninguna cita para esa fecha y hora.");
			}

			tamano--;

		}
		// 5.7 Método desplazarUnaPosicionHaciaIzquierda

		private void desplazarUnaPosicionHaciaIzquierda(int posicion) {
			if (!coleccionCitas[posicion].equals(null)) {
				throw new IllegalArgumentException("ERROR: Ya hay una cita en esta posicion");
			} else {
				coleccionCitas[posicion].equals(null);

				for (int i = posicion + 1; i < coleccionCitas.length; i++) {
					if (!coleccionCitas[i].equals(null)) {
						coleccionCitas[i - 1] = coleccionCitas[i];
					}
				}
			}
		}


}

