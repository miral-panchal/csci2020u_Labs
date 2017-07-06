package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
/**
 * Created by miral on 31/03/17.
 */
public class ChatClientHandler implements Runnable {
    private Controller serverController = null;
    private Socket socket = null;
    private boolean keepRunning = true;

    public ChatClientHandler(Controller serverController, Socket socket) {
        this.serverController = serverController;
        this.socket = socket;
    }

    public void stop() {
        this.keepRunning = false;
    }

    public void sendMessage(String message) {
        try {
            PrintWriter out = new PrintWriter(this.socket.getOutputStream());
            out.println(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println(Thread.currentThread().getName() + ": Listening for client data");
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String line = null;
            while (this.keepRunning) {
                System.out.println("Reading a line of text.");
                line = in.readLine();
                System.out.println("MESSAGE:" + line);
                this.serverController.addMessage(line);
            }
            System.out.println("Quitting");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

