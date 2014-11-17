import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MailgunService extends EmailService {
	private final static int MAILGUN_SERVICE_WEIGHT = 23;
	private final static String API_KEY = "SAMPLE_KEY";
	private final static String DOMAIN = "SAMPLE_DOMAIN";
	private final static String MAILGUN_ADDRESS = "https://api.mailgun.net/v2/" + DOMAIN + "/messages";
	
	private WebResource webResource; 
	
	/**
	 * Default constructor that sets serviceWeight and initializes the Mailgun Object.
	 */
	public MailgunService()
	{
		Client client = Client.create();
		client.addFilter( new HTTPBasicAuthFilter( "api", API_KEY ) );
		
		webResource = client.resource( MAILGUN_ADDRESS );
		
		setServiceWeight( MAILGUN_SERVICE_WEIGHT );
	}
	
	/**
	 * Method overridden with specific calls to the Mailgun API.
	 */
	@Override
	public String useService(Email email) {
		String errorCode = "";
		ClientResponse response;
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		
		for( EmailAddress emailAddress : email.getToLine() )  {
			formData.add( "to", emailAddress.toString() );
		}
		for( EmailAddress emailAddress : email.getCcLine() ) {
			formData.add( "cc", emailAddress.toString() );
		}
		for( EmailAddress emailAddress : email.getBccLine() ) {
			formData.add( "bcc", emailAddress.toString() );
		}
		formData.add( "from", email.getFrom().toString() );
		formData.add( "subject", email.getSubject() );
		formData.add( "text", email.getText() );
		
		response = webResource.type( MediaType.APPLICATION_FORM_URLENCODED ).post( ClientResponse.class, formData );
		
		if( ClientResponse.Status.OK.getStatusCode() == response.getStatus() )
		{
			errorCode = SUCCESS_CODE;
		}
		else
		{
			errorCode = "Mailgun Failure: " + response.getStatus() + " - " + response.getStatusInfo().toString() + "\n";
		}
		
		return errorCode;
	}

}
