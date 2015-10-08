/**
 * 
 */
package project.txtMessage;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.SmsFactory;
import com.twilio.sdk.resource.instance.Account;
import com.twilio.sdk.resource.list.AccountList;

/**
 * Class sends a message to the users's phone number
 * @author Citi 2015
 *
 */
public class TwilioDriver {

	final static String ACCOUNT_SID = "ACf45c5e57a47d0a91cd4e8115bf82d735";
	final static String AUTH_TOKEN = "dc01d8bf25d0a551c864c7bfc6b23860";

	/**
	 * Send message with twilio
	 * @param String message
	 */
	public static void twilioSendMessage(String message) {


			// Create a rest client
			TwilioRestClient client = new TwilioRestClient(ACCOUNT_SID,
					AUTH_TOKEN);

			// Get the main account (The one we used to authenticate the client)
			Account mainAccount = client.getAccount();

			// Get all accounts including sub accounts
			AccountList accountList = client.getAccounts();

			SmsFactory messageFactory = mainAccount.getSmsFactory();

			List<NameValuePair> messageParams = new ArrayList<NameValuePair>();
			messageParams.add(new BasicNameValuePair("To", "07912316165"));
			messageParams
					.add(new BasicNameValuePair("From", "+44 2891042347"));

			messageParams.add(new BasicNameValuePair("Body", message));
			try {
				messageFactory.create(messageParams);
			} catch (TwilioRestException e) {
				e.printStackTrace();


		}

	}
	
}
