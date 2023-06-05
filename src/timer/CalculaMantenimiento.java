package timer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.TimerTask;

import clases.Banco;
import clases.Cuenta;
import clases.TipoTransaccion;
import clases.Transaccion;
import clases.Usuario;
import principal.ATM;
import serializables.SerializarCajero;


public class CalculaMantenimiento extends TimerTask implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ATM cajero;

	public CalculaMantenimiento(ATM cajero){
		this.cajero = cajero;
	}
	
	
	
	@Override
	public void run() {
		Banco banco = null;
		Iterator<Banco> itt = cajero.getListaBancos().iterator();
		while ( (banco=itt.next()).getNombre().compareTo("De la Plaza")!=0 ){
		}
		Iterator<Usuario> itu = banco.getListaUsuarios().iterator();
		while (itu.hasNext()) {
			Usuario usuario = itu.next();
			for (Iterator<Cuenta> itc = usuario.getListaCuentas().iterator(); itc.hasNext();) {
				Cuenta cuenta = itc.next();
				if (cuenta.getClass().getSimpleName().compareTo("CuentaCorriente")==0) {
					cuenta.setSaldo(cuenta.getSaldo().subtract(new BigDecimal("300")));
					Transaccion t=new Transaccion(new Date(),(new BigDecimal("300")));
					t.setTipoTransaccion(new TipoTransaccion("Gasto de mantenimiento", false));
					cuenta.getListaTransacciones().add(t);
				} else
					if (cuenta.getClass().getSimpleName().compareTo("CajaAhorro")==0) {
						cuenta.setSaldo(cuenta.getSaldo().subtract(new BigDecimal("150")));
						Transaccion t=new Transaccion(new Date(),(new BigDecimal("150")));
						t.setTipoTransaccion(new TipoTransaccion("Gasto de mantenimiento", false));
						cuenta.getListaTransacciones().add(t);
					}
				
			}
		}
		SerializarCajero.persistirCajero(cajero);
	
	}
}