package principal;


import java.awt.Label;
import java.io.Serializable;
import java.math.*;
import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import clases.*;

import vista.Ventana;

public class ATM implements Serializable {

    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int ID;
    String Ubicacion;
    boolean modoMantenimiento;
    private ArrayList<Banco> listaBancos;
    private ArrayList<Billetero> listaBilleteros;
    private String admin;
	private String password;
	private Month ultimoMes;
	private boolean retirado;
    private boolean depositado;
	
    public ATM(int iD, String ubicacion, boolean modoMantenimiento) {
        super();
        this.setID(iD);
        this.setUbicacion(ubicacion);
        this.setModoMantenimiento(modoMantenimiento);
        this.setListaBancos(listaBancos = new ArrayList<Banco>());
        this.setListaBilleteros(listaBilleteros = new ArrayList<Billetero>());
    }
    
    
    
    
    public void validar(JTextField TFPin, TarjetaATM TarjetaInsertada, boolean check, JPanel PanelPin, JComboBox<Cuenta> comboBox, Label label, JPanel panelSeleccionarCuenta, JPanel panelBDLP, boolean BDLP, JPanel panelBancos, Cuenta auxCuenta, int tries, JPanel PanelIngreso, JLabel lblNombreBDLP, JLabel lblNombreBancos) {
    try {
				int count=0,PIN;
				PIN=Integer.parseInt(TFPin.getText());
				int auxPin=PIN;
				while (auxPin!=0) {
					auxPin=auxPin/10;
					count++;
				}
				if (count==4) {			//Cantidad correcta de digitos
					if (PIN==TarjetaInsertada.getPIN()) { //PIN Correcto
						check=true;
						TFPin.setText(null);
						PanelPin.setVisible(false);
						int contCuentas=0;
						try {
						for (Iterator<Cuenta> i = TarjetaInsertada.getUsuario().getListaCuentas().iterator(); i.hasNext();) {
					        Cuenta next = i.next();
					        contCuentas++;
					        next.toString();
					        comboBox.addItem(next);
							label.setText(TarjetaInsertada.getUsuario().getNombre()+" "+TarjetaInsertada.getUsuario().getApellido());

					    }
						}
						catch (java.lang.NullPointerException npex) {
							
						}
						PanelPin.setVisible(false);
						if (contCuentas>1) {
						panelSeleccionarCuenta.setVisible(true);
						}
						else {
/*Banco de la Plaza*/	if ((new BigInteger(TarjetaInsertada.getId().toString().substring(0,6)).compareTo(new BigInteger("300001"))>=0)&&(new BigInteger(TarjetaInsertada.getId().toString().substring(0,6)).compareTo(new BigInteger("500000"))<=0)) {
								panelBDLP.setVisible(true);
								panelBDLP.setOpaque(false);
								BDLP=true;
							}else {
							 	panelBancos.setVisible(true);
								panelBancos.setOpaque(false);
							 	BDLP=false;
							}
									try {
									Iterator<Cuenta> it=TarjetaInsertada.getUsuario().getListaCuentas().iterator();
									auxCuenta=it.next();
									}
									catch(java.util.NoSuchElementException nseex) {
										panelBancos.setVisible(false);
										panelBDLP.setVisible(false);
										PanelIngreso.setVisible(true);
										JOptionPane.showMessageDialog(comboBox,"ERROR: No se ha encontrado ninguna cuenta para el usuario");
									}
						}
					}
					else {//Pin Incorrecto
						JOptionPane.showMessageDialog(TFPin,"PIN Incorrecto");
						TFPin.setText(null);
						tries++;
					}
				}
				else { 					//Digitos de mas/menos
					JOptionPane.showMessageDialog(TFPin,"ERROR: Recuerde que debe ingresar 4 digitos");
					TFPin.setText(null);
					tries++;
			}
			}
			catch(java.lang.NumberFormatException ex) {
				JOptionPane.showMessageDialog(TFPin,"ERROR: Recuerde que debe ingresar 4 digitos");
				TFPin.setText(null);
			}
			if (!check&&tries>=3) { //Tres intentos fallidos (Retener)
				JOptionPane.showMessageDialog(TFPin,"Tarjeta retenida");
				TarjetaInsertada.setHabilitada(false);
				TFPin.setText(null);
				PanelPin.setVisible(false);
				PanelIngreso.setVisible(true);
				tries=0;
				
			}
			lblNombreBDLP.setText(TarjetaInsertada.getUsuario().getNombre()+" "+TarjetaInsertada.getUsuario().getApellido());
			lblNombreBancos.setText(TarjetaInsertada.getUsuario().getNombre()+" "+TarjetaInsertada.getUsuario().getApellido());
			
    }
    
