import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

//
// YodafyServidorConcurrente
// (CC) jjramos, 2012
//
public class YodafyServidorConcurrente {

	public static void main(String[] args) {

		// Puerto de escucha
		int port=8989;
		// array de bytes auxiliar para recibir o enviar datos.
		//byte []buffer=new byte[256];
		// Número de bytes leídos
		//int bytesLeidos=0;

		//Creo ServerSocket modo pasivo
		ServerSocket socketServidor;
		Socket socketServicio = null;

		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			//////////////////////////////////////////////////
				socketServidor = new ServerSocket(port);
			// ...serverSocket=... (completar)
			//////////////////////////////////////////////////

			// Mientras ... siempre!
			do {

				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				socketServicio = socketServidor.accept();
				// socketServicio=... (completar)
				//////////////////////////////////////////////////

				// Creamos un objeto de la clase ProcesadorYodafy, pasándole como
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				ProcesadorYodafy procesador=new ProcesadorYodafy(socketServicio);
				procesador.hebras();

			} while (true);

		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
