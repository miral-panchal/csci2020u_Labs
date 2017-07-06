package Server;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by miral on 31/03/17.
 */
public class Controller {
    @FXML private TextArea messages;

    private List<ChatClientHandler> clientHandlers;
    private boolean keepGoing = true;
    private ServerSocket serverSocket = null;
    private NetworkHandler listener = null;

    public void initialize() {
        this.clientHandlers = new ArrayList<>();
        try {
            this.serverSocket = new ServerSocket(8001);
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener = new NetworkHandler(this, this.serverSocket);
        Thread listenerThread = new Thread(listener);
        listenerThread.start();
    }

    public void addClientHandler(ChatClientHandler newHandler) {
        this.clientHandlers.add(newHandler);
    }

    public void addMessage(String message) {
        if (message != null) {
            this.messages.appendText(message + "\n");
            System.out.println("Added message: " + message);
        }
    }

    public void exit() {
        // stop all of the client handler threads
        for (ChatClientHandler handler: this.clientHandlers) {
            handler.stop();
        }

        // close the server socket and listener
        try {
            this.listener.stop();
            this.serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // exit
        System.exit(0);
    }
}