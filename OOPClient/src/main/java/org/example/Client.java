package org.example;

import java.util.Scanner;

public class Client {

    public void start(){
        ServerHandler serverHandler = new ServerHandler("localhost", 8020);
        Thread threadR = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    String read = serverHandler.read();
                    if(read == null) break;
                    System.out.println(read);
                }
            }
        });
        threadR.start();
        Thread threadW = new Thread(new Runnable() {
            @Override
            public void run() {
                Scanner scanner = new Scanner(System.in);
                while(true){
                    String message = scanner.nextLine();
                    serverHandler.send(message);
                    if (message.equalsIgnoreCase("bye")){
                        serverHandler.close();
                        break;
                    }
                }
            }
        });
        threadW.start();
    }

}
