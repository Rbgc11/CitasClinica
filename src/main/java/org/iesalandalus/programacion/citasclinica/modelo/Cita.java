package org.iesalandalus.programacion.citasclinica.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Cita {

	// 4.1 Atributos

	public final static String FORMATO_FECHA_HORA = "dd/MM/yyyy HH:mm";
	private LocalDateTime fechaHora;
	private Paciente paciente;

	// 4.3 Constructor con parámetros

	public Cita(Paciente paciente, LocalDateTime fechaHora) {
		setPaciente(paciente);
		setFechaHora(fechaHora);
	}
	// 4.4 Constructor copia

	public Cita(Cita cita) {
		if (cita == null) {
			throw new NullPointerException("ERROR: No se puede copiar una cita nula.");
		}
		setPaciente(cita.getPaciente());
		setFechaHora(cita.getFechaHora());
	}
	
	// 4.2 Métodos Get y Set
	private void setPaciente(Paciente paciente) {
		if (paciente == null) {
			throw new NullPointerException("ERROR: El paciente de una cita no puede ser nulo.");
		}
		this.paciente = new Paciente(paciente);
	}
	
	public Paciente getPaciente() {
		return new Paciente(paciente);
	}


	public void setFechaHora(LocalDateTime fechaHora) {
		if (fechaHora == null) {
			throw new NullPointerException("ERROR: La fecha y hora de una cita no puede ser nula.");
		}
		this.fechaHora = fechaHora;
	}
	
	public LocalDateTime getFechaHora() {
		return fechaHora;
	}

	// 4.5 Métodos equals y hashCode
	@Override
	public int hashCode() {
		return Objects.hash(fechaHora, paciente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cita other = (Cita) obj;
		return Objects.equals(fechaHora, other.fechaHora);
	}
	

	// 4.6 Método toString

	@Override
	public String toString() {
		return String.format("%s, fechaHora=%s", paciente.toString(),
				fechaHora.format(DateTimeFormatter.ofPattern(FORMATO_FECHA_HORA)));
	}



}