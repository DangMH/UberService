UberService
===========
For the Uber Challenge I chose to code up the Email Service, Back-End Track.

The Concept:
  Create a service that accepts the necessary information and sends emails. It should provide an abstraction between two different email service providers. If one of the services goes down, your service can quickly failover to a different provider without affecting your customers.

Example Email Providers:

SendGrid - Simple Send Documentation
Mailgun - Simple Send Documentation
Mandrill - Simple Send Documentation

All three services are free to try and are pretty painless to sign up for, so please register your own test accounts on each.

The Solution:
  The goal of the solution was to create a simple API that would allow a user to send an email without regards to what service is being used on the backend.  The solution was also designed with extensibility in mind.  Every email service ( SendGrid, Mailgun, Mandrill ), and even the solution service, UberService, all extend the EmailService class, which can send an email, or return its reliability.
  
  Sending an email is self-explanatory.  Reliability is an arbitrary metric that is based on the number of emails sent versus the number of failures an instance of an email service has incurred, as well as factored by the serviceWeight ( A score 0-100 based on user experience from: http://socialcompare.com/en/comparison/transactional-emailing-providers-mailjet-sendgrid-critsend ).  The formula looks like:
  
   serviceWeight * ( mailSent - numFailures + 1)
   
  The +1 ensures that every initial instance of an email service will have its full serviceWeight factored in ordering [ serviceWeight * (0 - 0 + 1) ].
  
  The reliability score is used in the UberService to decide what order to attempt each service in.  UberService contains a list of EmailServices, and before it sends an Email, it will sort its list of emails in descending reliability score before iterating through each EmailService until a successful send.
  
  As you can see, every successfull send increases a service's reliability, and therefore pushes its weight further toward the front of the queue.  Potentially a service that succeeds 100% of the time will be the sole provider [ this is assuming each service has unlimited resources ].
  
  What's more, the code is implemented in such that in order to add new email services to the UberService, one only needs to:
  - extend EmailService with a new subclass that implements the specific API calls to the EmailService to send mail
  - add a line of code that initializes and instance of the service and adds it to the UberService's list
  
  That extensibility allows countless services to be added effortlessly.

Running the solution:
 Currently I am hosting an executable JAR on the desktop of an instance in AWS EC2 as suggested.  As it is my first time using the service; however, I will need some instruction on how to easily share the instance to run the application.

 The source can be found here on the GitHub repository.  I have removed my credentials from the source, however.

 The source will always fail sending emails until the appropriate credentials for the desired services are inputted:
- SendGrid accounts require a username and password
- MailGun accounts require an API key and a Domain
- Mandrill accounts require an API key

 Simply plug in the appropriate credentials in each Class (SendGridService.java, MailgunService.java, and MandrillService.java) and the compiled source will be able to run the email service.
 
 The GUI was done using the WindowsBuilder plugin for eclipse.
 
Located in the source is a pre-compiled JAR with sample credentials inserted.

Contact with Developer:
- Email: DangMH@gmail.com
- LinkedIn: http://www.linkedin.com/pub/michael-dang/31/792/854/
