/**
 * Abstract Class meant to encompass email services in general.
 * Defines email service interface: send and getReliabiility.
 * 
 * @author Michael
 *
 */
public abstract class EmailService implements Comparable<EmailService> {
	/**
	 * 
	 */
	public static String SUCCESS_CODE = "SUCCESS";
	
	protected final int minServiceWeight = 1;
	protected final int maxServiceWeight = 100;
	
	protected int maxSendServiceAttempts = 10;
	
	private int messagesSent = 0;
	private int serviceFailures = 0;
	private int serviceWeight = maxServiceWeight;
	
	/**
	 * Method to send a designated email.
	 * messagesSent will be incremented once per email to be sent.
	 * serviceFailures will increment every failed attempt to send the email.
	 * Each call will attempt to send the email at most MAX_EMAIL_SERVICE_ATTEMPTS.
	 * @param email The email to be sent.
	 * @return Error code on method success.  Returns SUCCESS_CODE if successful, else returns error message.
	 */
	public final String send(Email email)
	{
		String errorCode = "";
		String emailReady = email.isReadyToSend();
		String serviceLog = "";
		
		if( !Email.EMAIL_READY_SUCCESS.equals(emailReady) )
		{
			return emailReady;
		}
		
		if( messagesSent < Integer.MAX_VALUE )
		{
			++messagesSent;
		}
		
		for( int i = 0; i < maxSendServiceAttempts; ++i )
		{
			errorCode = useService(email);
			if( !SUCCESS_CODE.equals( errorCode )
					&& serviceFailures < Integer.MAX_VALUE )
			{
				serviceLog += errorCode + "\n";
				++serviceFailures;
			}
			else
			{
				return errorCode;
			}
		}
		
		return serviceLog;
	}
	
	/**
	 * Method to send email specific to Service Provider.
	 * @param email email The email to be sent.
	 * @return Error code on method success.  Returns SUCCESS_CODE if successful, else returns error message.
	 */
	public abstract String useService( Email email );
	
	/**
	 * Method to return the reliability of the specified email service.
	 * This method will be used to compare EmailServices in terms of service reliability.
	 * By default this score will be calculated by 'serviceWeight * ( messagesSent - serviceFailures + 1 )'.
	 * The 1 is to ensure initial reliability score is a factor.
	 * @return The reliability score of an EmailService.
	 * The reliability score should be based on failure rate/success by default.
	 */
	public final int getReliability()
	{
		if( ( Integer.MAX_VALUE / getServiceWeight() ) < ( messagesSent - serviceFailures + 1 ) )
		{
			return Integer.MAX_VALUE;
		}
		else
		{
			return getServiceWeight() * ( messagesSent - serviceFailures + 1);
		}
	}
	
	/**
	 * Accessor method for serviceWeight
	 * @return The service weight of the email service
	 */
	protected final int getServiceWeight() {
		return serviceWeight;
	}

	/**
	 * Mutator method for serviceWeight within proper boundaries
	 */
	protected final void setServiceWeight( int serviceWeight ) {
		this.serviceWeight = minServiceWeight > serviceWeight ? minServiceWeight : serviceWeight;
		this.serviceWeight = maxServiceWeight < serviceWeight ? maxServiceWeight : serviceWeight;
	}
	
	/**
	 * Overridden method required for Comparable interface.
	 * It ensures that lists will be sorted in descending order.
	 * @param o EmailService to be compared to.
	 * @return
	 * Returns 1 if EmailService o is less reliable than this EmailService.
	 * Returns 0 if EmailService o is just as reliable as this EmailService.
	 * Returns -1 if EmailService o is more reliable than this EmailService.
	 */
	public final int compareTo(EmailService o)
	{
		if( o.getReliability() < this.getReliability() )
		{
			return -1;
		}
		else if( o.getReliability() > this.getReliability() )
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
}