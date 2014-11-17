UberService
===========
Email service with fail-over logic among three free email services ( SendGrid, Mailgun, Mandill ).  When sending an email, the Uber service prioritizes the services in terms of reliability.  If a service fails, it will seamlessly attempt the next service in the list until all services and attempts are exhausted.

The source will always fail sending emails until the appropriate credentials for the desired services are inputted:
- SendGrid accounts require a username and password
- MailGun accounts require an API key and a Domain
- Mandrill accounts require an API key
 
Located in the source is a pre-compiled JAR with sample credentials inserted.

Contact with Developer:
- Email: DangMH@gmail.com
- LinkedIn: http://www.linkedin.com/pub/michael-dang/31/792/854/
