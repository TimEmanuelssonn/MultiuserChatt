import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.*;

class ChatClient extends JFrame implements ActionListener {

    //Create UI with Jframe
    JPanel basePanel = new JPanel();
    JPanel menuPanel = new JPanel();
    JPanel chatPanel = new JPanel();
    JPanel chatInputPanel = new JPanel();
    JTextArea chat = new JTextArea(20,40);

    //Try get scroll to work.
    JScrollPane scroll = new JScrollPane(chat, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    JTextField chatInput = new JTextField(40);
    JButton connectButton = new JButton("Connect to chat");

    String userNameInput;
    Boolean connected = false;

    String message = "";
    String dataToSend = "";

    Socket addressSocket;
    ChatClientListener ccl;
    PrintWriter out;
    String hostName = "127.0.0.1";
    int portNumber = 12345;

    public ChatClient() throws UnknownHostException, SocketException, IOException, InterruptedException {

        //Get username input from user
        userNameInput = JOptionPane.showInputDialog(null, "Enter username");
        if (userNameInput == null || userNameInput.length() == 0) {
            System.exit(0);
        }

        //Setup UI layout
        basePanel.setLayout(new BorderLayout());
        menuPanel.setLayout(new BorderLayout());
        
        add(basePanel);
        basePanel.add(menuPanel, BorderLayout.NORTH);
        basePanel.add(chatPanel);
        basePanel.add(chatInputPanel, BorderLayout.SOUTH);
        menuPanel.add(connectButton);
        chatPanel.add(chat);
        chatInputPanel.add(chatInput);

        chat.setEditable(false);
        chatInput.setEditable(false);
        chatInput.addActionListener(this);
        connectButton.addActionListener(this);
        setTitle("Chat " + userNameInput);
        pack();
        setLocation(500,100);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        addressSocket = new Socket(hostName, portNumber);
        out = new PrintWriter(addressSocket.getOutputStream(), true);
    }

    public static void main(String[] args) throws UnknownHostException, SocketException, IOException, InterruptedException {
        ChatClient m = new ChatClient();
    }

    public void actionPerformed(ActionEvent ae) {
        //If user press connect button
        if(ae.getSource() == connectButton) {
            if(!connected) {
                try {
                    ccl = new ChatClientListener(addressSocket, chat);
                    ccl.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Change text on connect button and activate chatinput.
                connectButton.setText("Disconnet");
                chatInput.setEditable(true);
                
                //Send message "Connected" to chat and set connected to true.
                sendMessage("Connected");
                connected = true;

            } else {
                //Send message "Disconnected" to chat and set connected to false.
                sendMessage("Disconnected");
                connected = false;

                //Interrupt thread.
                ccl.interrupt();

                //Change text on connect button to connect to chat and set chatinput to false.
                connectButton.setText("Connect to chat");
                chatInput.setEditable(false);
            }
        }

        if(ae.getSource() == chatInput) {
            //Get input from chatinput and send it to sendMessage.
            message = chatInput.getText();
            sendMessage(message);
        }
    }
    
    public void sendMessage(String message) {
            dataToSend = userNameInput + ": " + message;
            out.println(dataToSend);
            chatInput.setText("");
    }
}