package clases;
import java.io.Serializable;
import java.math.BigInteger;

public class TarjetaATM implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public TarjetaATM(BigInteger id, int pin, boolean habilitada) {
		super();
		this.setId(id);
		this.setPIN(pin);
		this.setHabilitada(habilitada);
	}
	private BigInteger id;
    private int PIN;
    private boolean habilitada;
    private Usuario usuario;
    
    
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public int getPIN() {
		return PIN;
	}
	public void setPIN(int pIN) {
		PIN = pIN;
	}
	public boolean isHabilitada() {
		return habilitada;
	}
	public void setHabilitada(boolean habilitada) {
		this.habilitada = habilitada;
	}
	public void setUsuario(Usuario a) {
		this.usuario=a;
	}
	public Usuario getUsuario() {
		return this.usuario;
	}
	
	@Override
	public String toString() {
		return "TarjetaATM [id=" + id + ", PIN=" + PIN + ", habilitada=" + habilitada + "]";
	}
}