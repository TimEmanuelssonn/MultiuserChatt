import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JTextArea;

public class ChatClientListener extends Thread {

    Socket clientSocket;
    JTextArea chat;
    String storedChat = "";
    BufferedReader in;
    
    public ChatClientListener(Socket clientSocket, JTextArea chat) throws IOException {
        this.clientSocket = clientSocket;
        this.chat = chat;

        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void run() {
        //Run while thread is not intrrupted
        while (!Thread.interrupted()) {
            String fromServer;
        
            try {
                if((fromServer = in.readLine()) != null) {
                    storedChat += fromServer + "\n";
                    chat.setText(storedChat);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
