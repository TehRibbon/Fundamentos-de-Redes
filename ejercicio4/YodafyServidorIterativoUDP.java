import java.io.IOException;
import java.net.DatagramSocket;		
import java.net.DatagramPacket;		
import java.net.InetAddress;
import java.util.Random;

//
// YodafyServidorIterativo
// (CC) jjramos, 2012
//
public class YodafyServidorIterativo {

	public static void main(String[] args) {

		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		byte []buferEnvio=new byte[256];	
		byte []buferRecepcion=new byte[256];
		
                int puerto;
                InetAddress direccion;
                DatagramPacket paquete;
                DatagramPacket paqueteEnviado;
                DatagramSocket socketServidor;
                String yodificado;
                
                

		try {
			//Socket para la comunicacion, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
			socketServidor = new DatagramSocket(port);
			//////////////////////////////////////////////////
                } catch (IOException e) {
                        System.out.println("Error: no se pudo atender en el puerto ");
                }
			// Mientras ... siempre!
			do {
                            
                                paquete = new DatagramPacket(buferRecepcion, buferRecepcion.length);
                                
                                socketServidor.receive(paquete);
			                                        
                                yodificado = new String(paquete.getData());
                                                                
                                // Yoda reinterpreta el mensaje:
			        String respuesta = yodaDo(yodificado);
                                           
                                
			        // Convertimos el String de respuesta en una array de bytes:
			        buferEnvio = respuesta.getBytes();
                                
                                direccion = paquete.getAddress();
                                puerto = paquete.getPort();
                                
                                // Se crea un nuevo paquete con el mensaje yodificado
                                paqueteEnviado = new DatagramPacket(buferEnvio,buferEnvio.length,direccion, puerto);

                                // Se envia el nuevo paquete con el mensaje yodificado incluido
            
                                 socketServidor.send(paqueteEnviado);
                                                                
				

			} while (true);

		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}
                
                
                //Hacemos la funcion de yoda aqui
                private static String yodaDo(String peticion) {
                
                Random random = new Random();
		// Desordenamos las palabras:
		String[] s = peticion.split(" ");
		String resultado="";
                

		for(int i=0;i<s.length;i++){
			int j=random.nextInt(s.length);
			int k=random.nextInt(s.length);
			String tmp=s[j];

			s[j]=s[k];
			s[k]=tmp;
		}

		resultado=s[0];
		for(int i=1;i<s.length;i++){
		  resultado+=" "+s[i];
		}

		return resultado;
	}
                
                
                

	

}
