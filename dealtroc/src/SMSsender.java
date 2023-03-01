import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSsender {

  // Your Twilio account SID and auth token
  public static final String ACCOUNT_SID = "ACdecd415a5f91bbc7debfdbe0ecf5cd81";
  public static final String AUTH_TOKEN = "22b458e4a8814776abdb9e7165042761";

  public static void sendSMS(){

    // Initialize the Twilio client
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    // Send a message
    Message message = Message.creator(
            new PhoneNumber("+1234567890"),  // Replace with the phone number you want to send a message to
            new PhoneNumber("+1987654321"),  // Replace with your Twilio phone number
            "Hello, World!")  // Replace with the content of the message you want to send
        .create();

    // Print the message SID
    System.out.println(message.getSid());
  }
}
