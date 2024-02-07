package sg.edu.nus.iss.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientApp {
    public static void main(String[] args) {
        System.out.println("ClientApp.main()");
        String[] connInfo = args[0].split(":");
        System.out.println(connInfo[0] + " " + connInfo[1]);
        InputStream is = null;

        try {
            while (true) {
                Socket sock = new Socket(connInfo[0], Integer.parseInt(connInfo[1]));
                is = sock.getInputStream();
                // put inside loop so that it doesn't keep reusing "cached" cookie / datastream
                // won't get cut off halfway
                DataInputStream dis = new DataInputStream(is);

                OutputStream os = sock.getOutputStream();
                DataOutputStream dos = new DataOutputStream(os);

                Console cons = System.console();

                String input = cons.readLine("Server command to the server: ");
                dos.writeUTF(input);
                dos.flush();

                String response = dis.readUTF();
                if (response.contains("cookie-text")) {
                    String[] arrRes = response.split("_");
                    System.out.println("Cookie from server: " + arrRes[1]);
                } else {
                    System.err.println(response);
                }

                // need to close here instead of in finally section
                is.close();
                os.close();
                // sock.close();    // check why intructor did not need to close this
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }

    }

}