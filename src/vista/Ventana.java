package vista;

import javax.swing.*;

import clases.*;
import principal.ATM;
import serializables.SerializarCajero;

import java.awt.event.ActionListener;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.awt.event.ActionEvent;
import java.awt.*;

public class Ventana extends JFrame implements Serializable  {

	
	private JTextField TFTarjeta;
	private JTextField TFPin;
	private static final long serialVersionUID = -2004716158443861219L;
	BigInteger ID;
	private JPanel JContentPane = null;
	private TarjetaATM TarjetaInsertada=null;
	private int tries=0;
	private boolean check=false;
	private JTextField TFPinActual;
	private JTextField TFPinNuevo;
	private JTextField TFPinConfirmar;
	private boolean BDLP;
	public Banco auxBanco;
	TarjetaATM auxTarjeta;
	Cuenta auxCuenta;
	private JTextField tfMontoDeposito;
	private JTextField tfRetirar;
	private JTextField tfCBUTransferencia;
	private JTextField tfMontoTransferencia;
	private JTextField tfAgregarTarjetaPin;
	private JTextField tfAgregarTarjetaDNI;
	private JTextField tfAgregarUsuarioNombre;
	private JTextField tfAgregarUsuarioApellido;
	private JTextField tfAgregarUsuarioID;
	private JTextField tfEliminarUsuarioID;
	private JTextField tfModificarUsuarioID;
	private JTextField tfModificarUsuarioModificarNombre;
	private JTextField tfModificarUsuarioModificarApellido;
	private Usuario usuarioAModificar;

	public Ventana(ATM cajero) {
		setResizable(false);
		Toolkit frame = Toolkit.getDefaultToolkit();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("Banco de la Plaza");
		Dimension tamanoPantalla=frame.getScreenSize();
		int altoPantalla=tamanoPantalla.height;
		int anchoPantalla=tamanoPantalla.width;
		setBounds(anchoPantalla/5, altoPantalla/5, 782, 517); 
		
		
		Image myicon = frame.getImage("src/imagenes/BDP-icon.png");
		setIconImage(myicon);
		setContentPane(getJContentPane("BDP-background.jpg"));
								
		
								JPanel panelGestionarUsuario = new JPanel();
								panelGestionarUsuario.setVisible(false);
								
								JPanel panelAgregarUsuario = new JPanel();
								panelAgregarUsuario.setOpaque(false);
								panelAgregarUsuario.setVisible(false);
								
								JPanel panelEliminarUsuario = new JPanel();
								panelEliminarUsuario.setOpaque(false);
								panelEliminarUsuario.setVisible(false);
								
								JPanel panelModificarUsuarioModificar = new JPanel();
								panelModificarUsuarioModificar.setOpaque(false);
								panelModificarUsuarioModificar.setVisible(false);
								panelModificarUsuarioModificar.setBounds(0, 0, 776, 488);
								getContentPane().add(panelModificarUsuarioModificar);
								panelModificarUsuarioModificar.setLayout(null);
								
								JLabel lblModificarUsuarioUsuario = new JLabel("");
								lblModificarUsuarioUsuario.setHorizontalAlignment(SwingConstants.CENTER);
								lblModificarUsuarioUsuario.setBounds(224, 152, 330, 14);
								panelModificarUsuarioModificar.add(lblModificarUsuarioUsuario);
								
								JButton btnModificarUsuarioNombre = new JButton("Modificar Nombre y Apellido");
								btnModificarUsuarioNombre.setBounds(262, 194, 244, 23);
								panelModificarUsuarioModificar.add(btnModificarUsuarioNombre);
								
								JButton btnModificarUsuarioEliminarCuentas = new JButton("Eliminar Cuentas del Usuario");
								btnModificarUsuarioEliminarCuentas.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										for (Iterator<Cuenta> i=usuarioAModificar.getListaCuentas().iterator();i.hasNext();) {
										}
										usuarioAModificar.getListaCuentas().clear();
										serializables.SerializarCajero.persistirCajero(cajero);
									}
								});
								btnModificarUsuarioEliminarCuentas.setBounds(262, 249, 244, 23);
								panelModificarUsuarioModificar.add(btnModificarUsuarioEliminarCuentas);
								
								JButton btnExitModificarUsuario = new JButton("Salir");
								
								btnExitModificarUsuario.setBounds(677, 454, 89, 23);
								panelModificarUsuarioModificar.add(btnExitModificarUsuario);
								
								JPanel panelModificarUsuarioModificarNombre = new JPanel();
								panelModificarUsuarioModificarNombre.setOpaque(false);
								panelModificarUsuarioModificarNombre.setVisible(false);
								panelModificarUsuarioModificarNombre.setBounds(0, 0, 776, 488);
								getContentPane().add(panelModificarUsuarioModificarNombre);
								panelModificarUsuarioModificarNombre.setLayout(null);
								
								tfModificarUsuarioModificarNombre = new JTextField();
								tfModificarUsuarioModificarNombre.setBounds(179, 203, 161, 20);
								panelModificarUsuarioModificarNombre.add(tfModificarUsuarioModificarNombre);
								tfModificarUsuarioModificarNombre.setColumns(10);
								
								tfModificarUsuarioModificarApellido = new JTextField();
								tfModificarUsuarioModificarApellido.setBounds(407, 203, 181, 20);
								panelModificarUsuarioModificarNombre.add(tfModificarUsuarioModificarApellido);
								tfModificarUsuarioModificarApellido.setColumns(10);
								
								JButton btnModificarUsuarioModificar = new JButton("Modificar");
								btnModificarUsuarioModificar.setBounds(338, 264, 89, 23);
								panelModificarUsuarioModificarNombre.add(btnModificarUsuarioModificar);
								
								JLabel lbllModificarUsuarioModificarNombre = new JLabel("Nombre");
								lbllModificarUsuarioModificarNombre.setHorizontalAlignment(SwingConstants.CENTER);
								lbllModificarUsuarioModificarNombre.setBounds(179, 180, 161, 14);
								panelModificarUsuarioModificarNombre.add(lbllModificarUsuarioModificarNombre);
								
								JLabel lblModificarUsuarioModificarApellido = new JLabel("Apellido");
								lblModificarUsuarioModificarApellido.setHorizontalAlignment(SwingConstants.CENTER);
								lblModificarUsuarioModificarApellido.setBounds(407, 180, 181, 14);
								panelModificarUsuarioModificarNombre.add(lblModificarUsuarioModificarApellido);
								
								JButton btnExitModificarUsuarioModificar = new JButton("Salir");
								btnExitModificarUsuarioModificar.setBounds(677, 454, 89, 23);
								panelModificarUsuarioModificarNombre.add(btnExitModificarUsuarioModificar);
								
								JPanel panelModificarUsuario = new JPanel();
								panelModificarUsuario.setOpaque(false);
								panelModificarUsuario.setVisible(false);
								panelModificarUsuario.setBounds(0, 0, 776, 488);
								getContentPane().add(panelModificarUsuario);
								panelModificarUsuario.setLayout(null);
								
								tfModificarUsuarioID = new JTextField();
								tfModificarUsuarioID.setBounds(254, 224, 166, 20);
								panelModificarUsuario.add(tfModificarUsuarioID);
								tfModificarUsuarioID.setColumns(10);
								
								JButton btnModificarUsuario1 = new JButton("Modificar Usuario");
								btnModificarUsuario1.setBounds(438, 223, 153, 23);
								panelModificarUsuario.add(btnModificarUsuario1);
								
								JButton btnExitModificarUsuario1 = new JButton("Salir");
								btnExitModificarUsuario1.setBounds(677, 454, 89, 23);
								panelModificarUsuario.add(btnExitModificarUsuario1);
								panelEliminarUsuario.setBounds(0, 0, 776, 488);
								getContentPane().add(panelEliminarUsuario);
								panelEliminarUsuario.setLayout(null);
								
								tfEliminarUsuarioID = new JTextField();
								tfEliminarUsuarioID.setBounds(333, 232, 125, 20);
								panelEliminarUsuario.add(tfEliminarUsuarioID);
								tfEliminarUsuarioID.setColumns(10);
								
								JLabel lbEliminarUsuarioID = new JLabel("Ingrese ID");
								lbEliminarUsuarioID.setHorizontalAlignment(SwingConstants.CENTER);
								lbEliminarUsuarioID.setBounds(333, 207, 125, 14);
								panelEliminarUsuario.add(lbEliminarUsuarioID);
								
								JButton btnEliminarUsuarioEliminar = new JButton("Eliminar Usuario");
								btnEliminarUsuarioEliminar.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										boolean eliminado=false;
										Iterator<Banco> itb=cajero.getListaBancos().iterator();
										Banco banco;
										while( (  (banco=itb.next()).getNombre().compareTo("De la Plaza"))!=0 ) {
										}
										try {
											for (int i=1;i<=banco.getListaUsuarios().size();i++) {
												if (banco.getListaUsuarios().get(i-1).getID()==Integer.valueOf(tfEliminarUsuarioID.getText())) {
													JOptionPane.showMessageDialog(btnEliminarUsuarioEliminar,"Se ha eliminado el usuario: "+banco.getListaUsuarios().get(i-1).getID());
													banco.getListaUsuarios().remove(i-1);
													serializables.SerializarCajero.persistirCajero(cajero);
													eliminado=true;
												}
											}
											if (!eliminado) {
												JOptionPane.showMessageDialog(btnEliminarUsuarioEliminar,"No se ha encontrado al usuario");
												tfEliminarUsuarioID.setText(null);
											}
										}
										catch(java.lang.NumberFormatException nfex) {
											JOptionPane.showMessageDialog(btnEliminarUsuarioEliminar,"Error: Recuerde que debe ingresar el ID");
											tfEliminarUsuarioID.setText(null);
										}
									}
								});
								btnEliminarUsuarioEliminar.setBounds(483, 231, 139, 23);
								panelEliminarUsuario.add(btnEliminarUsuarioEliminar);
								
								JButton btnEliminarUsuarioExit = new JButton("Salir");
								btnEliminarUsuarioExit.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panelEliminarUsuario.setVisible(false);
										panelGestionarUsuario.setVisible(true);
									}
								});
								btnEliminarUsuarioExit.setBounds(677, 454, 89, 23);
								panelEliminarUsuario.add(btnEliminarUsuarioExit);
								
								
								panelAgregarUsuario.setBounds(0, 0, 776, 488);
								getContentPane().add(panelAgregarUsuario);
								panelAgregarUsuario.setLayout(null);
								
								JButton btnExitAgregarUsuario = new JButton("Salir");
								btnExitAgregarUsuario.setBounds(677, 454, 89, 23);
								panelAgregarUsuario.add(btnExitAgregarUsuario);
								
								tfAgregarUsuarioNombre = new JTextField();
								tfAgregarUsuarioNombre.setBounds(117, 252, 86, 20);
								panelAgregarUsuario.add(tfAgregarUsuarioNombre);
								tfAgregarUsuarioNombre.setColumns(10);
								
								tfAgregarUsuarioApellido = new JTextField();
								tfAgregarUsuarioApellido.setBounds(274, 252, 86, 20);
								panelAgregarUsuario.add(tfAgregarUsuarioApellido);
								tfAgregarUsuarioApellido.setColumns(10);
								
								tfAgregarUsuarioID = new JTextField();
								tfAgregarUsuarioID.setBounds(430, 252, 86, 20);
								panelAgregarUsuario.add(tfAgregarUsuarioID);
								tfAgregarUsuarioID.setColumns(10);
								
								JButton btnAgregarUsuarioAgregar = new JButton("Agregar usuario");
								btnAgregarUsuarioAgregar.setBounds(586, 251, 135, 23);
								panelAgregarUsuario.add(btnAgregarUsuarioAgregar);
								
								JLabel lbAgregarUsuarioNombre = new JLabel("Nombre");
								lbAgregarUsuarioNombre.setHorizontalAlignment(SwingConstants.CENTER);
								lbAgregarUsuarioNombre.setBounds(136, 227, 46, 14);
								panelAgregarUsuario.add(lbAgregarUsuarioNombre);
								
								JLabel lbAgregarUsuarioApellido = new JLabel("Apellido");
								lbAgregarUsuarioApellido.setHorizontalAlignment(SwingConstants.CENTER);
								lbAgregarUsuarioApellido.setBounds(294, 227, 46, 14);
								panelAgregarUsuario.add(lbAgregarUsuarioApellido);
								
								JLabel lbAgregarUsuarioID = new JLabel("ID");
								lbAgregarUsuarioID.setHorizontalAlignment(SwingConstants.CENTER);
								lbAgregarUsuarioID.setBounds(451, 227, 46, 14);
								panelAgregarUsuario.add(lbAgregarUsuarioID);
								
								btnExitAgregarUsuario.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panelAgregarUsuario.setVisible(false);
										panelGestionarUsuario.setVisible(true);
									}
								});
								panelGestionarUsuario.setOpaque(false);
								panelGestionarUsuario.setBounds(0, 0, 776, 488);
								getContentPane().add(panelGestionarUsuario);
								panelGestionarUsuario.setLayout(null);
								
								JButton btnAgregarUsuario = new JButton("Agregar usuario");
								btnAgregarUsuario.setBounds(307, 137, 175, 23);
								panelGestionarUsuario.add(btnAgregarUsuario);
								
								JButton btnModificarUsuario = new JButton("Modificar usuario");
								btnModificarUsuario.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panelGestionarUsuario.setVisible(false);
										panelModificarUsuario.setVisible(true);
									}
								});
								btnModificarUsuario.setBounds(307, 208, 175, 23);
								panelGestionarUsuario.add(btnModificarUsuario);
								
								JButton btnEliminarUsuario = new JButton("Eliminar Usuario");
								btnEliminarUsuario.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panelEliminarUsuario.setVisible(true);
										panelGestionarUsuario.setVisible(false);
									}
								});
								btnEliminarUsuario.setBounds(307, 290, 175, 23);
								panelGestionarUsuario.add(btnEliminarUsuario);
								
								JButton btnExitGestionarUsuario = new JButton("Salir");
								
								btnExitGestionarUsuario.setBounds(677, 454, 89, 23);
								panelGestionarUsuario.add(btnExitGestionarUsuario);
								
								btnAgregarUsuario.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										panelGestionarUsuario.setVisible(false);
										panelAgregarUsuario.setVisible(true);
									}
								});
								
								JPanel PanelAdminOp = new JPanel();
								PanelAdminOp.setBounds(0, 0, anchoPantalla, altoPantalla);
								getContentPane().add(PanelAdminOp);
								PanelAdminOp.setLayout(null);
								PanelAdminOp.setOpaque(false);
								PanelAdminOp.setVisible(false);
								
								JLabel lblSeleccionaOpcion = new JLabel(" Seleccione una opcion");
								lblSeleccionaOpcion.setFont(new Font("Sylfaen", Font.BOLD, 18));
								lblSeleccionaOpcion.setForeground(Color.WHITE);
								lblSeleccionaOpcion.setBounds(300,100,215,36);
								PanelAdminOp.add(lblSeleccionaOpcion);
								
								JButton btnCargaBilletero = new JButton("Cargar billetero");
								btnCargaBilletero.setBounds(300, 140, 189, 23);
								PanelAdminOp.add(btnCargaBilletero);
								
								JButton btnDescargaBilletero = new JButton("Descargar billetero");
								btnDescargaBilletero.setBounds(300,180,189,23);
								PanelAdminOp.add(btnDescargaBilletero);
								
								JButton btnGestionarUsuarios = new JButton("Gestionar usuarios");
								btnGestionarUsuarios.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										PanelAdminOp.setVisible(false);
										panelGestionarUsuario.setVisible(true);
									}
								});
								btnGestionarUsuarios.setBounds(300,260,189,23);
								PanelAdminOp.add(btnGestionarUsuarios);
								
								JButton btnAgregaTarjeta = new JButton("Agregar tarjeta");
								
								btnAgregaTarjeta.setBounds(300,220,189,23);
								PanelAdminOp.add(btnAgregaTarjeta);
								
								JButton btnSalirAdminOp = new JButton("Salir");
								btnSalirAdminOp.setBounds(300,380,189,23);
								PanelAdminOp.add(btnSalirAdminOp);
								
								JPanel panelDepositarDinero = new JPanel();
								panelDepositarDinero.setBounds(0, 0, 776, 488);
								getContentPane().add(panelDepositarDinero);
								panelDepositarDinero.setLayout(null);
								
								JComboBox<String> cbTipoBilleteDeposito = new JComboBox<String>();
								cbTipoBilleteDeposito.setBounds(342, 280, 100, 30);
								cbTipoBilleteDeposito.addItem("100"); 
								cbTipoBilleteDeposito.addItem("500"); 
								cbTipoBilleteDeposito.addItem("1000");
								panelDepositarDinero.add(cbTipoBilleteDeposito);
								
								
								JButton btnExitDepositarDinero = new JButton("Salir");
								
								btnExitDepositarDinero.setBounds(677, 454, 89, 23);
								panelDepositarDinero.add(btnExitDepositarDinero);
								
								tfMontoDeposito = new JTextField();
								tfMontoDeposito.setHorizontalAlignment(SwingConstants.RIGHT);
								tfMontoDeposito.setBounds(298, 191, 190, 20);
								panelDepositarDinero.add(tfMontoDeposito);
								tfMontoDeposito.setColumns(10);
								
								JLabel lblMontoDeposito = new JLabel("Ingrese el monto que desea depositar");
								lblMontoDeposito.setHorizontalAlignment(SwingConstants.CENTER);
								lblMontoDeposito.setBounds(231, 166, 325, 14);
								panelDepositarDinero.add(lblMontoDeposito);
								
								JLabel lblBilleteDeposito = new JLabel("Seleccione el tipo de billete");
								lblBilleteDeposito.setHorizontalAlignment(SwingConstants.CENTER);
								lblBilleteDeposito.setBounds(231, 255, 325, 14);
								panelDepositarDinero.add(lblBilleteDeposito);
								
								
								JButton btnDepositar = new JButton("Depositar");
								
								btnDepositar.setBounds(348, 330, 89, 23);
								panelDepositarDinero.add(btnDepositar);
								panelDepositarDinero.setVisible(false);
								
								
								
								
								
								panelDepositarDinero.setVisible(false);
								panelDepositarDinero.setOpaque(false);
								
								JButton btnTicketDepositar = new JButton("Imprimir");
								
								btnTicketDepositar.setBounds(358, 454, 89, 23);
								panelDepositarDinero.add(btnTicketDepositar);
								
								JTextArea taTicketDeposito = new JTextArea();
								taTicketDeposito.setBounds(265, 119, 260, 257);
								panelDepositarDinero.add(taTicketDeposito);
								btnTicketDepositar.setVisible(false);
								taTicketDeposito.setEditable(false);
								taTicketDeposito.setVisible(false);
								
								JPanel panelRetirar = new JPanel();
								panelRetirar.setBounds(0, 0, 776, 488);
								getContentPane().add(panelRetirar);
								panelRetirar.setLayout(null);
								
								JLabel lblRetirar = new JLabel("Seleccione cuanto dinero desea retirar");
								lblRetirar.setHorizontalAlignment(SwingConstants.CENTER);
								lblRetirar.setBounds(274, 170, 250, 14);
								panelRetirar.add(lblRetirar);
								
								JButton btnRetirar = new JButton("Retirar");
								btnRetirar.setBounds(506, 208, 89, 23);
								panelRetirar.add(btnRetirar);
								
								JButton btnExitRetirar = new JButton("Salir");
								
								btnExitRetirar.setBounds(677, 454, 89, 23);
								panelRetirar.add(btnExitRetirar);
								
								tfRetirar = new JTextField();
								tfRetirar.setHorizontalAlignment(SwingConstants.RIGHT);
								tfRetirar.setBounds(305, 209, 191, 20);
								panelRetirar.add(tfRetirar);
								tfRetirar.setColumns(10);
								
								panelRetirar.setVisible(false);
								
								panelRetirar.setVisible(false);
								panelRetirar.setOpaque(false);
								
								JTextArea taTicketRetiro = new JTextArea();
								taTicketRetiro.setBounds(263, 101, 261, 262);
								panelRetirar.add(taTicketRetiro);
								taTicketRetiro.setVisible(false);
								taTicketRetiro.setEditable(false);
								
								JButton btnTicketRetiro = new JButton("Imprimir");
								
								btnTicketRetiro.setBounds(357, 454, 89, 23);
								panelRetirar.add(btnTicketRetiro);
								btnTicketRetiro.setVisible(false);
								
								btnTicketRetiro.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										taTicketRetiro.setText(null);
										btnTicketRetiro.setVisible(false);
										taTicketRetiro.setVisible(false);
										btnExitRetirar.setVisible(true);
										tfRetirar.setVisible(true);
										btnRetirar.setVisible(true);
										lblRetirar.setVisible(true);
									}
								});
								
								
								
								JPanel panelTransferencias = new JPanel();
								panelTransferencias.setBounds(0, 0, 776, 488);
								getContentPane().add(panelTransferencias);
								panelTransferencias.setLayout(null);
								
								
								tfCBUTransferencia = new JTextField();
								tfCBUTransferencia.setBounds(228, 191, 298, 20);
								panelTransferencias.add(tfCBUTransferencia);
								tfCBUTransferencia.setColumns(10);
								
								tfMontoTransferencia = new JTextField();
								tfMontoTransferencia.setBounds(228, 253, 86, 20);
								panelTransferencias.add(tfMontoTransferencia);
								tfMontoTransferencia.setColumns(10);
								
								JButton btnTransferir = new JButton("Transferir");
								btnTransferir.setBounds(437, 251, 89, 23);

								panelTransferencias.add(btnTransferir);
								
								JLabel lblCBUTransferencia = new JLabel("Ingrese el CBU a transferir");
								lblCBUTransferencia.setHorizontalAlignment(SwingConstants.CENTER);
								lblCBUTransferencia.setBounds(228, 166, 298, 14);
								panelTransferencias.add(lblCBUTransferencia);
								
								JLabel lblMontoTransferencia = new JLabel("Monto");
								lblMontoTransferencia.setHorizontalAlignment(SwingConstants.CENTER);
								lblMontoTransferencia.setBounds(200, 235, 148, 14);
								panelTransferencias.add(lblMontoTransferencia);
								
								JButton btnExitTransferencias = new JButton("Salir");
								
								btnExitTransferencias.setBounds(677, 454, 89, 23);
								panelTransferencias.add(btnExitTransferencias);
								
								btnTransferir.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										if(tfCBUTransferencia.getText().compareTo(auxCuenta.getCBU().toString())!=0){
										cajero.transferir(tfCBUTransferencia, tfMontoTransferencia, auxCuenta, btnTransferir);
										SerializarCajero.persistirCajero(cajero);
										}
										else{
											JOptionPane.showMessageDialog(btnTransferir,"ERROR: No se puede transferir a la misma cuenta bancaria");
											tfCBUTransferencia.setText(null);
											tfMontoTransferencia.setText(null);
										}
									}
								});
								
										
										panelTransferencias.setVisible(false);
										panelTransferencias.setOpaque(false);
										
										JTextArea taTicketTransferencia = new JTextArea();
										taTicketTransferencia.setBounds(270, 120, 221, 250);
										panelTransferencias.add(taTicketTransferencia);
										taTicketTransferencia.setEditable(false);
										taTicketTransferencia.setVisible(false);
								
								JPanel panelAgregarTarjeta = new JPanel();
								panelAgregarTarjeta.setBounds(0, 0, 776, 488);
								getContentPane().add(panelAgregarTarjeta);
								panelAgregarTarjeta.setLayout(null);
								panelAgregarTarjeta.setVisible(false);
								panelAgregarTarjeta.setOpaque(false);
								
								tfAgregarTarjetaPin = new JTextField();
								tfAgregarTarjetaPin.setBounds(311, 216, 135, 20);
								panelAgregarTarjeta.add(tfAgregarTarjetaPin);
								tfAgregarTarjetaPin.setColumns(10);
								
								tfAgregarTarjetaDNI = new JTextField();
								tfAgregarTarjetaDNI.setBounds(525, 216, 108, 20);
								panelAgregarTarjeta.add(tfAgregarTarjetaDNI);
								tfAgregarTarjetaDNI.setColumns(10);
								
								JButton btnAgregarTarjeta = new JButton("Agregar");
								
								btnAgregarTarjeta.setBounds(677, 215, 89, 23);
								panelAgregarTarjeta.add(btnAgregarTarjeta);
								
								JButton btnExitAgregarTarjeta = new JButton("Salir");
								btnExitAgregarTarjeta.setBounds(677, 454, 89, 23);
								panelAgregarTarjeta.add(btnExitAgregarTarjeta);
								
								JComboBox<String> cbBancosAgregarTarjeta = new JComboBox<String>();
								cbBancosAgregarTarjeta.setBounds(63, 215, 185, 22);
								panelAgregarTarjeta.add(cbBancosAgregarTarjeta);
								
								JLabel lblNewLabel_1 = new JLabel("Bancos");
								lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_1.setBounds(63, 190, 185, 14);
								panelAgregarTarjeta.add(lblNewLabel_1);
								
								JLabel lblNewLabel_2 = new JLabel("Pin");
								lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_2.setBounds(311, 191, 135, 14);
								panelAgregarTarjeta.add(lblNewLabel_2);
								
								JLabel lblNewLabel_3 = new JLabel("DNI");
								lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
								lblNewLabel_3.setBounds(525, 191, 108, 14);
								panelAgregarTarjeta.add(lblNewLabel_3);
								
								
								
								btnAgregarTarjeta.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										try {
											boolean encontrado=false;
											int cont=0;
											Banco BancoSeleccionado= cajero.getListaBancos().get(cbBancosAgregarTarjeta.getSelectedIndex());
											int PINSeleccionado= Integer.valueOf(tfAgregarTarjetaPin.getText());
											while (PINSeleccionado!=0){
												PINSeleccionado/=10;
												cont++;
											}
											if (cont==4){
											BigInteger IDSeleccionado= BigInteger.valueOf(Long.valueOf(tfAgregarTarjetaDNI.getText()));
											for (Iterator<Usuario> it=BancoSeleccionado.getListaUsuarios().iterator();it.hasNext();) {
												if (IDSeleccionado.compareTo(BigInteger.valueOf(Long.valueOf((it.next()).getID())))==0) {
													BigInteger auxID=new BigInteger(BancoSeleccionado.getMinRango().toString()+"0000000000");
													boolean existe=true;
													Iterator<Usuario> iterator=BancoSeleccionado.getListaUsuarios().iterator();
													while(existe && iterator.hasNext()) {
														auxID.add(new BigInteger("1"));
														if (auxID.compareTo(BigInteger.valueOf(Long.valueOf(iterator.next().getID())))!=0) {
														TarjetaATM tarjeta;
														JOptionPane.showMessageDialog(btnAgregarTarjeta,tarjeta=new TarjetaATM(auxID,PINSeleccionado,true));
														BancoSeleccionado.agregar(tarjeta);
														encontrado=true;
														existe=false;
														SerializarCajero.persistirCajero(cajero);
														}
													}
												}
											}
											if (!encontrado) {
												JOptionPane.showMessageDialog(tfAgregarTarjetaPin,"No se ha encontrado al usuario");
											}
											tfAgregarTarjetaDNI.setText(null);
											tfAgregarTarjetaPin.setText(null);
										}
										else{
											JOptionPane.showMessageDialog(btnAgregarTarjeta,"ERROR: Recuerde que el pin debe ser de 4 digitos");	
										}										
										}
										catch(java.lang.NumberFormatException nfexc) {
											tfAgregarTarjetaDNI.setText(null);
											tfAgregarTarjetaPin.setText(null);
											JOptionPane.showMessageDialog(btnAgregarTarjeta,"ERROR: Recuerde que debe ingresar los valores correspondientes");
										}
									}
								});
								
								
								JPanel panelBDLP = new JPanel();
								panelBDLP.setBounds(0, 0, 776, 488);
								getContentPane().add(panelBDLP);
								panelBDLP.setLayout(null);
								panelBDLP.setVisible(false);
								
								JButton btnCambiarClave = new JButton();
								btnCambiarClave.setBounds(50, 80, 194, 90);
								btnCambiarClave.setBorderPainted(false);
								btnCambiarClave.setContentAreaFilled(false);
								btnCambiarClave.setOpaque(false);
								btnCambiarClave.setIcon(setIcono("/Botones/BotonOpciones/CambiarClave.png",btnCambiarClave));
								btnCambiarClave.setPressedIcon(setIcono("/Botones/BotonOpciones/CambiarClave-PRESSED.png",btnCambiarClave));
								panelBDLP.add(btnCambiarClave);
								
								JButton btnConsultaSaldo = new JButton();
								btnConsultaSaldo.setBounds(50, 180, 194, 90);
								btnConsultaSaldo.setBorderPainted(false);
								btnConsultaSaldo.setContentAreaFilled(false);
								btnConsultaSaldo.setOpaque(false);
								btnConsultaSaldo.setIcon(setIcono("/Botones/BotonOpciones/ConsultaSaldo.png",btnConsultaSaldo));
								btnConsultaSaldo.setPressedIcon(setIcono("/Botones/BotonOpciones/ConsultaSaldo-PRESSED.png",btnConsultaSaldo));
								panelBDLP.add(btnConsultaSaldo);
								
								
								JButton btnRetirarDinero = new JButton();
								btnRetirarDinero.setBounds(50, 280, 194, 90);
								btnRetirarDinero.setBorderPainted(false);
								btnRetirarDinero.setContentAreaFilled(false);
								btnRetirarDinero.setOpaque(false);
								btnRetirarDinero.setIcon(setIcono("/Botones/BotonOpciones/Retirar.png",btnRetirarDinero));
								btnRetirarDinero.setPressedIcon(setIcono("/Botones/BotonOpciones/Retirar-PRESSED.png",btnRetirarDinero));
								panelBDLP.add(btnRetirarDinero);
								
								
								JButton btnDepositarDinero = new JButton();
								btnDepositarDinero.setBounds(50, 380, 194, 90);
								btnDepositarDinero.setBorderPainted(false);
								btnDepositarDinero.setContentAreaFilled(false);
								btnDepositarDinero.setOpaque(false);
								btnDepositarDinero.setIcon(setIcono("/Botones/BotonOpciones/Depositar.png",btnDepositarDinero));
								btnDepositarDinero.setPressedIcon(setIcono("/Botones/BotonOpciones/Depositar-PRESSED.png",btnDepositarDinero));
								panelBDLP.add(btnDepositarDinero);
								
								JButton btnTransferenciaDinero = new JButton();
								btnTransferenciaDinero.setBounds(350, 280, 194, 90);
								btnTransferenciaDinero.setBorderPainted(false);
								btnTransferenciaDinero.setContentAreaFilled(false);
								btnTransferenciaDinero.setOpaque(false);
								btnTransferenciaDinero.setIcon(setIcono("/Botones/BotonOpciones/Transferencias.png",btnTransferenciaDinero));
								btnTransferenciaDinero.setPressedIcon(setIcono("/Botones/BotonOpciones/Transferencias-PRESSED.png",btnTransferenciaDinero));
								panelBDLP.add(btnTransferenciaDinero);
								
								JButton btnConsultaMovimientos = new JButton();
								btnConsultaMovimientos.setBounds(350, 180, 194, 90);
								btnConsultaMovimientos.setBorderPainted(false);
								btnConsultaMovimientos.setContentAreaFilled(false);
								btnConsultaMovimientos.setOpaque(false);
								btnConsultaMovimientos.setIcon(setIcono("/Botones/BotonOpciones/Consultas.png",btnConsultaMovimientos));
								btnConsultaMovimientos.setPressedIcon(setIcono("/Botones/BotonOpciones/Consultas-PRESSED.png",btnConsultaMovimientos));
								panelBDLP.add(btnConsultaMovimientos);
								
								JButton btnExit = new JButton();
								btnExit.setBounds(350, 380, 194, 90);
								btnExit.setBorderPainted(false);
								btnExit.setContentAreaFilled(false);
								btnExit.setOpaque(false);
								btnExit.setIcon(setIcono("/Botones/BotonOpciones/Salir.png",btnExit));
								btnExit.setPressedIcon(setIcono("/Botones/BotonOpciones/Salir-PRESSED.png",btnExit));
								panelBDLP.add(btnExit);
								

								JLabel lblNombreBDLP = new JLabel();
								lblNombreBDLP.setFont(new Font("Sylfaen", Font.BOLD, 16));
								lblNombreBDLP.setForeground(Color.WHITE);
								lblNombreBDLP.setHorizontalAlignment(SwingConstants.LEFT);
								lblNombreBDLP.setBounds(595, 35, 218, 14);
								panelBDLP.add(lblNombreBDLP);
								
								JButton btnIconoUsuario = new JButton();
								btnIconoUsuario.setBounds(540,18,50,50);
								btnIconoUsuario.setBorderPainted(false);
								btnIconoUsuario.setContentAreaFilled(false);
								btnIconoUsuario.setOpaque(false);
								btnIconoUsuario.setIcon(setIcono("/imagenes/Icono-Usuario.png",btnIconoUsuario));
								panelBDLP.add(btnIconoUsuario);
								
								
								JPanel panelBancos = new JPanel();
								panelBancos.setLayout(null);
								panelBancos.setBounds(0, 0, 776, 488);
								getContentPane().add(panelBancos);
								panelBancos.setVisible(false);
								
								JButton btnRetirarDineroBancos = new JButton();
								btnRetirarDineroBancos.setBounds(50, 280, 194, 90);
								btnRetirarDineroBancos.setBorderPainted(false);
								btnRetirarDineroBancos.setContentAreaFilled(false);
								btnRetirarDineroBancos.setOpaque(false);
								btnRetirarDineroBancos.setIcon(setIcono("/Botones/BotonOpciones/Retirar.png",btnRetirarDineroBancos));
								btnRetirarDineroBancos.setPressedIcon(setIcono("/Botones/BotonOpciones/Retirar-PRESSED.png",btnRetirarDineroBancos));
								panelBancos.add(btnRetirarDineroBancos);
								
								JButton btnCambiarClaveBancos = new JButton();
								btnCambiarClaveBancos.setBounds(50, 80, 194, 90);
								btnCambiarClaveBancos.setBorderPainted(false);
								btnCambiarClaveBancos.setContentAreaFilled(false);
								btnCambiarClaveBancos.setOpaque(false);
								btnCambiarClaveBancos.setIcon(setIcono("/Botones/BotonOpciones/CambiarClave.png",btnCambiarClaveBancos));
								btnCambiarClaveBancos.setPressedIcon(setIcono("/Botones/BotonOpciones/CambiarClave-PRESSED.png",btnCambiarClaveBancos));
								panelBancos.add(btnCambiarClaveBancos);
								
								JButton btnConsultaSaldoBancos = new JButton();
								btnConsultaSaldoBancos.setBounds(50, 180, 194, 90);
								btnConsultaSaldoBancos.setBorderPainted(false);
								btnConsultaSaldoBancos.setContentAreaFilled(false);
								btnConsultaSaldoBancos.setOpaque(false);
								btnConsultaSaldoBancos.setIcon(setIcono("/Botones/BotonOpciones/ConsultaSaldo.png",btnConsultaSaldoBancos));
								btnConsultaSaldoBancos.setPressedIcon(setIcono("/Botones/BotonOpciones/ConsultaSaldo-PRESSED.png",btnConsultaSaldoBancos));
								panelBancos.add(btnConsultaSaldoBancos);
								
								JButton btnDepositarDineroBancos = new JButton();
								btnDepositarDineroBancos.setBounds(50, 380, 194, 90);
								btnDepositarDineroBancos.setBorderPainted(false);
								btnDepositarDineroBancos.setContentAreaFilled(false);
								btnDepositarDineroBancos.setOpaque(false);
								btnDepositarDineroBancos.setIcon(setIcono("/Botones/BotonOpciones/Depositar.png",btnDepositarDineroBancos));
								btnDepositarDineroBancos.setPressedIcon(setIcono("/Botones/BotonOpciones/Depositar-PRESSED.png",btnDepositarDineroBancos));
								panelBancos.add(btnDepositarDineroBancos);
								
								JButton btnTransferenciaDineroBancos = new JButton();
								btnTransferenciaDineroBancos.setBounds(350, 180, 194, 90);
								btnTransferenciaDineroBancos.setBorderPainted(false);
								btnTransferenciaDineroBancos.setContentAreaFilled(false);
								btnTransferenciaDineroBancos.setOpaque(false);
								btnTransferenciaDineroBancos.setIcon(setIcono("/Botones/BotonOpciones/Transferencias.png",btnTransferenciaDineroBancos));
								btnTransferenciaDineroBancos.setPressedIcon(setIcono("/Botones/BotonOpciones/Transferencias-PRESSED.png",btnTransferenciaDineroBancos));
								panelBancos.add(btnTransferenciaDineroBancos);
								
								JButton btnExitBancos = new JButton();
								btnExitBancos.setBounds(350, 280, 194, 90);
								btnExitBancos.setBorderPainted(false);
								btnExitBancos.setContentAreaFilled(false);
								btnExitBancos.setOpaque(false);
								btnExitBancos.setIcon(setIcono("/Botones/BotonOpciones/Salir.png",btnExitBancos));
								btnExitBancos.setPressedIcon(setIcono("/Botones/BotonOpciones/Salir-PRESSED.png",btnExitBancos));
								panelBancos.add(btnExitBancos);
								
								JPanel panelMovimientos = new JPanel();
								panelMovimientos.setBounds(0, 0, 776, 488);
								getContentPane().add(panelMovimientos);
								panelMovimientos.setLayout(null);
								
								JButton btnExitMovimientos = new JButton("Salir");
								
								btnExitMovimientos.setBounds(677, 454, 89, 23);
								panelMovimientos.add(btnExitMovimientos);
								
								JLabel lblMovimientos = new JLabel("Ultimos movimientos:");
								lblMovimientos.setHorizontalAlignment(SwingConstants.CENTER);
								lblMovimientos.setBounds(275, 34, 227, 14);
								panelMovimientos.add(lblMovimientos);
								
								JTextArea taMovimientos = new JTextArea();
								taMovimientos.setEditable(false);
								taMovimientos.setForeground(Color.BLACK);
								taMovimientos.setBounds(0, 0, 5, 22);
								panelMovimientos.add(taMovimientos);
								
								JScrollPane spMovimientos = new JScrollPane(taMovimientos);
								spMovimientos.setBounds(10, 59, 756, 384);
								panelMovimientos.add(spMovimientos);
								
								JPanel PanelSeleccionUsuario = new JPanel();
								PanelSeleccionUsuario.setBounds(0, 0, anchoPantalla, altoPantalla);
								getContentPane().add(PanelSeleccionUsuario);
								PanelSeleccionUsuario.setLayout(null);
								PanelSeleccionUsuario.setOpaque(false);
								
								JButton btnCliente = new JButton();
								btnCliente.setBounds(430, 190, 120, 120);
								
								JButton btnAdmin = new JButton();
								
								btnAdmin.setBounds(240, 190, 120, 120);
								btnAdmin.setBorderPainted(false);
								btnAdmin.setContentAreaFilled(false);
								btnAdmin.setOpaque(false);

								btnCliente.setBorderPainted(false);
								btnCliente.setContentAreaFilled(false);
								btnCliente.setOpaque(false);
								
								btnCliente.setIcon(setIcono("/Botones/BotonInicio/boton-CLIENTE.png", btnCliente));
								btnAdmin.setIcon(setIcono("/Botones/BotonInicio/boton-ADMIN.png", btnAdmin));
								btnCliente.setPressedIcon(setIcono("/Botones/BotonInicio/boton-CLIENTE-PRESSED.png", btnCliente));
								btnAdmin.setPressedIcon(setIcono("/Botones/BotonInicio/boton-ADMIN-PRESSED.png", btnAdmin));
								
								PanelSeleccionUsuario.add(btnCliente);
								PanelSeleccionUsuario.add(btnAdmin);
								PanelSeleccionUsuario.setVisible(true);
							
							
			//	PANELES ADMIN O INGRESO					
								
								
								
								
								JPanel PanelAdminIngreso = new JPanel();
								PanelAdminIngreso.setBounds(0, 0, anchoPantalla, altoPantalla);
								getContentPane().add(PanelAdminIngreso);
								PanelAdminIngreso.setLayout(null);
								PanelAdminIngreso.setOpaque(false);
								PanelAdminIngreso.setVisible(false);
								
								JPanel PanelIngreso = new JPanel();
								PanelIngreso.setBounds(0, 0, anchoPantalla,altoPantalla);
								getContentPane().add(PanelIngreso);
								PanelIngreso.setLayout(null);
								PanelIngreso.setOpaque(false);	
								PanelIngreso.setVisible(false);
								
								btnAdmin.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										PanelSeleccionUsuario.setVisible(false);
										PanelAdminIngreso.setVisible(true);
									}
									
								});
								
								btnCliente.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										PanelSeleccionUsuario.setVisible(false);
										PanelIngreso.setVisible(true);
									}
								});

								JLabel lblUsuarioAdmin = new JLabel("Ingrese nombre de usuario");
								lblUsuarioAdmin.setFont(new Font("Sylfaen", Font.PLAIN, 18));
								lblUsuarioAdmin.setBounds(275, 170, 215, 36);
								PanelAdminIngreso.add(lblUsuarioAdmin);
								
								JTextField tfUsuarioAdmin = new JTextField();
								tfUsuarioAdmin.setBounds(244, 200, 249, 20);
								PanelAdminIngreso.add(tfUsuarioAdmin);
								tfUsuarioAdmin.setColumns(10);
								
								JLabel lblContraseniaAdmin = new JLabel("Ingrese contrase\u00F1a");
								lblContraseniaAdmin.setFont(new Font("Sylfaen", Font.PLAIN, 18));
								lblContraseniaAdmin.setBounds(295, 225, 215, 36);
								PanelAdminIngreso.add(lblContraseniaAdmin);
								
								JPasswordField tfContraseniaAdmin = new JPasswordField();
								tfContraseniaAdmin.setBounds(244, 255, 249, 20);
								PanelAdminIngreso.add(tfContraseniaAdmin);
								tfContraseniaAdmin.setColumns(10);
								
								JButton btnIngresaAdmin = new JButton("Ingresar");
								btnIngresaAdmin.setBounds(320, 290, 89, 23);
								PanelAdminIngreso.add(btnIngresaAdmin);
								
								JButton btnSalirAdmin = new JButton("Salir");
								btnSalirAdmin.setBounds(320, 320, 89, 23);
								PanelAdminIngreso.add(btnSalirAdmin);
								
								btnIngresaAdmin.addActionListener(new ActionListener() {
									@SuppressWarnings("deprecation")
									public void actionPerformed(ActionEvent e) {
										if (tfUsuarioAdmin.getText().compareTo(cajero.getAdmin()) == 0 && tfContraseniaAdmin.getText().compareTo(cajero.getPassword()) == 0) {
											PanelAdminIngreso.setVisible(false);
											PanelAdminOp.setVisible(true);
											cajero.setModoMantenimiento(true);
										}
										else
											JOptionPane.showMessageDialog(btnIngresaAdmin, "Usuario y/o contraseña incorrectos");
										tfUsuarioAdmin.setText(null);
										tfContraseniaAdmin.setText(null);
									}
								});
								
								btnSalirAdmin.addActionListener(new ActionListener() {
									public void actionPerformed (ActionEvent e) {
										PanelAdminIngreso.setVisible(false);
										PanelSeleccionUsuario.setVisible(true);
										cajero.setModoMantenimiento(false);
										tfContraseniaAdmin.setText(null);
										tfUsuarioAdmin.setText(null);
									}
								});
								
								JPanel PanelCargaBilletero = new JPanel();
								PanelCargaBilletero.setBounds(0,0,anchoPantalla,altoPantalla);
								getContentPane().add(PanelCargaBilletero);
								PanelCargaBilletero.setLayout(null);
								PanelCargaBilletero.setOpaque(false);
								PanelCargaBilletero.setVisible(false);
								
								JLabel lblTipoBillete = new JLabel("Seleccione el tipo de billete para recarga");
								lblTipoBillete.setFont(new Font("Sylfaen", Font.BOLD, 18));
								lblTipoBillete.setForeground(Color.WHITE);
								lblTipoBillete.setBounds(240,100,400,36);
								PanelCargaBilletero.add(lblTipoBillete);
								
								JComboBox<String> cbTipoBillete = new JComboBox<String>();
								cbTipoBillete.setBounds(240, 146, 340, 30);
								cbTipoBillete.addItem("100"); cbTipoBillete.addItem("500"); cbTipoBillete.addItem("1000");
								PanelCargaBilletero.add(cbTipoBillete);
								
								JLabel lblCantBillete = new JLabel("Ingrese la cantidad de billetes");
								lblCantBillete.setFont(new Font("Sylfaen", Font.BOLD, 18));
								lblCantBillete.setForeground(Color.WHITE);
								lblCantBillete.setBounds(280,192,400,36);
								PanelCargaBilletero.add(lblCantBillete);
								
								JTextField tfCantBillete = new JTextField();
								tfCantBillete.setBounds(240, 238, 340, 20);
								tfCantBillete.setColumns(10);tfCantBillete.setHorizontalAlignment(SwingConstants.CENTER);
								PanelCargaBilletero.add(tfCantBillete);
								
								JButton btnAceptaIngreso = new JButton("Aceptar");
								btnAceptaIngreso.setBounds(240,284,340,36);
								PanelCargaBilletero.add(btnAceptaIngreso);
								
								JButton btnCreaBilletero = new JButton("Crear Billetero (Para serializacion)");
								btnCreaBilletero.setBounds(240,376,340,36);
								PanelCargaBilletero.add(btnCreaBilletero);
								
								JButton btnSalirAB = new JButton("Salir");
								btnSalirAB.setBounds(240,330,340,36);
								PanelCargaBilletero.add(btnSalirAB);
								
								JPanel PanelDescargaBilletero = new JPanel();
								PanelDescargaBilletero.setBounds(0, 0, anchoPantalla, altoPantalla);
								getContentPane().add(PanelDescargaBilletero);
								PanelDescargaBilletero.setLayout(null);
								PanelDescargaBilletero.setOpaque(false);
								PanelDescargaBilletero.setVisible(false);
								
								JLabel lblTipoBilleteDescarga = new JLabel("Seleccione el tipo de billete para descarga");
								lblTipoBilleteDescarga.setFont(new Font("Sylfaen", Font.BOLD, 18));
								lblTipoBilleteDescarga.setForeground(Color.WHITE);
								lblTipoBilleteDescarga.setBounds(240,100,400,36);
								PanelDescargaBilletero.add(lblTipoBilleteDescarga);
								
								JComboBox<String> cbTipoBilleteDescarga = new JComboBox<String>();
								cbTipoBilleteDescarga.setBounds(240, 146, 340, 30);
								cbTipoBilleteDescarga.addItem("100"); cbTipoBilleteDescarga.addItem("500"); cbTipoBilleteDescarga.addItem("1000");
								PanelDescargaBilletero.add(cbTipoBilleteDescarga);
								
								JLabel lblCantBilleteDescarga = new JLabel("Ingrese la cantidad de billetes a descargar");
								lblCantBilleteDescarga.setFont(new Font("Sylfaen", Font.BOLD, 18));
								lblCantBilleteDescarga.setForeground(Color.WHITE);
								lblCantBilleteDescarga.setBounds(240,192,400,36);
								PanelDescargaBilletero.add(lblCantBilleteDescarga);
								
								JTextField tfCantBilleteDescarga = new JTextField();
								tfCantBilleteDescarga.setBounds(240, 238, 340, 20);
								tfCantBilleteDescarga.setColumns(10);tfCantBilleteDescarga.setHorizontalAlignment(SwingConstants.CENTER);
								PanelDescargaBilletero.add(tfCantBilleteDescarga);
								
								JButton btnAceptaDescarga = new JButton("Aceptar");
								btnAceptaDescarga.setBounds(240,284,340,36);
								PanelDescargaBilletero.add(btnAceptaDescarga);
								
								JButton btnSalirDB = new JButton("Salir");
								btnSalirDB.setBounds(240,330,340,36);
								PanelDescargaBilletero.add(btnSalirDB);
								
								
								btnCreaBilletero.addActionListener(new ActionListener () {
									public void actionPerformed(ActionEvent e) {
										BigDecimal tipoBill = new BigDecimal(cbTipoBillete.getSelectedItem().toString());
										try {
											if (tipoBill.compareTo(new BigDecimal("100"))==0) {
												cajero.agregar(new Billetero(new BigDecimal("100"),0));
												JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero creado correctamente");
											}
											else 
												if (tipoBill.compareTo(new BigDecimal("500"))==0) {
													cajero.agregar(new Billetero(new BigDecimal("500"),0));
													JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero creado correctamente");
												}
												else
													if (tipoBill.compareTo(new BigDecimal("1000"))==0) {
														cajero.agregar(new Billetero(new BigDecimal("1000"),0));
														JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero creado correctamente");
													}
													else
														JOptionPane.showMessageDialog(btnAceptaIngreso, "No se ha podido crear el billetero");
										}
										catch(java.util.NoSuchElementException exc) {
											JOptionPane.showMessageDialog(btnCreaBilletero, exc.toString());
										}
										SerializarCajero.persistirCajero(cajero);
									}
								});
								
								btnSalirAB.addActionListener(new ActionListener () {
									public void actionPerformed (ActionEvent e) {
										PanelCargaBilletero.setVisible(false);
										PanelAdminOp.setVisible(true);
										tfCantBillete.setText(null);
									}
								});
								
								btnAceptaIngreso.addActionListener(new ActionListener () {
									public void actionPerformed (ActionEvent e) {
										for (Iterator<Billetero> i = cajero.getBilleteros().iterator(); i.hasNext();) {
											Billetero billetero = i.next();
											BigDecimal tipoBill = new BigDecimal(cbTipoBillete.getSelectedItem().toString());
											try {
												if (billetero.getValorBillete().compareTo(new BigDecimal("100"))==0 && tipoBill.compareTo(new BigDecimal("100"))==0) {
													billetero.recargar(new BigDecimal(cbTipoBillete.getSelectedItem().toString()), cajero, Integer.valueOf(tfCantBillete.getText()));
													JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero de 100 recargado, cantidad de billetes: " + billetero.getCantidad());
												}
												if (billetero.getValorBillete().compareTo(new BigDecimal("500"))==0 && tipoBill.compareTo(new BigDecimal("500"))==0) {
													billetero.recargar(new BigDecimal(cbTipoBillete.getSelectedItem().toString()), cajero,Integer.valueOf(tfCantBillete.getText()));
													JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero de 500 recargado, cantidad de billetes: " + billetero.getCantidad());
												}
												if (billetero.getValorBillete().compareTo(new BigDecimal("1000"))==0 && tipoBill.compareTo(new BigDecimal("1000"))==0) {
													billetero.recargar(new BigDecimal(cbTipoBillete.getSelectedItem().toString()), cajero,Integer.valueOf(tfCantBillete.getText()));
													JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero de 1000 recargado, cantidad de billetes: " + billetero.getCantidad());
												}
											}
											catch(java.lang.NumberFormatException nfexc) {
												tfCantBillete.setText(null);
												JOptionPane.showMessageDialog(btnAceptaIngreso,"ERROR: Recuerde que debe ingresar un numero");
											}
											SerializarCajero.persistirCajero(cajero);
										}
									tfCantBillete.setText(null);
									}
								});
								
								btnAceptaDescarga.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {

										for (Iterator<Billetero> i = cajero.getBilleteros().iterator(); i.hasNext();) {
											try {
											Billetero billetero = i.next();
											BigDecimal tipoBill = new BigDecimal(cbTipoBilleteDescarga.getSelectedItem().toString());
											int cantBill= Integer.valueOf(tfCantBilleteDescarga.getText());
											if ((billetero.getCantidad() - cantBill) >=0) {	
												if (billetero.getValorBillete().compareTo(new BigDecimal("100"))==0 && tipoBill.compareTo(new BigDecimal("100"))==0) {										
													billetero.descargar(cantBill);
													JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero de 100 descargado");																							
												}
												else
													if (billetero.getValorBillete().compareTo(new BigDecimal("500"))==0 && tipoBill.compareTo(new BigDecimal("500"))==0) {												
														billetero.descargar(cantBill);
														JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero de 500 descargado");												
													}
													else
														if (billetero.getValorBillete().compareTo(new BigDecimal("1000"))==0 && tipoBill.compareTo(new BigDecimal("1000"))==0) {
															billetero.descargar(cantBill);
															JOptionPane.showMessageDialog(btnAceptaIngreso, "Billetero de 1000 descargado");
														}
											}
											else {
												JOptionPane.showMessageDialog(btnAceptaIngreso, "No es posible descargar esa cantidad");	
											}
											}
											catch(java.lang.NumberFormatException nfexc) {
												tfCantBilleteDescarga.setText(null);
												JOptionPane.showMessageDialog(btnAceptaDescarga,"ERROR: Recuerde que debe ingresar un digito");
											}
											SerializarCajero.persistirCajero(cajero);
										}
									tfCantBilleteDescarga.setText(null);
									}
								});
								
								btnSalirDB.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										PanelDescargaBilletero.setVisible(false);
										PanelAdminOp.setVisible(true);
										tfCantBilleteDescarga.setText(null);
									}
								});
								
								
								
								
				//FIN PANELES ADMIN E INGRESO
								
								
								
								
								TFTarjeta = new JTextField();
								TFTarjeta.setBounds(244, 272, 249, 20);
								PanelIngreso.add(TFTarjeta);
								TFTarjeta.setColumns(10);
								
								JLabel lblBienvenido = new JLabel("Bienvenido");
								lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
								lblBienvenido.setForeground(Color.WHITE);
								lblBienvenido.setFont(new Font("SansSerif", Font.BOLD, 84));
								lblBienvenido.setBounds(162, 110, 468, 104);
								PanelIngreso.add(lblBienvenido);
								
								JLabel lblTarjeta = new JLabel("Por favor ingrese su tarjeta");
								lblTarjeta.setFont(new Font("Sylfaen", Font.PLAIN, 18));
								lblTarjeta.setBounds(290, 225, 215, 36);
								PanelIngreso.add(lblTarjeta);
								
								JButton btnIngresar = new JButton("Ingresar");
								
								btnIngresar.setBounds(503, 271, 89, 23);	
								
								PanelIngreso.add(btnIngresar);
								
								JButton btnSalir = new JButton("Salir");
								btnSalir.setBounds(340, 315, 89, 23);
								PanelIngreso.add(btnSalir);
								
								btnSalir.addActionListener(new ActionListener() {
									public void actionPerformed (ActionEvent e) {
										PanelIngreso.setVisible(false);
										PanelSeleccionUsuario.setVisible(true);
										TFTarjeta.setText(null);
									}
								});
								
								
						JPanel PanelPin = new JPanel();
						
						PanelPin.setOpaque(false);	
						
						PanelPin.setBounds(0, 0, anchoPantalla,altoPantalla);
						getContentPane().add(PanelPin);
						PanelPin.setLayout(null);
						
						TFPin = new JPasswordField();
						TFPin.setBounds(227, 234, 249, 20);
						PanelPin.add(TFPin);
						TFPin.setColumns(10);
						
						JButton btnVerificar = new JButton("Ingresar");
						
							btnVerificar.setBounds(486, 233, 89, 23);
							PanelPin.add(btnVerificar);
							
							JLabel lblPin = new JLabel("Por favor ingrese su PIN");
							lblPin.setFont(new Font("Sylfaen", Font.PLAIN, 18));
							lblPin.setBounds(286, 152, 190, 35);
							PanelPin.add(lblPin);
							
							JButton btnExitPIN = new JButton("Salir");
							
							btnExitPIN.setBounds(676, 455, 89, 23);
							PanelPin.add(btnExitPIN);
							PanelPin.setVisible(false);
					
							
							btnExitPIN.addActionListener(new ActionListener() {
								public void actionPerformed(ActionEvent e) {
									TFPin.setText(null);
									PanelPin.setVisible(false);
									PanelIngreso.setVisible(true);
								}
							});
		
		JPanel panelSeleccionarCuenta = new JPanel();
		panelSeleccionarCuenta.setBounds(0, 0, 776, 488);
		getContentPane().add(panelSeleccionarCuenta);
		panelSeleccionarCuenta.setLayout(null);
		
		JComboBox<Cuenta> comboBox = new JComboBox<Cuenta>();
		comboBox.setBounds(218, 205, 356, 22);
		panelSeleccionarCuenta.add(comboBox);
		
		JLabel lblSeleccionCuenta = new JLabel("Seleccione una cuenta para ingresar a su HomeBanking");
		lblSeleccionCuenta.setHorizontalAlignment(SwingConstants.CENTER);
		lblSeleccionCuenta.setBounds(218, 180, 356, 14);
		panelSeleccionarCuenta.add(lblSeleccionCuenta);
		
		JButton btnSeleccionarCuenta = new JButton("Seleccionar");
		
		btnSeleccionarCuenta.setBounds(342, 250, 89, 23);
		panelSeleccionarCuenta.add(btnSeleccionarCuenta);
		
		JButton btnExitConsultaCuenta = new JButton("Salir");
		
		btnExitConsultaCuenta.setBounds(677, 454, 89, 23);
		panelSeleccionarCuenta.add(btnExitConsultaCuenta);
		
		Label label = new Label("");
		label.setAlignment(Label.CENTER);
		label.setBounds(218, 140, 356, 22);
		panelSeleccionarCuenta.add(label);
		
		JPanel panelConsultaSaldo = new JPanel();
		panelConsultaSaldo.setBounds(0, 0, 776, 488);
		getContentPane().add(panelConsultaSaldo);
		panelConsultaSaldo.setLayout(null);
		
		JButton btnExitConsulta = new JButton("Salir");
		
		btnExitConsulta.setBounds(677, 454, 89, 23);
		panelConsultaSaldo.add(btnExitConsulta);
		
		JTextArea taConsultaSaldo = new JTextArea();
		taConsultaSaldo.setBounds(263, 101, 261, 262);
		panelConsultaSaldo.add(taConsultaSaldo);
		taConsultaSaldo.setVisible(false);
		taConsultaSaldo.setEditable(false);
		
		JPanel panelCambiarPIN = new JPanel();
		panelCambiarPIN.setBounds(0, 0, 776, 488);
		getContentPane().add(panelCambiarPIN);
		panelCambiarPIN.setLayout(null);
		panelCambiarPIN.setVisible(false);
		
		
		TFPinActual = new JPasswordField();
		TFPinActual.setHorizontalAlignment(SwingConstants.RIGHT);
		TFPinActual.setBounds(322, 108, 137, 20);
		panelCambiarPIN.add(TFPinActual);
		TFPinActual.setColumns(10);
		
		TFPinNuevo = new JPasswordField();
		TFPinNuevo.setHorizontalAlignment(SwingConstants.RIGHT);
		TFPinNuevo.setColumns(10);
		TFPinNuevo.setBounds(322, 201, 137, 20);
		panelCambiarPIN.add(TFPinNuevo);
		
		TFPinConfirmar = new JPasswordField();
		TFPinConfirmar.setHorizontalAlignment(SwingConstants.RIGHT);
		TFPinConfirmar.setColumns(10);
		TFPinConfirmar.setBounds(322, 290, 137, 20);
		panelCambiarPIN.add(TFPinConfirmar);
		
		JLabel lblIngreseSuClave = new JLabel("Ingrese su clave actual");
		lblIngreseSuClave.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSuClave.setBounds(306, 83, 165, 14);
		panelCambiarPIN.add(lblIngreseSuClave);
		
		JLabel lblIngreseSuNueva = new JLabel("Ingrese su nueva clave");
		lblIngreseSuNueva.setHorizontalAlignment(SwingConstants.CENTER);
		lblIngreseSuNueva.setBounds(306, 176, 165, 14);
		panelCambiarPIN.add(lblIngreseSuNueva);
		
		JLabel lblConfirmeSuNueva = new JLabel("Confirme su nueva clave");
		lblConfirmeSuNueva.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmeSuNueva.setBounds(306, 265, 165, 14);
		panelCambiarPIN.add(lblConfirmeSuNueva);
		
		JButton btnCambiarPIN = new JButton("Cambiar PIN");
		
		btnCambiarPIN.setBounds(517, 289, 137, 23);
		panelCambiarPIN.add(btnCambiarPIN);
		
		JButton btnExitCP = new JButton("Salir");
		
		JLabel lblNombreBancos = new JLabel();
		lblNombreBancos.setFont(new Font("Sylfaen", Font.BOLD, 16));
		lblNombreBancos.setForeground(Color.WHITE);
		lblNombreBancos.setHorizontalAlignment(SwingConstants.LEFT);
		lblNombreBancos.setBounds(595, 35, 218, 14);
		panelBancos.add(lblNombreBancos);
		
		JButton btnIconoUsuarioBancos = new JButton();
		btnIconoUsuarioBancos.setBounds(540,18,50,50);
		btnIconoUsuarioBancos.setBorderPainted(false);
		btnIconoUsuarioBancos.setContentAreaFilled(false);
		btnIconoUsuarioBancos.setOpaque(false);
		btnIconoUsuarioBancos.setIcon(setIcono("/imagenes/Icono-Usuario.png",btnIconoUsuarioBancos));
		panelBancos.add(btnIconoUsuarioBancos);
		
		
		btnExitConsulta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taConsultaSaldo.setText(null);
				panelConsultaSaldo.setVisible(false);
				if (BDLP) {
					panelBDLP.setVisible(true);
				}
				else {
					panelBancos.setVisible(true);
				}
			}
		});
		
		btnSeleccionarCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					auxCuenta=(Cuenta) comboBox.getSelectedItem();
					comboBox.removeAllItems();
					panelSeleccionarCuenta.setVisible(false);
