package bio.kuno.banco.modelo;

import java.io.Serializable;
import java.text.Collator;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@SuppressWarnings("serial")
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable, Comparable<Cliente> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	@Expose
	private int id;
	@Expose
	private String nombre;
	@Expose
	private String apellido1;
	@Expose
	private String apellido2;
	@Expose
	private String nif;
	@Enumerated(EnumType.STRING)
	@Expose
	private Sexo sexo;
	@Expose
	private String municipio;
	@Expose
	private String provincia;
	@OneToMany(mappedBy = "cliente")
	private Set<Cuenta> cuentas;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public Set<Cuenta> getCuentas() {
		return cuentas;
	}
	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	public String getApellidos() {
		return apellido1 + " " + apellido2;
	}
	public String getNombreCompleto() {
		return nombre + " " + getApellidos();
	}
	public Sexo getGenero() {
		return sexo;
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
		Cliente other = (Cliente) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Cliente [ID " + id + ": " + apellido1 + " " + apellido2 +  " " + nombre
				+ "]";
	}
	@Override
	public int compareTo(Cliente o) {
		if(this.equals(o)) return 0;
		Collator col = Collator.getInstance(new Locale("es"));
		return col.compare(this.apellido1 + this.apellido2 + this.id, o.apellido1 + o.apellido2 + o.id);
	}
	
}
