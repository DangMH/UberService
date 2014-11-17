import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class MandrillService extends EmailService {
	private final static int MANDRILL_SERVICE_WEIGHT = 13;
	private final static String API_KEY = "A0x-j05BFKkoY0NwghUAAQ";
	private final static String MANDRILL_ADDRESS = "https://mandrillapp.com/api/1.0/messages/send.json";

	private WebResource webResource; 
	
	private JsonObject jsonObject;

	/**
	 * Default constructor that sets serviceWeight and initializes the Mandrill Object.
	 */
	public MandrillService()
	{
		Client client = Client.create();
		client.addFilter( new HTTPBasicAuthFilter( "api", API_KEY ) );
		
		webResource = client.resource( MANDRILL_ADDRESS );
		
		setServiceWeight( MANDRILL_SERVICE_WEIGHT );
	}
	
	/**
	 * Method overridden with specific calls to the Mandrill API.
	 */
	@Override
	public String useService(Email email) {
		String errorCode = "";
		ClientResponse response;
		
		JsonArrayBuilder toLineArray = Json.createArrayBuilder();

		for( EmailAddress emailAddress : email.getToLine() )
		{
			toLineArray.add( Json.createObjectBuilder().add( "email", emailAddress.toString() ) );
		}
		for( EmailAddress emailAddress : email.getCcLine() )
		{
			toLineArray.add( Json.createObjectBuilder().add( "email", emailAddress.toString() ).add( "type", "cc" ) );
		}
		for( EmailAddress emailAddress : email.getBccLine() )
		{
			toLineArray.add( Json.createObjectBuilder().add( "email", emailAddress.toString() ).add( "type", "bcc" ) );
		}
		
		jsonObject = Json.createObjectBuilder()
				.add( "key", API_KEY )
				.add( "message", Json.createObjectBuilder()
						.add( "text", email.getText() )
						.add( "subject", email.getSubject() )
						.add( "from_email", email.getFrom().toString() )
						.add( "to", toLineArray) ).build();
		
		response = webResource.type( MediaType.APPLICATION_JSON_TYPE ).post( ClientResponse.class, jsonObject.toString() );
		
		if( ClientResponse.Status.OK.getStatusCode() == response.getStatus() )
		{
			errorCode = SUCCESS_CODE;
		}
		else
		{
			errorCode = "Mandrill Failure: " + response.getStatus() + " - " + response.getStatusInfo().toString() + "\n";
		}
		
		return errorCode;
	}

}
