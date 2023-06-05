package clases;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
//import java.time.Month;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;


public class CuentaCorriente extends Cuenta implements Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



public CuentaCorriente(BigInteger cBU, BigDecimal saldo, float importeDebOtroBanco,BigDecimal descubierto) {
		super(cBU, saldo, importeDebOtroBanco);
		super.setTipoCta("CC");
		this.setDescubierto(descubierto);
		this.setExtraccionesmensuales(0);
	}



	private BigDecimal descubierto;
	private int extraccionesmensuales;


	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Cuenta Corriente "+ super.getCBU());
		return sb.toString();
	}



	public BigDecimal getDescubierto() {
		return descubierto;
	}



	public void setDescubierto(BigDecimal descubierto) {
		this.descubierto = descubierto;
	}



	public int getExtraccionesmensuales() {
		return extraccionesmensuales;
	}



	public void setExtraccionesmensuales(int extraccionesmensuales) {
		this.extraccionesmensuales = extraccionesmensuales;
	}
	
	public void retirarDinero(BigDecimal monto, Cuenta auxCuenta, boolean BDLP, JButton btnRetirar,JTextField tfRetirar) {
		super.setRetirado(false);
		if (monto.compareTo(auxCuenta.getSaldo().add(((CuentaCorriente) auxCuenta).getDescubierto()))<=0) {
			JOptionPane.showMessageDialog(btnRetirar,"Se han retirado $"+monto);
			if (!BDLP) {
				monto=monto.add(new BigDecimal(30));
			}
				((CuentaCorriente)auxCuenta).setExtraccionesmensuales(((CuentaCorriente)auxCuenta).getExtraccionesmensuales()+1);
				if (((CuentaCorriente)auxCuenta).getExtraccionesmensuales()<=3) {
				}
				else {
					monto=monto.add(new BigDecimal(15));
				}
			
			Transaccion t;
			auxCuenta.agregar(t=new Transaccion(new Date(),monto));
			t.setTipoTransaccion(new TipoTransaccion("Retiro de dinero",true));
			auxCuenta.setSaldo(auxCuenta.getSaldo().subtract(monto));
			super.setRetirado(true);
		}
		else {
			JOptionPane.showMessageDialog(btnRetirar,"No se ha podido retirar el dinero, no dispones de ese saldo");
		}	
		tfRetirar.setText(null);
	}



//	@SuppressWarnings("deprecation")
//	@Override
//	public void mantenimiento() {
//		 try {
//		    	if (new Date().getMonth()!=getUltimoMes().getValue()) {
//		    		setUltimoMes(Month.of((new Date().getMonth())+1));
//		    		this.setSaldo(this.getSaldo().subtract(new BigDecimal("300")));
//		    		Transaccion t=new Transaccion(new Date(),(new BigDecimal("300")));
//		    		t.setTipoTransaccion(new TipoTransaccion("Mantenimiento", false));
//		    		getListaTransacciones().add(t);
//		    	}
//		    }
//		    catch( java.lang.NullPointerException e) {
//		    	setUltimoMes(Month.of((new Date().getMonth())+1));
//	    		this.setSaldo(this.getSaldo().subtract(new BigDecimal("300")));
//	    		Transaccion t=new Transaccion(new Date(),(new BigDecimal("300")));
//	    		t.setTipoTransaccion(new TipoTransaccion("Mantenimiento", false));
//	    		getListaTransacciones().add(t);		    	
//	    	}		
//	}
	
	
}