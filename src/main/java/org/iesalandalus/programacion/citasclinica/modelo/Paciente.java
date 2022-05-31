package org.iesalandalus.programacion.citasclinica.modelo;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paciente {
	
	// 3.1 Atributos
	private static final String ER_DNI = "([0-9]{8})([a-zA-Z])";
	private static final String ER_TELEFONO = "[6-9][0-9]{8}";
	private String nombre, dni, telefono;

	
	// 3.5 Constructor con parámetros
	public Paciente(String nombre, String dni, String telefono) {
		setNombre(formateaNombre(nombre));
		setDni(comprobarLetraDni(dni));
		setTelefono(telefono);
}
	
	// 3.6 Constructor copia
	public Paciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: No es posible copiar un paciente nulo.");
		} else {
			setNombre(paciente.getNombre());
			setDni(paciente.getDni());
			setTelefono(paciente.getTelefono());
		}

	}
	
	//3.4 Métodos Get y Set
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		if (nombre == null) 
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		 
		
		if (nombre == null || nombre.trim().isEmpty())
			throw new NullPointerException("ERROR: El nombre de un paciente no puede ser nulo o vacío.");
		
			this.nombre = formateaNombre(nombre);

	}
	//3.2 Método formateaNombre
		private String formateaNombre(String nombre) {
			
			String Nombreformateado;
			Nombreformateado = nombre.toLowerCase();

			char[] caracteres = Nombreformateado.toCharArray();
			caracteres[0] = Character.toUpperCase(caracteres[0]);

			for (int i = 0; i < Nombreformateado.length() - 2; i++)
				if (caracteres[i] == ' ' || caracteres[i] == '.' || caracteres[i] == ',')
					caracteres[i + 1] = Character.toUpperCase(caracteres[i + 1]);

			Nombreformateado = String.valueOf(caracteres).trim().replace("  ", "");

			return Nombreformateado;
		}
		
	//3.4 Métodos Get y Set
	public String getDni() {
			return dni;
		}
	
	
	private void setDni(String dni) {
		if (dni == null || dni.trim().isEmpty())
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		
		//!dni.matcher(ER_DNI)
		if (!dni.matches(ER_DNI))
			throw new IllegalArgumentException("ERROR: El DNI no tiene un formato válido.");
		
		this.dni = dni;
	} 
	
	


	//3.3 Método comprobarLetraDNI
	private String comprobarLetraDni(String dni) {
		int numeroDni;
		char letraDni;
		char[] arrayLetras = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V',
				'H', 'L', 'C', 'K', 'E' };
		Pattern patron;
		Matcher comparar;
		if (dni == null) {
			throw new NullPointerException("ERROR: El DNI de un paciente no puede ser nulo o vacío.");
		} else {
			patron = Pattern.compile(ER_DNI);
			if (!dni.matches(ER_DNI)) {
				throw new IllegalArgumentException("ERROR: Este no es un número de DNI.");
			} else {
				comparar = patron.matcher(dni);
				comparar.matches();
				numeroDni = Integer.parseInt(comparar.group(1));
				letraDni = comparar.group(2).charAt(0);
			}
			if (letraDni != arrayLetras[numeroDni % 23]) {
				throw new IllegalArgumentException("ERROR: La letra no es correcta.");
			} else {
				return dni;
			}
		}
	}



	//3.4 Métodos Get y Set
	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {

		if (telefono == null) {
			throw new NullPointerException("ERROR: El teléfono de un paciente no puede ser nulo o vacío.");
		} else if (!telefono.matches(ER_TELEFONO)) {
			throw new IllegalArgumentException("ERROR: Este número de teléfono no es correcto");
		} else if (telefono.matches(ER_TELEFONO)) {
			this.telefono = telefono;
		}

	}
	
	//3.7 Métodos equals y hashCode
	@Override //MODIFICAR
	public int hashCode() {
		return Objects.hash(dni, nombre, telefono);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(dni, other.dni);
	}

	//3.9 Método toString
	@Override
	public String toString() {
		return "Paciente [nombre=" + nombre + ", dni=" + dni + ", telefono=" + telefono + "]";
	}
	//3.8 Método getIniciales
		private String getIniciales() {
			String iniciales = "";
			char inicial;

			for (int i = 0; i < formateaNombre(nombre).length(); i++) {
				if (Character.isUpperCase(formateaNombre(nombre).charAt(i))) {
					inicial = formateaNombre(nombre).charAt(i);
					iniciales += inicial;
				}
			}
			return iniciales;
		}

	
}
	