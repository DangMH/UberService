
import org.junit.Test;


public class EmailAddressTest {
	@Test
	public void testEmailAddress() {
		new EmailAddress("MyEmail@MyDomain.com");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testEmailAddressInvalid() {
		new EmailAddress("--MyDomain.com");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testEmailAddressEmpty() {
		new EmailAddress("");
	}

	@Test
	public void testSetAddress() {
		EmailAddress emailAddress = new EmailAddress("MyEmail@MyDomain.com");
		emailAddress.setAddress("MyEmail2@MyDomain2.com");
	}

	@Test (expected=IllegalArgumentException.class)
	public void testSetAddressInvalid() {
		EmailAddress emailAddress = new EmailAddress("MyEmail@MyDomain.com");
		emailAddress.setAddress("--MyDomain2.com");
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void testSetAddressEmpty() {
		EmailAddress emailAddress = new EmailAddress("MyEmail@MyDomain.com");
		emailAddress.setAddress("");
	}
}
