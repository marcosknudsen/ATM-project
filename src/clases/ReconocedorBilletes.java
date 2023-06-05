package clases;
import java.io.Serializable;
import java.math.*;
import java.util.*;

public class ReconocedorBilletes implements Serializable {
    private int billetesPermitidos;
    
    public ReconocedorBilletes(int billetesPermitidos) {
        super();
        this.billetesPermitidos = billetesPermitidos;
    }
    
    public int ReconoceBillete() {
        int nr = (int) (Math.random()*10+1);
        return nr;
    }

	public int getBilletesPermitidos() {
		return billetesPermitidos;
	}

	public void setBilletesPermitidos(int billetesPermitidos) {
		this.billetesPermitidos = billetesPermitidos;
	}
    
    
    
}