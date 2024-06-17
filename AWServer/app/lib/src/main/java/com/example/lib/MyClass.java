package com.example.lib;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class MyClass {
    private ServerSocket server;
    private Socket client_1;
    private Socket client_2;
    public static void main(String[] args){
        new MyClass();
    }

    public MyClass(){
        try {
            InetAddress addr = InetAddress.getLocalHost();
            System.out.println("--local host: " + addr + "--");
            doInBackground();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    protected void doInBackground() {
        try {
            server = new ServerSocket(9999);
            // Waiting for connection
            System.out.println("Waiting For Connection...");
            client_1 = server.accept();
            System.out.println("First Connection Success!");
            client_2 = server.accept();
            // Send Matched
            sendMatchedMessage(client_1);
            sendMatchedMessage(client_2);
            System.out.println("Successfully Connect!");
            while (true) {
                // TODO : exchange score between 2 clients
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMatchedMessage(Socket socket) {
        try {
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
            out.println("Matched");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}