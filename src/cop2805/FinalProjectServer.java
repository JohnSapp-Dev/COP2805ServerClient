package cop2805;

import java.io.*;
import java.net.*;

public class FinalProjectServer
{
    public static void main(String[] args) {
        boolean shutdown = false;
        try {
            ServerSocket server = new ServerSocket(1236);
            while (!shutdown){
                System.out.println("Waiting for client...");
                Socket client = server.accept();
                // gets input from the client
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(client.getInputStream()));
                String input = br.readLine();
                System.out.println("Client wants to calculate the "+input+" place in the Fibonacci sequence");
                // converts the string to int and send the value to the method
                long calculated = fibonacci(Integer.valueOf(input));

                String response = String.valueOf(calculated)+"\n";
                PrintWriter pr = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
                // sends the result of the fibonacci method to the client.
                pr.println(response);
                pr.flush();

                client.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static long fibonacci(int n){
        long num1 = 0;
        long num2 = 1;
        long num3 = 0;
        for(int i=2;i<=n;i++){
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
        }
        return num3;
    }
}