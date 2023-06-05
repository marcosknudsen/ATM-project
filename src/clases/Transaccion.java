package clases;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.text.DecimalFormat;


public class Transaccion implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Transaccion(Date fecha, BigDecimal valor) {
		super();
		this.setFecha(fecha);
		this.setValor(valor);
	}

	private Date fecha;
    private BigDecimal valor;
    private TipoTransaccion tipoTransaccion;
    
    DecimalFormat df=new DecimalFormat("0.00");
    
    void mostrar() {
        
    }

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public TipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(TipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(tipoTransaccion);
		sb.append("  Monto: "+df.format(valor)+"\n");
		sb.append("  Fecha: "+fecha);
		
		
		return sb.toString();
	}
    
}