import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public Runnable getRunnable(){
        return new Runnable(){
            @Override
            public void run(){
                int port = 8010;
                try{
                    InetAddress address = InetAddress.getByName("localhost");
                    Socket socket = new Socket(address, port);
                    try{
                        PrintWriter toScoket = new PrintWriter(socket.getOutputStream());
                        BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        toScoket.println("Hello from client");
                        String line = fromSocket.readLine();
                        System.out.println("Response from the socket is: "+line);
                        
                    }catch(IOException ex){
                        ex.printStackTrace();
                    }
                
                }catch(IOException ex){
                    ex.printStackTrace();
                }
                
            }
        };
    }

    public static void main(String[] args){
        Client client = new Client();
        try{
            for(int i=0; i<100; i++){
                Thread thread = new Thread(client.getRunnable());
                thread.start();
            }
        }catch(Exception ex){
            return;
        }
    }
}
