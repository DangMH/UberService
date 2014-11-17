
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;


public class EmailServiceTestNegativeCases {
	EmailAddress testAddress = new EmailAddress( "DangMH@gmail.com" );
	String testSubject = "Test Subject";
	String testText = "Test Text";
	String errorCode = "";
	Email email = new Email();
	EmailService service = new SendGridService();
	
	@Test
	public void testUseServiceMissingToLine() {
		email.setFrom( testAddress );
		email.setSubject( testSubject );
		email.setText( testText );
		
		errorCode = service.send( email );
		
		Assert.assertEquals(Email.EMAIL_MISSING_TO, errorCode);
	}
	
	@Test
	public void testUseServiceMissingFromLine() {
		email.addToLine( new ArrayList<EmailAddress>( Arrays.asList( testAddress ) ) );
		email.setSubject( testSubject );
		email.setText( testText );
		
		errorCode = service.send( email );
		
		Assert.assertEquals(Email.EMAIL_MISSING_FROM, errorCode);
	}
	
	@Test
	public void testUseServiceMissingSubjectLine() {
		email.addToLine( new ArrayList<EmailAddress>( Arrays.asList( testAddress ) ) );
		email.setFrom( testAddress );
		email.setText( testText );
		
		errorCode = service.send( email );
		
		Assert.assertEquals(Email.EMAIL_MISSING_SUBJECT, errorCode);
	}
	
	@Test
	public void testUseServiceMissingText() {
		email.addToLine( new ArrayList<EmailAddress>( Arrays.asList( testAddress ) ) );
		email.setFrom( testAddress );
		email.setSubject( testSubject );
		
		errorCode = service.send( email );
		
		Assert.assertEquals(Email.EMAIL_MISSING_TEXT, errorCode);
	}
}
