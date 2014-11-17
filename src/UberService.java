import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class UberService extends EmailService {
	private List<EmailService> emailServices;
	
	/**
	 * Default constructor that sets serviceWeight to the maximum of the list of email services enlisted.
	 */
	public UberService()
	{
		int reliabilityScore = minServiceWeight;
		
		emailServices = new ArrayList<EmailService>();
		emailServices.add(new SendGridService());
		emailServices.add(new MailgunService());
		
		for( EmailService emailService : emailServices )
		{
			reliabilityScore = reliabilityScore  < emailService.getReliability() ? emailService.getReliability() : reliabilityScore;
		}
	}
	
	/**
	 * Method overridden with specific calls to the enlisted email services in order of decreasing reliability score.
	 */
	@Override
	public String useService(Email email) {
		String errorCode = "";
		String serviceLog = "";
		
		Collections.sort( emailServices );
		
		for( EmailService emailService : emailServices )
		{
			errorCode = emailService.send(email);
			
			if( SUCCESS_CODE.equals(errorCode) )
			{
				return errorCode;
			}
			
			serviceLog += errorCode;
		}
		
		return serviceLog;
	}

}
