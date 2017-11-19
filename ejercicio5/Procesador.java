//Servicio online de citas
//(CC) Mario Lopez, Antonio Rodriguez, 2017)

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Random;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.io.PrintWriter;


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

		String datosRecibidos;


		// Array de bytes para enviar la respuesta. Podemos reservar memoria cuando vayamos a enviarka:
		String datosEnviar;
		String respuesta = null;
		String centro_seleccionado;
		String fecha_seleccionada;
		String hora_seleccionada;
		String user = "fr", pass = "finisterre";
		boolean autenticado, centro, fecha, hora;
		centro_seleccionado = fecha_seleccionada = hora_seleccionada = "";
		autenticado = centro = fecha = hora = false;
		try {
			try{

			// Obtiene los flujos de escritura/lectura
			inputStream=socketServicio.getInputStream();
			outputStream=socketServicio.getOutputStream();



			BufferedReader inReader = new BufferedReader(new InputStreamReader(inputStream));
			PrintWriter outPrinter = new PrintWriter(outputStream, true);

			//Lectura de login
			while(!autenticado){
				datosRecibidos = inReader.readLine();

				if(datosRecibidos.equals(user+"-"+pass)){
					autenticado = true;
					respuesta = "Autenticado. \nSelecciona centro: PTS, ETSIIT, ETSA, Facultad de Ciencias de la Educación: ";
				}else{
					respuesta = "ERROR/No autenticado";
				}

				outPrinter.println(respuesta);

			}

			////////////////////////////////////////////////////////

			//Recepcion del centro
			if(autenticado){
				while(!centro){
					datosRecibidos = inReader.readLine();
					centro_seleccionado = datosRecibidos;
					respuesta = "ERROR/Centro incorrecto.";

					if(centro_seleccionado.equals("PTS")){
						respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en PTS: 10/11/2017, 13/11/2017";
						centro = true;
					}
					if(centro_seleccionado.equals("ETSIIT")){
						respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en ETSIIT: 25/12/2017";
						centro = true;

					}
					if(centro_seleccionado.equals("ETSA")){
						respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en ETSA: 10/11/2017, 13/11/2017, 14/11/2017";
						centro = true;
					}
					if(centro_seleccionado.equals("Facultad de Ciencias de la Educacion")){
						respuesta = "Centro seleccionado: " + centro_seleccionado + ". Fechas disponibles en Facultad de Ciencias de la Educacion: 10/11/2017";
						centro = true;
					}

				outPrinter.println(respuesta);
				}
			}


			//Seleccion de la fecha
			if(autenticado && centro){

				while(!fecha){
					datosRecibidos = inReader.readLine();
					fecha_seleccionada = datosRecibidos;
					respuesta = "ERROR/Fecha incorrecta.";


					if(centro_seleccionado.equals("PTS")){
						if(datosRecibidos.equals("10/11/2017"))
							respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 10:00,10:30,12:15";
						if(datosRecibidos.equals("13/11/2017"))
							respuesta = "Fecha seleccionada: " + fecha_seleccionada + ". Horas disponibles: 10:00,10:30,12:15";
						fecha=true;
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
			}

			//Seleccion de horas
			if(autenticado && centro && fecha){
				while(!hora){
					datosRecibidos = inReader.readLine();
					hora_seleccionada = datosRecibidos;
					respuesta = "ERROR/Hora incorrecta -> "+ datosRecibidos;

					if(centro_seleccionado.equals("PTS")){
						if(datosRecibidos.equals("10:00")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
						if(datosRecibidos.equals("10:30")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
						if(datosRecibidos.equals("12:15")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
					}

					if(centro_seleccionado.equals("ETSIIT")){
						if(datosRecibidos.equals("13:30")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
					}

					if(centro_seleccionado.equals("ETSA")){
						if(datosRecibidos.equals("11:15")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
						if(datosRecibidos.equals("11:30")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
						if(datosRecibidos.equals("12:00")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
					}

					if(centro_seleccionado.equals("Facultad de Ciencias de la Educacion")){
						if(datosRecibidos.equals("09:00")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
						if(datosRecibidos.equals("09:30")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
						if(datosRecibidos.equals("11:00")){
							respuesta = "Hora seleccionada: " + hora_seleccionada;
							hora = true;
						}
					}
					outPrinter.println(respuesta);
				}
			}

				if(autenticado && centro && fecha && hora){
					respuesta = "Su cita es en el centro: " + centro_seleccionado + " el día: " + fecha_seleccionada + " a las: " +hora_seleccionada;
					outPrinter.println(respuesta);
				}

			} catch (NullPointerException e){
					System.out.print("Logout correcto. Hasta luego\n");
			}
		} catch (IOException e) {
			System.err.println("Error al obtener los flujos de entrada/salida.");
		}

	}


}
