import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatListener {

    List<Socket> clientSocketList = new ArrayList<>();

    public ChatListener() {}

    public void setClientSocket(Socket clientSocket) {
        clientSocketList.add(clientSocket);
    }

    public void sendToServers(String message) {
        try {
            for(int i = 0; i < clientSocketList.size(); i++) {
                PrintWriter out = new PrintWriter(clientSocketList.get(i).getOutputStream(), true);
                out.println(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
