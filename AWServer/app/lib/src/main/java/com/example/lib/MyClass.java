package com.example.lib;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
            System.out.println("Waiting... ...");
            client_1 = server.accept();
            client_2 = server.accept();
            sendMessage(client_1,"Matched");
            sendMessage(client_2,"Matched");
            System.out.println("Successfully Connect!");
            new Thread(() -> passScoreLeft2Right(client_1,client_2)).start();
            new Thread(() -> passScoreLeft2Right(client_2,client_1)).start();
            if (client_1.isClosed() || client_2.isClosed()) System.out.println("One Socket closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void sendMessage(Socket socket,String msg) {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)), true);
            out.println(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void passScoreLeft2Right(Socket left,Socket right) {
        while (true) {
            try {
                String msg;
                BufferedReader in = new BufferedReader(new InputStreamReader(left.getInputStream(),StandardCharsets.UTF_8));
                if ((msg = in.readLine()) != null) {
                    System.out.println(msg);
                    sendMessage(right,msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}