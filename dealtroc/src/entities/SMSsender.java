/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author Eya
 */
public class SMSsender {

  // Your Twilio account SID and auth token
  public static final String ACCOUNT_SID = "ACdecd415a5f91bbc7debfdbe0ecf5cd81";
  public static final String AUTH_TOKEN = "22b458e4a8814776abdb9e7165042761";

  public static void sendSMS(){

    // Initialize the Twilio client
    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

    //message
    Message message = Message.creator(
            new PhoneNumber("+21695816137"),  // Remplacer avec le numéro de téléphone de destination
            new PhoneNumber("+12766378349"),  // Remplacer avec votre numéro de téléphone Twilio
            "someone commented your article")
        .create();
    // verif message 
    System.out.println("message envoyer");
  }
}
  
