import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

/**
 * Class to implement SendGrid API calls to send an email message.
 * TODO: Override with the SendGrid credentials to run code.
 * @author Michael
 *
 */
public class SendGridService extends EmailService {
	private final static int sendGridSuccessCode = 200;
	private final static int SENDGRID_SERVICE_WEIGHT = 64;
	private final static String SENDGRID_USERNAME = "SAMPLE_USERNAME";
	private final static String SENDGRID_PASSWORD = "SAMPLE_PASSWORD";
	
	private SendGrid sendGrid;
	
	/**
	 * Default constructor that sets serviceWeight and initializes the SendGrid Object.
	 */
	public SendGridService()
	{
		sendGrid = new SendGrid(SENDGRID_USERNAME, SENDGRID_PASSWORD);
		
		setServiceWeight(SENDGRID_SERVICE_WEIGHT);
	}
	
	/**
	 * Method overridden with specific calls to the SendGrid API.
	 */
	@Override
	public String useService(Email email) {
		String errorCode = "";
		int responseCode = -1;
		String responseMessage = "";
		SendGrid.Email sendGridEmail = new SendGrid.Email();
		SendGrid.Response response = null;
		
		/*
		 * Parsing the email information into the SendGrid email.
		 */
		for( EmailAddress emailAddress : email.getToLine() )  {
			sendGridEmail.addTo( emailAddress.toString() );
		}
		for( EmailAddress emailAddress : email.getCcLine() ) {
			sendGridEmail.addCc( emailAddress.toString() );
		}
		for( EmailAddress emailAddress : email.getBccLine() ) {
			sendGridEmail.addBcc( emailAddress.toString() );
		}
		sendGridEmail.setFrom( email.getFrom().toString() );
		sendGridEmail.setSubject( email.getSubject() );
		sendGridEmail.setText( email.getText() );
		
		try
		{
			response = sendGrid.send( sendGridEmail );
			
			responseMessage = response.getMessage();
			responseCode = response.getCode();
		}
		catch ( SendGridException e )
		{
			e.printStackTrace();
		}
		
		if( sendGridSuccessCode == responseCode )
		{
			errorCode = SUCCESS_CODE;
		}
		else
		{
			errorCode = "SendGrid Failure: " + responseCode + " - " + responseMessage + "\n";
		}
		
		return errorCode;
	}

}
