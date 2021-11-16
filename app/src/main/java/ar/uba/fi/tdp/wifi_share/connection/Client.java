package ar.uba.fi.tdp.wifi_share.connection;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    private Socket socket;

    public Client() {

        byte[] ipAddr = new byte[] {10, 22, 5, 57};
        InetAddress address = null;
        try {
            address = InetAddress.getByAddress(ipAddr);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

            new Thread(new Runnable() {

                public void run() {

                    InetAddress address2 = null;

                    try {
                        address2 = InetAddress.getByName(new String("vaio"));
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }

                    Socket socket2 = null;

                    try {
                        System.out.println("Conecto a vaio");
                        //Socket socket = new Socket("10.22.5.113", 2222);
                        socket2 = new Socket(address2, 2222);

                        OutputStream out = socket2.getOutputStream();
                        PrintWriter output = new PrintWriter(out, true);

                        output.println("Hello from Android".toCharArray());


                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            socket2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }



                }
            }).start();

        int a = 1;
    }

}
