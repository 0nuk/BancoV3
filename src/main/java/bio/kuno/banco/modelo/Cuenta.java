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
@Table(name = "cuentas")
public class Cuenta implements Serializable, Comparable<Cuenta>{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cuenta")
	@Expose
	private int id;
	@Expose
	private String numero;
	@ManyToOne
	@JoinColumn(name = "fk_cliente")
	private Cliente cliente;
	@OneToMany(mappedBy = "cuenta")
	private Set<Extracto> extractos;
	@OneToMany(mappedBy = "cuenta")
	private Set<Tarjeta> tarjetas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public Set<Extracto> getExtractos() {
		return extractos;
	}
	public void setExtractos(Set<Extracto> extractos) {
		this.extractos = extractos;
	}
	public Set<Tarjeta> getTarjetas() {
		return tarjetas;
	}
	public void setTarjetas(Set<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
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
		Cuenta other = (Cuenta) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < numero.length(); i++) {
			if(i%4 == 0) {
				sb.append(" ");
			}
			sb.append(numero.charAt(i));
		}
		return sb.toString();
	}
	@Override
	public int compareTo(Cuenta o) {
		return this.id - o.id;
	}	
}
