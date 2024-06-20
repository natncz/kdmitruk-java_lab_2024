package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private ServerSocket serverSocket;
    private Map<String, ClientHandler> clients = new HashMap<>();

    public void addClient(String login, ClientHandler clientHandler){
        clients.put(login, clientHandler);
    }

    public void removeClient(String login){
        clients.remove(login);
    }

    public void printClients(){
        System.out.println(clients.keySet());
    }

    public void broadcast(String message){
        for (ClientHandler c:clients.values()
             ) {
            c.send(message);
        }
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true){
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
