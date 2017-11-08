//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.net.DatagramSocket;		
import java.net.DatagramPacket;		
import java.net.InetAddress;


public class YodafyClienteUDP {

	public static void main(String[] args) {

		byte []buferEnvio=new byte[256];	
		byte []buferRecepcion=new byte[256];
		                
                // Nombre del host donde se ejecuta el servidor:
		String host="localhost";

		// Puerto en el que espera el servidor:
		int port=8989;
                
                InetAddress direccion;
                DatagramPacket paquete;
                DatagramPacket paqueteRecibido;
                DatagramSocket socket;
                String yodificado;
                
                
                
               

		

		try {
                        //busca la direcio IP del servidor
                        direccion = InetAddress.getByName(host);
                        
                        buferEnvio="Al monte del volcán debes ir sin demora".getBytes();
                        //crea un datagrama con todos los datos---
                        paquete = new DatagramPacket(buferEnvio, buferEnvio.length, direccion, port);
                        //---para enviarlo por el datagramsocket
                        socket.send(paquete);
                        
                        //Se recibira el mensaje yodificado
                        paqueteRecibido = new DatagramPacket(buferRecepcion, buferRecepcion.length);
                        socket.receive(paqueteRecibido);
                        
                        //paqueteRecibido.getData();
                        //paqueteRecibido.getAddress();
                        //paqueteRecibido.getPort();
                        
                        yodificado = new String(paqueteRecibido.getData());
                                                
			// MOstremos la cadena de caracteres recibidos:
			System.out.println("Recibido: ");
			System.out.print(yodificado);
			System.out.println("\n");

			// Una vez terminado el servicio, cerramos el socket (automáticamente se cierran
			// el inpuStream  y el outputStream)
			//////////////////////////////////////////////////////
			socket.close();
			//////////////////////////////////////////////////////

			// Excepciones:
		} catch (UnknownHostException e) {
			System.err.println("Error: Nombre de host no encontrado.");
		} catch (IOException e) {
			System.err.println("Error de entrada/salida al abrir el socket.");
		}
	}
}
