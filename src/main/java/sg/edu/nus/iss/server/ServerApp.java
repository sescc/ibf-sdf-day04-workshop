package sg.edu.nus.iss.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {
    public static void main(String[] arg) {
        System.out.println("ServerApp.main()");
        Socket socket = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            String serverPort = arg[0];
            String cookieFile = arg[1];
            System.out.println("" + serverPort + " " + cookieFile);

            // create a server
            ServerSocket serverSocket = new ServerSocket(Integer.parseInt(serverPort));

            while (true) {
                // Waiting for client
                // accept connections
                socket = serverSocket.accept();
                // get data client programme as input in bytes
                is = socket.getInputStream();
                DataInputStream dis = new DataInputStream(is);

                os = socket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                while (true) {
                    // received command from client
                    try {
                        String dataFromClient = dis.readUTF();
                        if (dataFromClient.equals("get-cookie")) {
                            // TODO
                            dos.writeUTF("cookie-text_cookie");
                        } else {
                            dos.writeUTF("Invalid command.");
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.err.println("Client disconnected due to " + e.getMessage());
                        socket.close(); // if don't close and allow client to resume,
                                        // server will crash due to infinite while loop and connection still active
                                        // (still expecting new datastream from client); but client indirectly still
                                        // holding a connection and won't be able to get-cookie a 2nd time
                        break;  // go back to the listening while loop, else client stays on the API all the time and not reconnect
                    }

                }

            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
