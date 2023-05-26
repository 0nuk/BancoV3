package bio.kuno.banco.modelo;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
@Entity
@Table(name = "tarjetas")
public class Tarjeta implements Serializable, Comparable<Tarjeta>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tarjeta")
	@Expose
	private int id;
	@Expose
	private String pan;
	@Expose
	private String marca;
	@Expose
	private String tipo;
	@Column(name = "anyo_vencimiento")
	@Expose
	private int anyoVencimiento;
	@Column(name = "mes_vencimiento")
	@Expose
	private int mesVencimiento;
	@ManyToOne
	@JoinColumn(name = "fk_cuenta")
	private Cuenta cuenta;
	@OneToMany(mappedBy = "tarjeta")
	private Set<Movimiento> movimientos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public int getAnyoVencimiento() {
		return anyoVencimiento;
	}
	public void setAnyoVencimiento(int anyoVencimiento) {
		this.anyoVencimiento = anyoVencimiento;
	}
	public int getMesVencimiento() {
		return mesVencimiento;
	}
	public void setMesVencimiento(int mesVencimiento) {
		this.mesVencimiento = mesVencimiento;
	}
	public Cuenta getCuenta() {
		return cuenta;
	}
	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}
	public void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tarjeta other = (Tarjeta) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Tarjeta [id=" + id + ", pan=" + pan + ", marca=" + marca + ", tipo=" + tipo + "]";
	}
	@Override
	public int compareTo(Tarjeta o) {
		return this.id - o.id;
	}
 }
