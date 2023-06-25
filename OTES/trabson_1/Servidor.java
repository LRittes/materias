package trabson_1;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Servidor {
    private static final int PORTA = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORTA);
            System.out.println("Servidor iniciado. Aguardando conexões...");

            while (true) {
                Socket socket = serverSocket.accept();
                String enderecoIP = socket.getInetAddress().getHostAddress();
                Date dataHoraConexao = new Date();
                logarConexao(enderecoIP, dataHoraConexao);

                System.out.println("Cliente conectado: " + enderecoIP);
                var thread = new ClienteHandler(socket);
                thread.start();

                var reader = new Scanner(System.in);
                var writer = new PrintWriter(socket.getOutputStream(), true);
                String mensagem;
                while (true) {
                    mensagem = reader.nextLine();
                    writer.println(mensagem);
                    // comando para fechar o servidor
                    if (mensagem.equals("exit")) {
                        break;
                    }
                }
                reader.close();
                break;
            }
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logarConexao(String enderecoIP, Date dataHoraConexao) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String log = "Cliente conectado: " + enderecoIP + " em " + dateFormat.format(dataHoraConexao) + "\n";
        try {
            FileWriter fileWriter = new FileWriter("log.txt", true);
            fileWriter.write(log);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClienteHandler extends Thread {
    private Socket socket;

    public ClienteHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String mensagem;
            do {
                mensagem = reader.readLine();
                System.out.println("Cliente: " + mensagem);
            } while (!mensagem.equals("!sair"));

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}