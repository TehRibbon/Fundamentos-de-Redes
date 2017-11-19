//Servicio online de citas
//(CC) Mario Lopez, Antonio Rodriguez, 2017)


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class CitasServidorIterativo {

	public static void main(String[] args) {

		// Puerto de escucha
		int port=8989;

		//Creo ServerSocket modo pasivo
		ServerSocket socketServidor;
		Socket socketServicio = null;

		try {
			// Abrimos el socket en modo pasivo, escuchando el en puerto indicado por "port"
			socketServidor = new ServerSocket(port);

			do {

				// Aceptamos una nueva conexión con accept()
				/////////////////////////////////////////////////
				socketServicio = socketServidor.accept();
				// socketServicio=... (completar)
				//////////////////////////////////////////////////

				// Creamos un objeto de la clase Procesador, pasándole como
				// argumento el nuevo socket, para que realice el procesamiento
				// Este esquema permite que se puedan usar hebras más fácilmente.
				Procesador procesador=new Procesador(socketServicio);
				procesador.procesa();

			} while (true);

		} catch (IOException e) {
			System.err.println("Error al escuchar en el puerto "+port);
		}

	}

}
