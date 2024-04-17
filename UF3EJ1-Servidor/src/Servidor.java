import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public void iniciar() {
        try {
            BufferedReader entradaTeclado = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Ingrese el puerto del servidor: ");
            int puerto = Integer.parseInt(entradaTeclado.readLine());
             System.out.println("La palabra clave para cerrar la conversacion es: Cleopatra ");
             
            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Iniciando servidor....OK");
            System.out.println("Esperando clientes...");

            Socket socket = serverSocket.accept();
            System.out.println("Cliente conectado desde " + socket.getInetAddress());

            BufferedReader entradaCliente = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salidaServidor = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader entradaServidor = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter salidaCliente = new PrintWriter(socket.getOutputStream(), true);

            String mensajeCliente;
            String mensajeServidor;

            while (true) {
                // Leemos el mensaje del cliente y lo mostramos
                if (entradaCliente.ready()) {
                    mensajeCliente = entradaCliente.readLine();
                    if (mensajeCliente != null) {
                        System.out.println("Cliente: " + mensajeCliente);
                        if (mensajeCliente.equalsIgnoreCase("Marc Antoni")) {
                             System.out.println("La palabra clave ha sido utilizada");
                             System.out.println("Closing chat....OK");
                             
                            break;
                        }
                    }
                }

                // Leemos el mensaje desde el servidor y lo enviamos al cliente
                if (entradaServidor.ready()) {
                    mensajeServidor = entradaServidor.readLine();
                    salidaServidor.println(mensajeServidor);
                    if (mensajeServidor.equalsIgnoreCase("Cleopatra")) {
                        System.out.println("La palabra clave ha sido utilizada");
                             System.out.println("Closing chat....OK");
                             
                        break;
                    }
                }
            }

            // Cerramos los recursos
            entradaCliente.close();
            salidaServidor.close();
            entradaServidor.close();
            salidaCliente.close();
            socket.close();
            serverSocket.close();

           System.out.println("Closing server....OK");
           System.out.println("Bye!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}