package serializables;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import principal.ATM;

public class SerializarCajero {
	
	
	
	public static void persistirCajero(ATM cajero) {
		try {
	           ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("datosCajero.dat"));
	           oos.writeObject(cajero);
	           oos.flush();
	           oos.close();
	       } catch (Exception e) {
	           System.out.println("Excepcion durante la serialización: " + e);
	           System.exit(0);
	       }		
	}

	public static ATM instanciarCajero() {
         ATM cajero = null;
		 try {
	            //abre el archivo
	            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("datosCajero.dat"));
	            //lee el objeto del archivo
	            cajero = (ATM) ois.readObject();
	            ois.close();
	            
	      } catch (Exception e) {
	            System.out.println("Excepcion en la deserializacion: " + e);
	            System.exit(0);
	      }
		  return cajero;
	}
}