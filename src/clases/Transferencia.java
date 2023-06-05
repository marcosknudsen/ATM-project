package clases;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class Transferencia extends Transaccion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BigInteger CBUCuentaATransferir;
    
    public Transferencia(Date fecha, BigDecimal valor,BigInteger CBUCuentaATransferir) {
		super(fecha, valor);
		this.setCBUCuentaATransferir(CBUCuentaATransferir);
    }

	void mostrar() {
        
    }

	public BigInteger getCBUCuentaATransferir() {
		return CBUCuentaATransferir;
	}

	public void setCBUCuentaATransferir(BigInteger cBUCuentaATransferir) {
		CBUCuentaATransferir = cBUCuentaATransferir;
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("CBU a transferir: "+getCBUCuentaATransferir());
		
		return sb.toString();
	}
}