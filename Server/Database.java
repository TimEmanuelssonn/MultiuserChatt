public class Database {
    private Chat chat = new Chat();

    public Database() {}

    public String getChat() {
        return chat.getChat();
    }

    public void setChat(String message) {
        chat.setChat(message);
    }
}
