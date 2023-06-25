package trabson_1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente1 {
    private static final String HOST = "localhost";
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(HOST, PORTA);
            var reader = new Scanner(System.in);
            var writer = new PrintWriter(socket.getOutputStream(), true);

            Thread thread = new ServidorHandler(socket);
            thread.start();

            String mensagem;
            do {
                mensagem = reader.nextLine();
                writer.println(mensagem);
            } while (!mensagem.equals("!sair"));
            reader.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServidorHandler extends Thread {
    private Socket socket;

    public ServidorHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            var reader = new Scanner(socket.getInputStream());

            while (reader.hasNextLine()) {
                System.out.println("Servidor: " + reader.nextLine());
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
