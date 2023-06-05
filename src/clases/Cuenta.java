package clases;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
//import java.time.Month;
import java.util.ArrayList;
import java.util.List;
//import java.util.TimerTask;

import javax.swing.JButton;
import javax.swing.JTextField;

public abstract class Cuenta implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cuenta(BigInteger cBU, BigDecimal saldo, float importeDebOtroBanco) {
		super();
		this.setCBU(cBU);
		this.setSaldo(saldo);
		this.setListaTransacciones(listaTransacciones=new ArrayList<Transaccion>());
		this.setImporteDebOtroBanco(importeDebOtroBanco);
	}

	private BigInteger CBU;
    private BigDecimal saldo;
    private float importeDebOtroBanco;
    private String tipoCta;
    private List<Transaccion> listaTransacciones;
    private boolean retirado;
//    private Month ultimoMes;
    
    public BigDecimal getSaldo() {
        return this.saldo;
        
    }
    
    public String getTipoCta() {
        return this.tipoCta;
    }

	public BigInteger getCBU() {
		return CBU;
	}

	public void setCBU(BigInteger cBU) {
		CBU = cBU;
	}

	public float getImporteDebOtroBanco() {
		return importeDebOtroBanco;
	}

	public void setImporteDebOtroBanco(float importeDebOtroBanco) {
		this.importeDebOtroBanco = importeDebOtroBanco;
	}

	public List<Transaccion> getListaTransacciones() {
		return listaTransacciones;
	}
	public void agregar(Transaccion a) {
		listaTransacciones.add(a);
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public void setTipoCta(String tipoCta) {
		this.tipoCta = tipoCta;
	}

	public abstract void retirarDinero(BigDecimal monto, Cuenta auxCuenta, boolean BDLP, JButton btnRetirar, JTextField tfRetirar);

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("CBU: "+getCBU()+" ");
		sb.append("$: "+getSaldo()+" ");
		sb.append("Importe debito de otro banco: "+importeDebOtroBanco);
		return sb.toString();
	}

	public void setListaTransacciones(List<Transaccion> listaTransacciones) {
		this.listaTransacciones = listaTransacciones;
	}

	public boolean isRetirado() {
		return retirado;
	}

	public void setRetirado(boolean retirado) {
		this.retirado = retirado;
	}
    
//	public abstract void mantenimiento();
//
//	public Month getUltimoMes() {
//		return ultimoMes;
//	}
//
//	public void setUltimoMes(Month ultimoMes) {
//		this.ultimoMes = ultimoMes;
//	}
}