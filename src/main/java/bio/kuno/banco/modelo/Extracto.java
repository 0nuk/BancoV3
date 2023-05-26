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

@SuppressWarnings("serial")
@Entity
@Table(name = "extractos")
public class Extracto implements Serializable, Comparable<Extracto> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_extracto")
	private int id;
	private int anyo;
	private int mes;
	@ManyToOne
	@JoinColumn(name = "fk_cuenta")
	private Cuenta cuenta;
	@OneToMany(mappedBy = "extracto")
	private Set<Movimiento> movimientos;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAnyo() {
		return anyo;
	}
	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	public int getMes() {
		return mes;
	}
	public void setMes(int mes) {
		this.mes = mes;
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
		Extracto other = (Extracto) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Extracto [id=" + id + ", anyo=" + anyo + ", mes=" + mes + "]";
	}
	@Override
	public int compareTo(Extracto o) {
		return this.id - o.id;
	}
}