/*BDLP*/			if ((new BigInteger(TarjetaInsertada.getId().toString().substring(0,6)).compareTo(new BigInteger("300001"))>=0)&&(new BigInteger(TarjetaInsertada.getId().toString().substring(0,6)).compareTo(new BigInteger("500000"))<=0)) {
						panelBDLP.setVisible(true);
						//setContentPane(getJContentPane("SelecOp-ClienteBDP.png"));
						BDLP=true;						
					}else {
					 	panelBancos.setVisible(true);
					 	BDLP=false;
					}
				}
				catch (Exception exc) {
					JOptionPane.showMessageDialog(btnVerificar,exc);
				}
			}
		});
		
		btnExitConsultaCuenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelSeleccionarCuenta.setVisible(false);
				PanelIngreso.setVisible(true);
				comboBox.removeAllItems();
			}
		});
		
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBDLP.setVisible(false);
				PanelIngreso.setVisible(true);
			}
		});
		
		btnVerificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cajero.validar(TFPin, TarjetaInsertada, check, PanelPin, comboBox, label, panelSeleccionarCuenta, panelBDLP, BDLP, panelBancos, auxCuenta, tries, PanelIngreso, lblNombreBDLP, lblNombreBancos);
			}	
		});
		
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BigInteger ID=BigInteger.valueOf(Long.parseLong(TFTarjeta.getText()));
					TFTarjeta.setText(null);
					int count=0;
					BigInteger aux=ID;
					while (aux!=BigInteger.valueOf(0)) {
						aux=aux.divide(BigInteger.valueOf(10));
						count++;
					}
					if (count==16){//Cantidad Correcta de digitos
						
						int shortStr=Integer.valueOf(ID.toString().substring(0,4));
						for (Iterator<Banco> itBancos=cajero.getBancos().iterator();itBancos.hasNext();) {
							Banco next = itBancos.next();
							if (shortStr<Integer.valueOf(next.getMaxRango().toString().substring(0,4))&&shortStr>Integer.valueOf(next.getMinRango().toString().substring(0,4))) {
								auxBanco=next;
							}
						}
						Iterator<TarjetaATM> itTarjeta=auxBanco.getTarjetas();
						while (ID.compareTo((auxTarjeta=itTarjeta.next()).getId())!=0) {
							}
						TarjetaInsertada=auxTarjeta;
						PanelPin.setVisible(true);
						PanelIngreso.setVisible(false);
							if (!auxTarjeta.isHabilitada()) {
								JOptionPane.showMessageDialog(btnIngresar,"Tarjeta deshabilitada");
								PanelPin.setVisible(false);
								PanelIngreso.setVisible(true);
							}
						
					}else {				//Digitos de mas/menos
						JOptionPane.showMessageDialog(TFTarjeta,"ERROR: Recuerde que debe ingresar 16 digitos");
					
						
					}
					
					}
					catch(java.lang.NumberFormatException err){
						JOptionPane.showMessageDialog(TFTarjeta,"ERROR: Recuerde que debe ingresar 16 digitos");
						TFTarjeta.setText(null);
					}
					catch(java.util.NoSuchElementException err) {
						JOptionPane.showMessageDialog(TFTarjeta,"ERROR: Tarjeta no encontrada");
						TFTarjeta.setText(null);
					}
				
			}
		});
		
		btnRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String montoStr=tfRetirar.getText();
				int cantXBillete;
				cajero.extraer(tfRetirar, auxCuenta, taTicketRetiro, btnRetirar, btnExitRetirar, lblRetirar, btnTicketRetiro, btnTicketRetiro, BDLP, btnRetirarDineroBancos);
				if (cajero.isRetirado()) {
					montoStr=montoStr.substring(0,montoStr.length()-2);
					for(int i = montoStr.length();i>0;i--) {
						if (Integer.valueOf(montoStr.substring(i-1,i))!=0){
							cantXBillete=(int) (Integer.valueOf(montoStr.substring(i-1,i)+"00")*Math.pow(10,Integer.valueOf(montoStr.length())-i));
							Iterator<Billetero> it=cajero.getListaBilleteros().iterator();
							Billetero aux;
							try {
							while((aux=it.next()).getValorBillete().compareTo(BigDecimal.valueOf((int)Math.pow(10,Integer.valueOf(montoStr.length())-i+2)))!=0) {
							}
							aux.descargar(cantXBillete/Integer.valueOf(aux.getValorBillete().toString()));
							}
							catch (java.util.NoSuchElementException nseex) {
								JOptionPane.showMessageDialog(btnRetirar,nseex.toString());
							}
							
						}
					}
				
					SerializarCajero.persistirCajero(cajero);
				}
			}
		});
		
		
		btnExitMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelMovimientos.setVisible(false);
				panelBDLP.setVisible(true);
				taMovimientos.setText(null);
			}
		});
		btnExitCP.setBounds(677, 454, 89, 23);
		panelCambiarPIN.add(btnExitCP);
		btnExitCP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TFPinNuevo.setText(null);
				TFPinActual.setText(null);
				TFPinConfirmar.setText(null);
				panelCambiarPIN.setVisible(false);
				if (BDLP) {
					panelBDLP.setVisible(true);
				}else {
					panelBancos.setVisible(true);
				}
			}
		});
		

		btnExitRetirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelRetirar.setVisible(false);
				if (BDLP) {
					panelBDLP.setVisible(true);
				}
				else {
					panelBancos.setVisible(true);
				}
				
			}
		});
		
		btnModificarUsuarioNombre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModificarUsuarioModificar.setVisible(false);
				panelModificarUsuarioModificarNombre.setVisible(true);
			}
		});
		
		btnCambiarPIN.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				if (Integer.valueOf(TFPinActual.getText())==TarjetaInsertada.getPIN()){ //Pin Correcto
					if (TFPinNuevo.getText().compareTo(TFPinConfirmar.getText())==0) {//Coinciden
						int auxPin=Integer.valueOf(TFPinNuevo.getText());
						int count=0;
						while (auxPin!=0) {
							auxPin=auxPin/10;
							count++;}
						if (count==4) { //Cantidad correcta de digitos
							TarjetaInsertada.setPIN(Integer.valueOf(TFPinNuevo.getText()));
							SerializarCajero.persistirCajero(cajero);
							JOptionPane.showMessageDialog(btnCambiarPIN,"Se ha cambiado el PIN");
							panelCambiarPIN.setVisible(false);
							if (BDLP) {
								panelBDLP.setVisible(true);
							}else {
								panelBancos.setVisible(true);
							}
						}
						else { //Cantidad incorrecta de digitos
							JOptionPane.showMessageDialog(btnCambiarClave,"ERROR: recuerde que debe ingresar 4 digitos");
							TFPinActual.setText(null);
							TFPinConfirmar.setText(null);
							TFPinNuevo.setText(null);
						}
					}else {//No coinciden
						JOptionPane.showMessageDialog(btnCambiarPIN,"Los PIN no coinciden, intentelo de nuevo");
						
					}
				}else {// Pin Incorrecto
					JOptionPane.showMessageDialog(btnCambiarPIN,"PIN Incorrecto, intente de nuevo");
				
				}
				}
				catch(java.lang.NumberFormatException nfex) {
					JOptionPane.showMessageDialog(btnCambiarClave,"ERROR: Recuerde que el PIN debe ser de 4 digitos");
					TFPinActual.setText(null);
					TFPinConfirmar.setText(null);
					TFPinNuevo.setText(null);
					
				}
				TFPinNuevo.setText(null);
				TFPinActual.setText(null);
				TFPinConfirmar.setText(null);
			}
		});
		
		panelSeleccionarCuenta.setVisible(false);
		panelSeleccionarCuenta.setOpaque(false);
		panelConsultaSaldo.setVisible(false);
		panelConsultaSaldo.setOpaque(false);
		panelMovimientos.setVisible(false);
		panelCambiarPIN.setVisible(false);
		panelCambiarPIN.setOpaque(false);
	
		
		
		btnCambiarClave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCambiarPIN.setVisible(true);
				panelBDLP.setVisible(false);
			}
		});
		btnConsultaSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBDLP.setVisible(false);
				panelConsultaSaldo.setVisible(true);
				taConsultaSaldo.setVisible(true);
				taConsultaSaldo.append("\n                ** Banco de la Plaza **\n");
				taConsultaSaldo.append("----------------------------------\n");
				taConsultaSaldo.append("\n   Saldo disponible: $"); 
				taConsultaSaldo.append(auxCuenta.getSaldo().toString());
				taConsultaSaldo.append("\n\n   Fecha: ");
				taConsultaSaldo.append(new Date().toString());
				taConsultaSaldo.append("\n\n----------------------------------\n");
				taConsultaSaldo.append("\n                          Gracias.");
			}
		});
		
		btnDepositarDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBDLP.setVisible(false);
				panelDepositarDinero.setVisible(true);
			}
		});
		
		btnRetirarDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBDLP.setVisible(false);
				panelRetirar.setVisible(true);
			}
		});
		
		btnCargaBilletero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelCargaBilletero.setVisible(true);
				PanelAdminOp.setVisible(false);
			}
		});
		
		btnAgregaTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAgregarTarjeta.setVisible(true);
				PanelAdminOp.setVisible(false);
				for (Iterator<Banco> i=cajero.getListaBancos().iterator();i.hasNext();) {
					cbBancosAgregarTarjeta.addItem("Banco "+i.next().getNombre().toString());
				}
			}
		});
		
		btnExitModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			panelModificarUsuarioModificar.setVisible(false);
			panelModificarUsuario.setVisible(true);
			}
		});
		
		
		
		btnConsultaMovimientos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBDLP.setVisible(false);
				panelMovimientos.setVisible(true);
				for (Iterator<Transaccion> i=auxCuenta.getListaTransacciones().iterator();i.hasNext();) {
					Transaccion next = i.next();
					taMovimientos.append(next.toString()+"\n");
				}
			}
		});
		
		btnSalirAdminOp.addActionListener(new ActionListener() {
			public void actionPerformed (ActionEvent e) {
				PanelAdminOp.setVisible(false);
				PanelAdminIngreso.setVisible(true);
			}
		});

		btnExitModificarUsuario1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModificarUsuario.setVisible(false);
				panelGestionarUsuario.setVisible(true);
				tfModificarUsuarioID.setText(null);
			}
		});

		btnModificarUsuarioModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!tfModificarUsuarioModificarApellido.getText().equals("")&&!tfModificarUsuarioModificarNombre.getText().equals("")) {
					usuarioAModificar.setApellido(tfModificarUsuarioModificarApellido.getText());
					usuarioAModificar.setNombre(tfModificarUsuarioModificarNombre.getText());
					serializables.SerializarCajero.persistirCajero(cajero);
					JOptionPane.showMessageDialog(btnModificarUsuarioModificar,"Usuario Modificado");
				}
				else {
					JOptionPane.showMessageDialog(btnModificarUsuarioModificar,"ERROR: Nombre o Apellido Invalido");
				}
			tfModificarUsuarioModificarApellido.setText(null);
			tfModificarUsuarioModificarNombre.setText(null);	
			}
		});
		
		btnAgregarUsuarioAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			int repetido=0;
			Iterator<Banco> i= cajero.getListaBancos().iterator();
			Banco banco=null;
			while(i.hasNext()&&(banco=i.next()).getNombre().compareTo("De la Plaza")!=0) {
			}
			try {
				for (Iterator<Usuario> it=banco.getListaUsuarios().iterator();it.hasNext();) {
					if (it.next().getID()==Integer.valueOf(tfAgregarUsuarioID.getText())) {
						repetido++;
					}
				}
				if (repetido==0) {
				banco.agregar(new Usuario(Integer.valueOf(tfAgregarUsuarioID.getText()),tfAgregarUsuarioApellido.getText(),tfAgregarUsuarioNombre.getText()));
				JOptionPane.showMessageDialog(btnAgregarUsuarioAgregar,"Usuario Agregado");
				serializables.SerializarCajero.persistirCajero(cajero);
				}
				else {
					JOptionPane.showMessageDialog(btnAgregarUsuarioAgregar,"ERROR: ID existente");
				}
				tfAgregarUsuarioApellido.setText(null);
				tfAgregarUsuarioNombre.setText(null);
				tfAgregarUsuarioID.setText(null);
			}
			catch(NullPointerException npexc){
				JOptionPane.showMessageDialog(btnAgregarUsuarioAgregar,npexc.toString());
			}
			catch(java.lang.NumberFormatException nfexc) {
				tfAgregarUsuarioApellido.setText(null);
				tfAgregarUsuarioID.setText(null);
				tfAgregarUsuarioNombre.setText(null);
				JOptionPane.showMessageDialog(btnAgregarUsuarioAgregar,"ERROR: Recuerde que debe ingresar los valores correspondientes");
			}
			}
		});
		
		btnDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Billetero auxBilletero;
				Billetero BilleteroARecargar=null;
				cajero.depositar(tfMontoDeposito, btnDepositar, BDLP, auxCuenta, cbTipoBilleteDeposito, taTicketDeposito, btnTicketDepositar,lblMontoDeposito,lblBilleteDeposito, btnExitDepositarDinero);
				if (cajero.isDepositado()) {
					for (Iterator<Billetero> i=cajero.getListaBilleteros().iterator();i.hasNext();) {
						if (((auxBilletero=(i.next())).getValorBillete().compareTo(BigDecimal.valueOf(Long.valueOf(cbTipoBilleteDeposito.getSelectedItem().toString())))==0 && auxBilletero.getTopeBillete()>=auxBilletero.getCantidad()+Integer.valueOf(tfMontoDeposito.getText()))) {
							BilleteroARecargar=auxBilletero;
						}
					} 
					BilleteroARecargar.recargar(new BigDecimal(cbTipoBilleteDeposito.getSelectedItem().toString()), cajero,Integer.valueOf(tfMontoDeposito.getText())/Integer.valueOf(cbTipoBillete.getSelectedItem().toString()));
					SerializarCajero.persistirCajero(cajero);
					tfMontoDeposito.setText(null);
				}
			}
		});
		
		btnExitModificarUsuarioModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelModificarUsuarioModificarNombre.setVisible(false);
				tfModificarUsuarioModificarApellido.setText(null);
				tfModificarUsuarioModificarNombre.setText(null);
				panelModificarUsuarioModificar.setVisible(true);
				lblModificarUsuarioUsuario.setText(usuarioAModificar.getNombre()+" "+usuarioAModificar.getApellido()+" "+usuarioAModificar.getID());
			}
		});
		
		btnTransferenciaDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBDLP.setVisible(false);
				panelTransferencias.setVisible(true);
			}
		});
		panelBDLP.setVisible(false);
		panelBDLP.setOpaque(false);
		
		btnCambiarClaveBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelCambiarPIN.setVisible(true);	
				panelBancos.setVisible(false);
				
			}
			
		});
		
		btnModificarUsuario1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			Iterator<Banco> ib=cajero.getListaBancos().iterator();
			Banco banco;
			while((banco=ib.next()).getNombre().compareTo("De la Plaza")!=0){
			}
			Iterator<Usuario> iu=banco.getListaUsuarios().iterator();
			try {
			while((usuarioAModificar=iu.next()).getID()!=Integer.valueOf(tfModificarUsuarioID.getText())) {
			}
			panelModificarUsuario.setVisible(false);
			panelModificarUsuarioModificar.setVisible(true);
			lblModificarUsuarioUsuario.setText(usuarioAModificar.getNombre()+" "+usuarioAModificar.getApellido()+" "+usuarioAModificar.getID());
			}
			catch(NoSuchElementException nseex) {
				tfModificarUsuarioID.setText(null);
				JOptionPane.showMessageDialog(btnModificarUsuario1,"ERROR: Usuario no encontrado");
			}
			catch(java.lang.NumberFormatException nfex) {
				tfModificarUsuarioID.setText(null);
				JOptionPane.showMessageDialog(btnModificarUsuario1,"ERROR: Recuerde que debe ingresar el ID");
			}
			tfModificarUsuarioID.setText(null);
			}
		});
		
		btnDescargaBilletero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PanelDescargaBilletero.setVisible(true);
				PanelAdminOp.setVisible(false);
			}
			
		});
		
		btnExitDepositarDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMontoDeposito.setText(null);
				panelDepositarDinero.setVisible(false);
				if (BDLP) {
					panelBDLP.setVisible(true);
				}
				else {
					panelBancos.setVisible(true);
				}
			}
		});
		
		btnExitBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBancos.setVisible(false);
				PanelIngreso.setVisible(true);
			}
		});
			
		btnTicketDepositar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taTicketDeposito.setText(null);
				btnTicketDepositar.setVisible(false);
				taTicketDeposito.setVisible(false);
				btnExitDepositarDinero.setVisible(true);
				btnDepositar.setVisible(true);
				tfMontoDeposito.setVisible(true);
				lblMontoDeposito.setVisible(true);
				lblBilleteDeposito.setVisible(true);
				cbTipoBilleteDeposito.setVisible(true);
			}
		});
		
		btnConsultaSaldoBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBancos.setVisible(false);
				panelConsultaSaldo.setVisible(true);
				panelConsultaSaldo.setVisible(true);
				taConsultaSaldo.setVisible(true);
				taConsultaSaldo.append("\n                ** Banco de la Plaza **\n");
				taConsultaSaldo.append("----------------------------------\n");
				taConsultaSaldo.append("\n   Saldo disponible: $"); 
				taConsultaSaldo.append(auxCuenta.getSaldo().toString());
				taConsultaSaldo.append("\n\n   Fecha: ");
				taConsultaSaldo.append(new Date().toString());
				taConsultaSaldo.append("\n\n----------------------------------\n");
				taConsultaSaldo.append("\n                          Gracias.");
					
			}
		});
			
		btnDepositarDineroBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBancos.setVisible(false);
				panelDepositarDinero.setVisible(true);				}
			});
		
		btnRetirarDineroBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBancos.setVisible(false);
				panelRetirar.setVisible(true);				}
			});
		
		btnExitTransferencias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelTransferencias.setVisible(false);
				if (BDLP) {
					panelBDLP.setVisible(true);
				}
				else {
					panelBancos.setVisible(true);					}
				}
		});
		
		btnTransferenciaDineroBancos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelBancos.setVisible(false);
				panelTransferencias.setVisible(true);
			}
		});
		
		btnExitAgregarTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelAgregarTarjeta.setVisible(false);
				PanelAdminOp.setVisible(true);
			}
		});
		
		btnExitGestionarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelGestionarUsuario.setVisible(false);
				PanelAdminOp.setVisible(true);
			}
		});
		panelBancos.setVisible(false);
		panelBancos.setOpaque(false);
		SerializarCajero.persistirCajero(cajero);
			

	}
	
	private Icon setIcono(String ubic, JButton boton) {
		
		ImageIcon icon = new ImageIcon(getClass().getResource(ubic));
		int ancho = boton.getWidth();
		int alto = boton.getHeight();
		
		ImageIcon icono = new ImageIcon(icon.getImage().getScaledInstance(ancho, alto, Image.SCALE_DEFAULT));
		return icono;
	}
	
	public Background getJContentPane(String url) {
		JContentPane = null;
		if(JContentPane == null) {
			JContentPane = new Background(url);
			JContentPane.setLayout(new BorderLayout());
		}
		return (Background) JContentPane;
		
	}
	
	
	public class Background extends JPanel {
		
		private static final long serialVersionUID = 1L;
		ImageIcon fondo;
		
		
		public Background(String nom) {
			super();
			fondo = new ImageIcon(getClass().getResource(nom));
			setSize(fondo.getIconWidth(),fondo.getIconHeight());
			
		}
		
		protected void paintComponent(Graphics g) {
			
			Dimension d = getSize();
			g.drawImage(fondo.getImage(),0,0,d.width,d.height,null);
			setOpaque(false);			
			super.paintComponent(g);
			
		}
		
		
	}
}
