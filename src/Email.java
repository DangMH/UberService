import java.util.ArrayList;
import java.util.List;

/**
 * Class meant to encapsulate the required fields to send and email.
 * Contains accessor interface for appropriate fields.
 * @author Michael
 *
 */
public class Email {
	public final static String EMAIL_READY_SUCCESS = "true";
	public final static String EMAIL_MISSING_TO = "ERROR:\tIncomplete email address, To Line is empty\n";
	public final static String EMAIL_MISSING_FROM = "ERROR:\tIncomplete email address, From Line is empty\n";
	public final static String EMAIL_MISSING_SUBJECT = "ERROR:\tIncomplete email address, Subject Line is empty\n";
	public final static String EMAIL_MISSING_TEXT = "ERROR:\tIncomplete email address, Text is empty\n";
	
	private List<EmailAddress> to;
	private List<EmailAddress> cc;
	private List<EmailAddress> bcc;
	private EmailAddress from;
	private String subject;
	private String text;
	
	/**
	 * Default Constructor.  Lists are using ArrayListImplementation.
	 */
	public Email()
	{
		to = new ArrayList<EmailAddress>();
		cc = new ArrayList<EmailAddress>();
		bcc = new ArrayList<EmailAddress>();
		from = null;
		subject = "";
		text = "";
	}
	
	/**
	 * Accessor method for To line addresses.
	 * @return Returns the List of email addresses assigned to the To line.
	 */
	public List<EmailAddress> getToLine()
	{
		return to;
	}
	
	/**
	 * Mutator method for To line addresses.
	 * @param addresses Specified email addresses that replaces the To line. 
	 */
	public void setToLine( List<EmailAddress> addresses )
	{
		this.to = addresses;
	}
	
	/**
	 * Mutator method for To line addresses.
	 * @param addresses Specified email addresses to be appended to the To line.
	 */
	public void addToLine( List<EmailAddress> addresses )
	{
		this.to.addAll(addresses);
	}
	
	/**
	 * Accessor method for the From address.
	 * @return Returns the email address assigned to the From line.
	 */
	public EmailAddress getFrom()
	{
		return from;
	}
	
	/**
	 * Mutator method for the From address.
	 * @param address Specified email address that replaces the From line. 
	 */
	public void setFrom( EmailAddress address )
	{
		this.from = address;
	}
	
	/**
	 * Accessor method for Cc line addresses.
	 * @return Returns the List of email addresses assigned Cc the From line.
	 */
	public List<EmailAddress> getCcLine()
	{
		return cc;
	}
	
	/**
	 * Mutator method for Cc line addresses.
	 * @param addresses Specified email addresses that replaces the Cc line. 
	 */
	public void setCcLine( List<EmailAddress> addresses )
	{
		this.cc = addresses;
	}
	
	/**
	 * Mutator method for Cc line addresses.
	 * @param addresses Specified email addresses to be appended to the Cc line.
	 */
	public void addCcLine( List<EmailAddress> addresses )
	{
		this.cc.addAll(addresses);
	}
	
	/**
	 * Accessor method for Bcc line addresses.
	 * @return Returns the List of email addresses assigned Bcc the From line.
	 */
	public List<EmailAddress> getBccLine()
	{
		return bcc;
	}
	
	/**
	 * Mutator method for Bcc line addresses.
	 * @param addresses Specified email addresses that replaces the Bcc line. 
	 */
	public void setBccLine( List<EmailAddress> addresses )
	{
		this.bcc = addresses;
	}
	
	/**
	 * Mutator method for Bcc line addresses.
	 * @param addresses Specified email addresses to be appended to the Bcc line.
	 */
	public void addBccLine( List<EmailAddress> addresses )
	{
		this.bcc.addAll(addresses);
	}

	/**
	 * Accessor method for Subject line.
	 * @return Returns the String assigned Subject line.
	 */
	public String getSubject()
	{
		return subject;
	}
	
	/**
	 * Mutator method for Subject line.
	 * @param Subject Specified String that replaces the Subject line. 
	 */
	public void setSubject( String subject )
	{
		this.subject = subject;
	}
	
	/**
	 * Accessor method for Text.
	 * @return Returns the String assigned Text.
	 */
	public String getText()
	{
		return text;
	}
	
	/**
	 * Mutator method for Text body.
	 * @param addresses Specified String that replaces the Text body. 
	 */
	public void setText( String text )
	{
		this.text = text;
	}
	/**
	 * Method to verify that an email is ready to send
	 * @return "true" if the email is ready to send, else an error message containing issues.
	 */
	public String isReadyToSend()
	{
		String ret = "";
		
		ret = to.isEmpty() ? ret + EMAIL_MISSING_TO : ret;
		ret = null == from ? ret + EMAIL_MISSING_FROM : ret;
		ret = subject.isEmpty() ? ret + EMAIL_MISSING_SUBJECT : ret;
		ret = text.isEmpty() ? ret + EMAIL_MISSING_TEXT : ret;
		
		/*
		 * If there were no errors, return "success"
		 */
		ret = ret.isEmpty() ? EMAIL_READY_SUCCESS : ret;
		
		return ret;
	}
}
