package clases;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;

public class Tarifa implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Tarifa(ArrayList<BigDecimal> listaTarifas) {
		super();
		this.setListaTarifas(listaTarifas);
	}

	private ArrayList<BigDecimal> listaTarifas;

	public ArrayList<BigDecimal> getListaTarifas() {
		return listaTarifas;
	}

	public void setListaTarifas(ArrayList<BigDecimal> listaTarifas) {
		this.listaTarifas = listaTarifas;
	}
    
}