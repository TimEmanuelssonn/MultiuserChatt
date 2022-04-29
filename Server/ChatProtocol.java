public class ChatProtocol {

    private static final int BEFORE_CONNECTED = 0;
    private static final int CONNECTED = 1;
    private static final int DISCONNECTED = 2;

    private int state = BEFORE_CONNECTED;

    public String processInput(String theInput) {
        String theOutput = null;

        //Use split to splip string on : to get Disconnected
        if(theInput == "Disconnected") {
            System.out.println("Are we getting in here?");
            state = DISCONNECTED;
        }

        System.out.println("BEFORE CONNECTED");

        switch (state) {
            case BEFORE_CONNECTED:
                theOutput = theInput;
                state = CONNECTED;
                System.out.println("CONNECTED");
                break;
            case CONNECTED:
                theOutput = theInput;
                System.out.println("STILL CONNECTED");
                break;
            case DISCONNECTED:
                theOutput = theInput;
                state = BEFORE_CONNECTED;
                System.out.println("DISCONNECTED");
                break;
            default:
                break;
        }
        return theOutput;
    }
}
