package clases;
import java.util.ArrayList;
//import java.util.Date;
//import java.util.Iterator;
//import java.util.TimerTask;
//import java.io.IOException;
import java.io.Serializable;
//import java.math.BigDecimal;

public class Usuario implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Usuario(int iD, String apellido, String nombre) {
        super();
        this.setID(iD);
        this.setApellido(apellido);
        this.setNombre(nombre);
        this.setListaCuentas(listaCuentas = new ArrayList<>());
    }
	
	
    int ID;
    String apellido;
    String nombre;
    ArrayList<Cuenta> listaCuentas;
    
    public void agregar(Cuenta a) {
        listaCuentas.add(a);
    }
    
    public void eliminar(Cuenta a) {
    	listaCuentas.remove(a);
    }

	@Override
	public String toString() {
		return nombre + " " + apellido + " ID= " + ID;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Cuenta> getListaCuentas() {
		return listaCuentas;
	}
	
	public void setListaCuentas(ArrayList<Cuenta> listaCuentas) {
		this.listaCuentas = listaCuentas;
	}
	

//	@Override
//	public void run() {
//		Iterator<Cuenta> it = listaCuentas.iterator();
//		while (it.hasNext()) {
//			Cuenta cuenta = it.next();
//			cuenta.setSaldo(cuenta.getSaldo().subtract(new BigDecimal("300")));
//			Transaccion t=new Transaccion(new Date(),(new BigDecimal("300")));
//    		t.setTipoTransaccion(new TipoTransaccion("Gasto de mantenimiento", false));
//    		cuenta.getListaTransacciones().add(t);
//		}
//		
//	}

	
}