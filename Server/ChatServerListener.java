import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class ChatServerListener {
    ChatListener listener = new ChatListener();

    public ChatServerListener() {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(12345)) {
                final Socket socketToClient = serverSocket.accept();
                MultiUserChatServer clientHandler = new MultiUserChatServer(socketToClient, listener);
                clientHandler.start();
                listener.setClientSocket(socketToClient);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        ChatServerListener c = new ChatServerListener();
    }
}