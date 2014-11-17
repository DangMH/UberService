import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;


/**
 * Class representation of an Email Address.
 * Constructor will validate strings for email addresses.
 * Potential methods could parse domain and verify against DNS and other such functionalities.
 * @author Michael
 *
 */
public class EmailAddress {
	private String address;
	
	/**
	 * Default constructor is disabled.
	 * There is no default address.
	 */
	@SuppressWarnings( "unused" )
	private EmailAddress()
	{
		this("");
	}
	
	/**
	 * Public constructor that validates the email address
	 * @param address Email address to be validated
	 * @throws IllegalArgumentException If the String is not in a valid email address (i.e., myEmail@myDomain.com) or contains invalid characters such as '+' or '/'
	 */
	public EmailAddress( String address ) throws IllegalArgumentException 
	{
		setAddress( address );
	}
	
	/**
	 * Accessor method for the email address.
	 * @return The String assigned to the email address.
	 */
	public String getAddress()
	{
		return address;
	}
	
	/**
	 * Mutator method for the email address.
	 * @param address Specified String to replace the email address.
	 * @throws IllegalArgumentException If the String is not in a valid email address (i.e., myEmail@myDomain.com) or contains invalid characters such as '+' or '/'
	 */
	public void setAddress( String address ) throws IllegalArgumentException
	{
		if( isValidAddress( address ) )
		{
			this.address = address;
		}
		else
		{
			throw new IllegalArgumentException( "invalid Email Address: " + address );
		}
	}
	
	/**
	 * Method to validate Strings against email address formatting.
	 * source from Aaron Davidson from Stack Overflow forum: http://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
	 * @param address Specified String to be validated.
	 * @return true if the String is a valid email, else false.
	 */
	private boolean isValidAddress(String address)
	{
		boolean ret = true;
		try
		{
			InternetAddress emailAddress = new InternetAddress(address);
			emailAddress.validate();
		} catch(AddressException ex)
		{
			ret = false;
		}
		
		return ret;
	}
	
	/**
	 * Boolean check to see if email address is empty
	 * @return true if the email address is empty, else false.
	 */
	public boolean isEmpty()
	{
		return address.isEmpty();
	}
	
	/**
	 * Override default toString() method
	 */
	@Override
	public String toString()
	{
		return address;
	}
}