    public void extraer(JTextField tfRetirar, Cuenta auxCuenta, JTextArea taTicketRetiro, JButton btnRetirar, JButton btnExitRetirar, JLabel lblRetirar, JButton btnTicketCRetiro, JButton btnTicketIRetiro, boolean BDLP, JButton btnRetirarDinero) {
    	try {
    		retirado=false;
			BigDecimal monto=new BigDecimal(tfRetirar.getText().toString());
			String gm = (String) tfRetirar.getText().toString();
			String gs = (String) auxCuenta.getSaldo().toString();
			float comparamonto = Float.parseFloat(gm);
			float comparasaldo = Float.parseFloat(gs);
			
			if (comparamonto <= comparasaldo && (comparamonto % 100 == 0)) {
				auxCuenta.retirarDinero(monto, auxCuenta, BDLP, btnRetirarDinero, tfRetirar);
				if (auxCuenta.isRetirado()) {
					taTicketRetiro.append("                    ** Banco de la Plaza **\n");
					taTicketRetiro.append("              Cajero 3421, Guemes y Alberti\n\n"); 
					taTicketRetiro.append("  Fecha: "+new Date().toString()+"\n");
					taTicketRetiro.append("\n  Extraccion:     $"+monto);
					taTicketRetiro.append("\n  Saldo anterior: $"+(new BigDecimal(auxCuenta.getSaldo().toString())).add(monto));
					taTicketRetiro.append("\n  Saldo actual:   $"+auxCuenta.getSaldo().toString());
					taTicketRetiro.append("\n\n                  ** Gracias por su visita **");
					tfRetirar.setVisible(false);
					btnRetirar.setVisible(false);
					btnExitRetirar.setVisible(false);
					lblRetirar.setVisible(false);
					btnTicketCRetiro.setVisible(true);
					btnTicketIRetiro.setVisible(true);
					taTicketRetiro.setVisible(true);
					retirado=true;
				}
				else {
					retirado=false;
				}
			}
			else {
				if (!(comparamonto%100==0)) {
					JOptionPane.showMessageDialog(btnRetirar,"ERROR: Debe retirar de a $100");
					tfRetirar.setText(null);
				}
				else {
					JOptionPane.showMessageDialog(btnRetirar,"ERROR: No dispone de saldo suficiente.");
					tfRetirar.setText(null);
				}
			}
			
		}
	catch(java.lang.NumberFormatException ex) {
		JOptionPane.showMessageDialog(btnRetirar,"ERROR: Recuerde que debe ingresar solo digitos");
		tfRetirar.setText(null);
	}
    }
    
