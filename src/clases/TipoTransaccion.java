package clases;

import java.io.Serializable;

public class TipoTransaccion implements Serializable{

    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TipoTransaccion(String descripcion, boolean esDebito) {
		super();
		this.setDescripcion(descripcion);
		this.setEsDebito(esDebito);
	}
	private String descripcion;
    private boolean esDebito;
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public boolean isEsDebito() {
		return esDebito;
	}
	public void setEsDebito(boolean esDebito) {
		this.esDebito = esDebito;
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n<=========================================================================>\n");
		if (esDebito)
			sb.append("  Tipo de transaccion: "+descripcion+"\n  Debito: Si");
		else
			sb.append("  Tipo de transaccion: "+descripcion+"\n  Debito: No");
		
		return sb.toString();
	}
}