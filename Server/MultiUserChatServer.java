import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class MultiUserChatServer extends Thread {
    Socket clientSocket;
    ChatListener listener;
    
    public MultiUserChatServer(Socket clientSocket, ChatListener listener) throws IOException {
        this.clientSocket = clientSocket;
        this.listener = listener;
    }

    @Override
    public void run() {
            String inputLine;

            try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            ) {

                while ((inputLine = in.readLine()) != null) {
                    listener.sendToServers(inputLine);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
