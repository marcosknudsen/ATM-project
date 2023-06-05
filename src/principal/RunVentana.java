package principal;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import clases.*;
import serializables.SerializarCajero;
import timer.CalculaMantenimiento;


public class RunVentana implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        Billetero bil1,bil5,bil10;
		Banco b1;
        Banco b2;
        Banco b3;
        Usuario u1;
        Usuario u2;
        Usuario u3;
        TarjetaATM t1;
        TarjetaATM t2;
        TarjetaATM t3;
        Timer timer = new Timer();
		
        if (args[0].equals("serializar")) {
        	ATM cajero= new ATM(3421,"Guemes y Alberti",false);
        	cajero.setAdmin("admin");
        	cajero.setPassword("admin");
        	cajero.agregar(b3 = new Banco("Provincia",BigInteger.valueOf(100000) ,BigInteger.valueOf(300000)));
        	cajero.agregar(b1=new Banco("De la Plaza", BigInteger.valueOf(300001), BigInteger.valueOf(500000)));
        	cajero.agregar(bil1 =new Billetero(new BigDecimal("100"),6000));
        	cajero.agregar(bil5 =new Billetero(new BigDecimal("500"),6000));
        	cajero.agregar(bil10=new Billetero(new BigDecimal("1000"),6900));
        	b1.agregar(u1=new Usuario(142341412,"Knudsen","Marcos"));
			u1.agregar(new CuentaCorriente(new BigInteger("1234564563453254363843"),new BigDecimal("100000"),(float)0.2,new BigDecimal("500")));
			u1.agregar(new CajaAhorro(new BigInteger("646352723725725726462"),new BigDecimal("100000"),(float)0.2,new BigDecimal("500")));
			b1.agregar(u2=new Usuario(415151251,"Salustra","Axel"));
			u2.agregar(new CuentaCorriente(new BigInteger("146146464516461416161"),new BigDecimal("100000"),(float)0.2,new BigDecimal("500")));
			u2.agregar(new CuentaCorriente(new BigInteger("41414422"),new BigDecimal("300"),(float)0.2,new BigDecimal("500")));
			b1.agregar(t1=new TarjetaATM(new BigInteger("3200000000000000"),2000,true));
			b1.agregar(t3=new TarjetaATM(new BigInteger("3200000000000002"),2000,true));
			b1.agregar(t2=new TarjetaATM(new BigInteger("3200000000000001"),2000,true));
			t3.setUsuario(u1);
			t1.setUsuario(u1);
			t2.setUsuario(u2); 
			cajero.agregar(b2=new Banco("Frances",BigInteger.valueOf(500001) ,BigInteger.valueOf(700000)));
			b2.agregar(t3=new TarjetaATM(new BigInteger("5200000000000000"),2000,true));
			b2.agregar(u3=new Usuario(123456789,"Marcelo","Fernandez"));
			t3.setUsuario(u3);
			u3.agregar(new CuentaCorriente(new BigInteger("2234564562453254323441"),new BigDecimal("400"),(float)0.2,new BigDecimal("300")));
			cajero.agregar(new Banco("Galicia",BigInteger.valueOf(700001) ,BigInteger.valueOf(999999))); 

			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.YEAR, 2020);
			cal.set(Calendar.MONTH, 1);
			cal.set(Calendar.DATE, 9);
			cal.set(Calendar.HOUR, 8);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);
			Date firstTimeExecution = cal.getTime();
			CalculaMantenimiento task = new CalculaMantenimiento(cajero);
			long per = Long.parseLong("2628002880");
			timer.scheduleAtFixedRate(task, firstTimeExecution, per);
			SerializarCajero.persistirCajero(cajero);
        }
        if (args[0].equals("deserializar")) {
			ATM cajero = SerializarCajero.instanciarCajero();
        	cajero.mostrar(cajero);
        }
				
        
      
    }

}