    public void depositar(JTextField tfDepositarDinero, JButton btnDepositar, boolean BDLP, Cuenta auxCuenta, JComboBox<String> cbTipoBillete, JTextArea taTicket, JButton btnTicket, JLabel lblMontoDeposito, JLabel lblBillete, JButton btnExit) {
    	try {
    		depositado=false;
			BigDecimal monto=(new BigDecimal(tfDepositarDinero.getText()));
			float auxMonto=Float.parseFloat(tfDepositarDinero.getText());
			Transaccion t;
			if (auxMonto % 100 == 0) {
				float tb = Float.parseFloat((String) cbTipoBillete.getSelectedItem());
				if (tb == 100 && tb<=auxMonto && auxMonto%100==0) {	//Billetes de $100
					while (auxMonto>0) {
						auxCuenta.setSaldo(auxCuenta.getSaldo().add(new BigDecimal(100)));
						JOptionPane.showMessageDialog(btnDepositar,"Ingresar Billete");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						auxMonto-=100;
					}
					depositado=true;
				}
				else
					if (tb == 500 && tb<=auxMonto && auxMonto%500==0) {
						while (auxMonto>0) {
							auxCuenta.setSaldo(auxCuenta.getSaldo().add(new BigDecimal(500)));
							JOptionPane.showMessageDialog(btnDepositar,"Ingresar Billete");
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							
							auxMonto-=500;
						}
						depositado=true;
					}
					else
						if (tb == 1000 && tb<=auxMonto && auxMonto%1000==0) {
							while (auxMonto>0) {
								auxCuenta.setSaldo(auxCuenta.getSaldo().add(new BigDecimal(1000)));
								JOptionPane.showMessageDialog(btnDepositar,"Ingresar Billete");
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								auxMonto-=1000;
							}
							depositado=true;
						}
						else
							JOptionPane.showMessageDialog(btnDepositar,"Ingrese la cantidad justa de billetes");
				
				if (!BDLP) {
					if ((auxCuenta.getTipoCta()).compareTo("CA")==0) {
						monto.add(new BigDecimal(10));
					}
					else {
						if ((auxCuenta.getTipoCta().compareTo("CC"))==0) {
							monto.subtract(new BigDecimal(30));
						}
					}
				}
				if ((auxCuenta.getTipoCta().compareTo("CC"))==0) {
					if (((CuentaCorriente)auxCuenta).getExtraccionesmensuales()>=4) {
						monto.subtract(new BigDecimal(15));
					}
				}
			}
			else {
				JOptionPane.showMessageDialog(btnDepositar,"Este cajero solo acepta billetes de 100, 500 y 1000 pesos");
			}
			if (depositado) {
				JOptionPane.showMessageDialog(btnDepositar,"Se han depositado $"+monto);
				auxCuenta.agregar(t=new Transaccion(new Date(),monto));
				t.setTipoTransaccion(new TipoTransaccion("Deposito de dinero",false));
				tfDepositarDinero.setVisible(false);
				btnDepositar.setVisible(false);
				cbTipoBillete.setVisible(false);
				lblBillete.setVisible(false);
				lblMontoDeposito.setVisible(false);
				btnExit.setVisible(false);
				btnTicket.setVisible(true);
				taTicket.setVisible(true);
				taTicket.append("                    ** Banco de la Plaza **\n");
				taTicket.append("              Cajero 3421, Guemes y Alberti\n\n"); 
				taTicket.append("  Fecha: "+new Date().toString()+"\n");
				taTicket.append("\n  Deposito:     $"+monto);
				taTicket.append("\n  Saldo anterior: $"+(new BigDecimal(auxCuenta.getSaldo().toString())).subtract(monto));
				taTicket.append("\n  Saldo actual:   $"+auxCuenta.getSaldo().toString());
				taTicket.append("\n\n                  ** Gracias por su visita **");
			}
    	}
		catch (java.lang.NumberFormatException ex) {
			JOptionPane.showMessageDialog(btnDepositar,"ERROR: Recuerde que debe ingresar solo numeros");
			tfDepositarDinero.setText(null);
		}
    		
    	}
    
    public void recargar(Billetero billetero, int cantidad) {
    
    }
    
    public void descargar(Billetero billetero, int cantidad) {
    }
    
    public boolean isRetirado() {
		return retirado;
	}




	public boolean isDepositado() {
		return depositado;
	}
    
