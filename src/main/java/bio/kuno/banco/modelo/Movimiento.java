package bio.kuno.banco.modelo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@SuppressWarnings("serial")
@Entity
@Table(name = "movimientos")
public class Movimiento implements Serializable, Comparable<Movimiento> {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_movimiento")
	private int id;
	@ManyToOne
	@JoinColumn(name = "fk_extracto")
	private Extracto extracto;
	@ManyToOne
	@JoinColumn(name = "fk_tipo")
	private TipoMovimiento tipo;
	@ManyToOne
	@JoinColumn(name = "fk_tarjeta")
	private Tarjeta tarjeta;
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private double importe;
	private String proveedor;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Extracto getExtracto() {
		return extracto;
	}
	public void setExtracto(Extracto extracto) {
		this.extracto = extracto;
	}
	public TipoMovimiento getTipo() {
		return tipo;
	}
	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}
	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public double getImporte() {
		return importe;
	}
	public void setImporte(double importe) {
		this.importe = importe;
	}
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public String getFechaFormat() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.format(fecha);
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
		Movimiento other = (Movimiento) obj;
		return id == other.id;
	}
	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", tipo=" + tipo + ", fecha=" + fecha + ", importe=" + importe + "]";
	}
	@Override
	public int compareTo(Movimiento o) {
		return this.fecha.compareTo(o.fecha);
	}
}
