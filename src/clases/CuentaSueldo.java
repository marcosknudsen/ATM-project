package clases;


import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
//import java.time.Month;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CuentaSueldo extends CajaAhorro implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CuentaSueldo(BigInteger cBU, BigDecimal saldo, float importeDebOtroBanco, BigDecimal limiteDia,String CUIT) {
		super(cBU, saldo, importeDebOtroBanco, limiteDia);
		this.setCuitEmpleador(CUIT);
		this.setTipoCta("CS");
		// TODO Auto-generated constructor stub
	}

	private String cuitEmpleador;

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.append("Cuenta Sueldo"+super.getCBU());
		return sb.toString();
	}

	public String getCuitEmpleador() {
		return cuitEmpleador;
	}

	public void setCuitEmpleador(String cuitEmpleador) {
		this.cuitEmpleador = cuitEmpleador;
	}
	
	public void retirarDinero(BigDecimal monto, Cuenta auxCuenta, JButton btnRetirar,JTextField tfRetirar) {
		super.setRetirado(false);
		if ((monto.compareTo(new BigDecimal(0)))!=0){
			if (monto.compareTo(auxCuenta.getSaldo())<=0) {
				JOptionPane.showMessageDialog(btnRetirar,"Se ha retirado $"+monto);
				Transaccion t;
				auxCuenta.agregar(t=new Transaccion(new Date(),monto));
				t.setTipoTransaccion(new TipoTransaccion("Retiro de dinero",true));
				auxCuenta.setSaldo(auxCuenta.getSaldo().subtract(monto));
				super.setRetirado(true);
			}
			else {
				JOptionPane.showMessageDialog(btnRetirar,"No se ha podido retirar el dinero, no dispones de ese saldo");
			}
		}
		else {
			JOptionPane.showMessageDialog(btnRetirar,"No se pueden retirar $0");
		}
		tfRetirar.setText(null);
	}
//	@SuppressWarnings("deprecation")
//	@Override
//	public void mantenimiento() {
//	    try {
//	    	if (new Date().getMonth()!=getUltimoMes().getValue()) {
//	    		setUltimoMes(Month.of((new Date().getMonth())+1));
//	    	}
//	    }
//	    catch( java.lang.NullPointerException e) {
//	    	setUltimoMes(Month.of((new Date().getMonth())+1));
//    	}
//	}
}