    public void transferir(JTextField tfCBUTransferencia, JTextField tfMontoTransferencia, Cuenta auxCuenta, JButton btnTransferir) {
    	try {
			boolean encontrado = false;
			BigInteger CBUTransferencia= new BigInteger(tfCBUTransferencia.getText());
			BigDecimal monto=new BigDecimal(tfMontoTransferencia.getText());
			for (Iterator<Banco> i=this.getBancos().iterator();i.hasNext();) {
				Banco itBanco=i.next();
				for(Iterator<Usuario> it=itBanco.getUsuarios();it.hasNext();) {
					Usuario itUsuario=it.next();
					for (Iterator<Cuenta> ite= itUsuario.getListaCuentas().iterator();ite.hasNext();){
						Cuenta itCuenta=ite.next();
						if ((itCuenta.getCBU().compareTo(CBUTransferencia))==0) {
							encontrado=true;
								if (monto.compareTo(auxCuenta.getSaldo())<=0) {
									JOptionPane.showMessageDialog(btnTransferir,"Se ha transferido $"+monto+" a "+CBUTransferencia);
									tfCBUTransferencia.setText(null);
									tfMontoTransferencia.setText(null);
									auxCuenta.setSaldo(auxCuenta.getSaldo().subtract(monto));
									Transaccion t1;
									auxCuenta.agregar(t1=new Transaccion(new Date(),monto));
									Transaccion t2;
									itCuenta.agregar(t2=new Transaccion(new Date(),monto));
									itCuenta.setSaldo(itCuenta.getSaldo().add(monto));
									t1.setTipoTransaccion(new TipoTransaccion("Transferencia enviada",false));
									t2.setTipoTransaccion(new TipoTransaccion("Transferencia recibida",true));
								
								}
								else {
									JOptionPane.showMessageDialog(btnTransferir,"No se ha podido transferir, no dispones de ese saldo");
									tfCBUTransferencia.setText(null);
									tfMontoTransferencia.setText(null);
								}
						}
					}
				}
		}
			if (!encontrado) {
				JOptionPane.showMessageDialog(btnTransferir,"No se ha encontrado una cuenta con el CBU ingresado.");
				tfCBUTransferencia.setText(null);
				tfMontoTransferencia.setText(null);
			}
		}
		catch (java.lang.NumberFormatException ex) {
			JOptionPane.showMessageDialog(btnTransferir,"ERROR: Recuerde que debe ingresar solo digitos");
			tfCBUTransferencia.setText(null);
			tfMontoTransferencia.setText(null);
		}
    }
    
    void mostrar(ATM cajero) {
        Ventana ventana= new Ventana(cajero);
        ventana.setVisible(true);
    }
    
    public void agregar(Banco a) {
        listaBancos.add(a);
    }
    
    public List<Banco> getBancos(){
    	return listaBancos;
    }
    
    public void agregar(Billetero a) {
    	listaBilleteros.add(a);
    }
    public List<Billetero> getBilleteros() {
    	return listaBilleteros;
    }

	public int getID() {
		return ID;
	}




	public void setID(int iD) {
		ID = iD;
	}




	public String getUbicacion() {
		return Ubicacion;
	}




	public void setUbicacion(String ubicacion) {
		Ubicacion = ubicacion;
	}




	public boolean isModoMantenimiento() {
		return modoMantenimiento;
	}




	public void setModoMantenimiento(boolean modoMantenimiento) {
		this.modoMantenimiento = modoMantenimiento;
	}




	public ArrayList<Banco> getListaBancos() {
		return listaBancos;
	}




	public void setListaBancos(ArrayList<Banco> listaBancos) {
		this.listaBancos = listaBancos;
	}




	public ArrayList<Billetero> getListaBilleteros() {
		return listaBilleteros;
	}




	public void setListaBilleteros(ArrayList<Billetero> listaBilleteros) {
		this.listaBilleteros = listaBilleteros;
	}




	public String getAdmin() {
		return admin;
	}




	public void setAdmin(String admin) {
		this.admin = admin;
	}




	public String getPassword() {
		return password;
	}




	public void setPassword(String password) {
		this.password = password;
	}
	

	
} 