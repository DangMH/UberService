
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Basic test to see if an email can be successfully sent via SendGrid.
 * @author Michael
 *
 */
public class MandrillServiceTestPositiveCases {
	EmailAddress testAddress = new EmailAddress( "DangMH@gmail.com" );
	String testSubject = "Test Subject";
	String testText = "Test Text";
	String errorCode = "";
	Email email = new Email();
	EmailService service = new UberService();
	
	@Test
	public void testUseService() {
		email.addToLine( new ArrayList<EmailAddress>( Arrays.asList( testAddress ) ) );
		email.setFrom( testAddress );
		email.setSubject( testSubject );
		email.setText( testText );
		
		errorCode = service.send( email );
		
		assertEquals(EmailService.SUCCESS_CODE, errorCode);
	}
}
