import java.io.*;
import java.net.Socket;

public class Cliente {

    public void iniciar() {
        try {
            BufferedReader entradaTeclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese el puerto del servidor: ");
           
            int puerto = Integer.parseInt(entradaTeclado.readLine());
             System.out.println("La palabra clave para cerrar la conversacion es: Marc Antoni ");
            Socket socket = new Socket("localhost", puerto);
            System.out.println("Conectado al servidor......OK");

            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salidaServidor = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter salidaCliente = new PrintWriter(socket.getOutputStream(), true);

            String mensaje;

            while (true) {
                // Leemos el mensaje desde el servidor y lo mostramos
                if (entradaServidor.ready()) {
                    String mensajeServidor = entradaServidor.readLine();
                    if (mensajeServidor != null) {
                        System.out.println("Servidor: " + mensajeServidor);
                        if (mensajeServidor.equalsIgnoreCase("Cleopatra")) {
                            System.out.println("La palabra clave ha sido utilizada");
                             System.out.println("Closing chat....OK");
                            
                            break;
                        }
                    }
                }

                // Leemos el mensaje del cliente y lo enviamos al servidor
                if (entradaCliente.ready()) {
                    mensaje = entradaCliente.readLine();
                    salidaServidor.println(mensaje);
                    if (mensaje.equalsIgnoreCase("Marc Antoni")) {
                        System.out.println("La palabra clave ha sido utilizada");
                             System.out.println("Closing chat....OK");
                            
                        break;
                    }
                }
            }

            // Cerramos los recursos
            entradaServidor.close();
            salidaServidor.close();
            entradaCliente.close();
            salidaCliente.close();
            socket.close();

            System.out.println("Closing client....OK");
           System.out.println("Bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }}