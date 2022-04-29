public class Chat {
    String chat = "";
    String timestamp;
    String tempChat;

    public Chat() {}

    public void setChat(String message) {
        timestamp = new java.text.SimpleDateFormat("HH:mm:ss").format(new java.util.Date());
        tempChat = chat;
        chat = timestamp + " " + message;
        chat = tempChat + chat;
        System.out.println(chat);
    }

    public String getChat() {
        return chat;
    }
}
