
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Basic test to see if the service boundaries are enforced.
 * @author Michael
 *
 */
public class EmailServiceTest {

	@Test
	public void testSetServiceWeight() {
		EmailService service = new SendGridService();
		
		service.setServiceWeight(34);
		assertEquals( 34, service.getReliability() );
		
		service.setServiceWeight(1);
		assertEquals( 1, service.getReliability() );
		
		service.setServiceWeight(100);
		assertEquals( 100, service.getReliability() );
		
		service.setServiceWeight(1);
		assertEquals( 1, service.getReliability() );
		
		service.setServiceWeight(101);
		assertEquals( 100, service.getReliability() );
	}

}
