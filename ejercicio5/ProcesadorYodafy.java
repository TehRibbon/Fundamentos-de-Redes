
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.io.PrintWriter;


//
// Nota: si esta clase extendiera la clase Thread, y el procesamiento lo hiciera el método "run()",
// ¡Podríamos realizar un procesado concurrente!
//
public class Procesador {
	// Referencia a un socket para enviar/recibir las peticiones/respuestas
	private Socket socketServicio;
	// stream de lectura (por aquí se recibe lo que envía el cliente)
	private InputStream inputStream;
	// stream de escritura (por aquí se envía los datos al cliente)
	private OutputStream outputStream;

	// Para que la respuesta sea siempre diferente, usamos un generador de números aleatorios.
	private Random random;

	// Constructor que tiene como parámetro una referencia al socket abierto en por otra clase
	public Procesador(Socket socketServicio) {
		this.socketServicio=socketServicio;
		random=new Random();
	}


	// Aquí es donde se realiza el procesamiento realmente:
	void procesa(){

		// Como máximo leeremos un bloque de 1024 bytes. Esto se puede modificar.
		//byte [] datosRecibidos=new byte[1024];
		String datosRecibidos;
		//int bytesRecibidos=0;

		// Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
		String datosEnviar;
		String respuesta;
		String centro_seleccionado;
		String fecha_seleccionada;
		String hora_seleccionada;

		boolean autenticado, centro, fecha, hora;
		centro_seleccionado = fecha_seleccionada = hora_seleccionada = "";
		autenticado = centro = fecha = hora = false;
		try {
			// Obtiene los flujos de escritura/lectura
			inputStream=socketServicio.getInputStream();
			outputStream=socketServicio.getOutputStream();

			// Lee la frase a Yodaficar:
			////////////////////////////////////////////////////////
			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
			datosRecibidos = inReader.readLine();
			//inputStream.read(datosRecibidos);
			// read ... datosRecibidos.. (Completar)
			////////////////////////////////////////////////////////

			// Yoda hace su magia:
			// Creamos un String a partir de un array de bytes de tamaño "bytesRecibidos":
			//String peticion=new String(datosRecibidos,0,datosRecibidos.length;
			// Yoda reinterpreta el mensaje:


			if(datosRecibidos.equals("usuario-1234")){
				autenticado = true;
				respuesta = "Autenticado. \nSelecciona centro: PTS, ETSIIT, ETSA, Facultad de Ciencias de la Educación: ";
			}else{
				respuesta = "ERROR/No autenticado";
			}
			//String respuesta=yodaDo(datosRecibidos);
			// Convertimos el String de respuesta en una array de bytes:
			//datosEnviar=respuesta.getBytes();


			// Enviamos la traducción de Yoda:
			////////////////////////////////////////////////////////
			PrintWriter outPrinter = new PrintWriter(outputStream, true);
      outPrinter.println(respuesta);

			// ... write ... datosEnviar... datosEnviar.length ... (Completar)
			////////////////////////////////////////////////////////

			//Recepcion del centro
			if(autenticado){
				datosRecibidos = inReader.readLine();
				centro_seleccionado = datosRecibidos;
				respuesta = "Centro incorrecto.";

				if(centro_seleccionado.equals("PTS")){
					respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en PTS: 10/11/2017, 13/11/2017";
					centro = true;
				}
				if(centro_seleccionado.equals("ETSIIT")){
					respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en ETSIIT: 25/12/2017";
					centro = true;

				}
				if(centro_seleccionado.equals("ETSA"))
					respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en ETSA: 10/11/2017, 13/11/2017, 14/11/2017";

				if(centro_seleccionado.equals("Facultad de Ciencias de la Educacion"))
					respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en Facultad de Ciencias de la Educacion: 10/11/2017";

			outPrinter.println(respuesta);
			}


			//Seleccion de la fecha

			if(autenticado && centro){
				datosRecibidos = inReader.readLine();
				fecha_seleccionada = datosRecibidos;
				respuesta = "Fecha incorrecta.";


				if(centro_seleccionado.equals("PTS")){
					if(datosRecibidos.equals("10/11/2017"))
						respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 10:00,10:30,12:15";
					if(datosRecibidos.equals("13/11/2017"))
						respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 10:00,10:30,12:15";
				}

				if(centro_seleccionado.equals("ETSIIT")){
					if(datosRecibidos.equals("25/12/2017")){
						respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 13:30";
						fecha = true;
					}
				}

				if(centro_seleccionado.equals("ETSA")){
					if(datosRecibidos.equals("10/11/2017")){
						respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 11:15, 11:30, 12:00";
						fecha = true;
					}
					if(datosRecibidos.equals("13/11/2017")){
						respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 11:15, 11:30, 12:00";
						fecha = true;
					}
					if(datosRecibidos.equals("14/11/2017")){
						respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 11:15, 11:30, 12:00";
						fecha = true;
					}
				}

				if(centro_seleccionado.equals("Facultad de Ciencias de la Educacion")){
					if(datosRecibidos.equals("10/11/2017")){
						respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 09:00,09:30,11:00";
						fecha = true;
					}
				}


				outPrinter.println(respuesta);
			}

			//Seleccion de horas
			if(autenticado && centro && fecha){
				datosRecibidos = inReader.readLine();
				hora_seleccionada = datosRecibidos;
				respuesta = "Hora incorrecta";

				if(centro_seleccionado.equals("ETSIIT")){
					if(datosRecibidos.equals("13:30")){
						respuesta = "Hora seleccionada: " + hora_seleccionada;
						hora = true;
					}
				}

				outPrinter.println(respuesta);
			}

				if(autenticado && centro && fecha && hora){
					respuesta = "Su cita es en el centro: " + centro_seleccionado + " el día: " + fecha_seleccionada + " a las: " +hora_seleccionada;
					outPrinter.println(respuesta);
				}

		} catch (IOException e) {
			System.err.println("Error al obtener los flujos de entrada/salida.");
		}

	}

	// Yoda interpreta una frase y la devuelve en su "dialecto":
	private String yodaDo(String peticion) {
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
