package cop2805;

import java.io.*;
import java.net.*;
import javax.swing.*;
public class FinalProjectClient extends FinalProjectGUI{

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                constructGUI();
            }
        });

    }


    public static long toSever(int FibNumber){
       String response = "0";
        try {
            //starts the connection to the server
            Socket socket = new Socket("127.0.0.1", 1236);
            PrintWriter pr = new PrintWriter(
                    new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            String FibPlace = String.valueOf(FibNumber);
            pr.println(FibPlace);
            pr.flush();

            response = br.readLine();
            System.out.println("Server calculate the value of the fibonacci number at place: " + FibPlace + " = " + response);
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Long.parseLong(response);
    }


}


