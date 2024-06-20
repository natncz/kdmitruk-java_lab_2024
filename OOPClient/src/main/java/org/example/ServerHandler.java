package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerHandler {

    String hostName;
    int port;
    Socket socket;
    Scanner input;
    PrintWriter output;

    public ServerHandler(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
        try {
            socket = new Socket(hostName,port);
            input = new Scanner(socket.getInputStream());
            output = new PrintWriter(socket.getOutputStream(),true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void send(String message){
    output.println(message);
    }
    public String read(){
        if(input.hasNextLine()){
            return input.nextLine();
        }
        return null;
    }
    public void close(){
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
