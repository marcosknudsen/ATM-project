package clases;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Month;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class CajaAhorro extends Cuenta implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CajaAhorro(BigInteger cBU, BigDecimal saldo, float importeDebOtroBanco,BigDecimal limiteDia) {
		super(cBU, saldo, importeDebOtroBanco);
		super.setTipoCta("CA");
		this.setLimiteExtraccionDia(limiteDia);
		this.setDineroRetiradoHoy(new BigDecimal("0"));
	}
	private BigDecimal limiteExtraccionDia;
    private float tasaInteres;
    private BigDecimal DineroRetiradoHoy;
    private Month ultimomes;
    
    @Override
	public String toString() {
    	StringBuilder sb = new StringBuilder(super.toString());
    	sb.append("Caja de Ahorro: "+super.getCBU());
		return sb.toString();
	}
	public BigDecimal getLimiteExtraccionDia() {
		return limiteExtraccionDia;
	}
	public void setLimiteExtraccionDia(BigDecimal limiteExtraccionDia) {
		this.limiteExtraccionDia = limiteExtraccionDia;
	}
	public float getTasaInteres() {
		return tasaInteres;
	}
	public void setTasaInteres(float tasaInteres) {
		this.tasaInteres = tasaInteres;
	}
	public BigDecimal getDineroRetiradoHoy() {
		return DineroRetiradoHoy;
	}
	public void setDineroRetiradoHoy(BigDecimal dineroRetiradoHoy) {
		DineroRetiradoHoy = dineroRetiradoHoy;
	}
   
	public void retirarDinero(BigDecimal monto, Cuenta auxCuenta, boolean BDLP, JButton btnRetirar, JTextField tfRetirar) {
		super.setRetirado(false);
		if (monto.compareTo(new BigDecimal("0"))!=0){
			if (monto.compareTo(auxCuenta.getSaldo())<=0) {
				if ((monto.add(((CajaAhorro) auxCuenta).getDineroRetiradoHoy())).compareTo(((CajaAhorro) auxCuenta).getLimiteExtraccionDia())<=0){ // retiradoHoy+ARetirar<LimiteDiario
					JOptionPane.showMessageDialog(btnRetirar,"Se han retirado $"+monto);
					if (!BDLP) {
						monto=monto.add(new BigDecimal(10));
					}
					auxCuenta.setSaldo(auxCuenta.getSaldo().subtract(monto));
					Transaccion t;
					auxCuenta.agregar(t=new Transaccion(new Date(),monto));
					t.setTipoTransaccion(new TipoTransaccion("Retiro de dinero",true));
					((CajaAhorro) auxCuenta).setDineroRetiradoHoy(((CajaAhorro)auxCuenta).getDineroRetiradoHoy().add(monto));
					tfRetirar.setText(null);
					super.setRetirado(true);
				}
				else {
					JOptionPane.showMessageDialog(btnRetirar,"No se ha podido retirar el dinero, esto excede su limite de extraccion diario");
					tfRetirar.setText(null);
				}
			}
			else {
				JOptionPane.showMessageDialog(btnRetirar,"No se ha podido retirar el dinero, no dispone de esa cantidad de saldo");
				tfRetirar.setText(null);
			}	
		}
		else {
			JOptionPane.showMessageDialog(btnRetirar,"No puede retirar $0");
		}
		tfRetirar.setText(null);
	}
//	@SuppressWarnings("deprecation")
//	@Override
//	public void mantenimiento() {
//		    try {
//		    	if (new Date().getMonth()!=ultimomes.getValue()) {
//		    		ultimomes=Month.of((new Date().getMonth())+1);
//		    		this.setSaldo(this.getSaldo().subtract(new BigDecimal("150")));
//		    		Transaccion t=new Transaccion(new Date(),(new BigDecimal("150")));
//		    		t.setTipoTransaccion(new TipoTransaccion("Mantenimiento", false));
//		    		getListaTransacciones().add(t);
//		    	}
//		    }
//		    catch( java.lang.NullPointerException e) {
//		    	ultimomes=Month.of((new Date().getMonth())+1);
//	    		this.setSaldo(this.getSaldo().subtract(new BigDecimal("150")));
//	    		Transaccion t=new Transaccion(new Date(),(new BigDecimal("150")));
//	    		t.setTipoTransaccion(new TipoTransaccion("Mantenimiento", false));
//	    		getListaTransacciones().add(t);		    	
//	    	}
//	}
	

}
