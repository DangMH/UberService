import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.factories.FormFactory;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class UberServiceApplication {

	private JFrame frmUberservice;
	private EmailService emailService;
	private JTextField textFieldToLine;
	private JTextField textFieldFromLine;
	private JTextField textFieldSubjectLine;
	private JTextArea textFieldText;
	private JTextField textFieldCcLine;
	private JTextField textFieldBccLine;
	private JTextArea textFieldErrorCode;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UberServiceApplication window = new UberServiceApplication();
					window.frmUberservice.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UberServiceApplication() {
		emailService = new UberService();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmUberservice = new JFrame();
		frmUberservice.setFont(new Font("Impact", Font.PLAIN, 12));
		frmUberservice.setTitle("UberService");
		frmUberservice.setBounds(100, 100, 450, 300);
		frmUberservice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FormLayout formLayout = new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,});
		formLayout.setColumnGroups(new int[][]{new int[]{8, 2}, new int[]{6, 4}});
		frmUberservice.getContentPane().setLayout(formLayout);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setHorizontalAlignment(SwingConstants.LEFT);
		lblTo.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblTo.setToolTipText("Email address(es) of the Recipient (comma delimited).");
		lblTo.setBackground(Color.WHITE);
		frmUberservice.getContentPane().add(lblTo, "2, 2, right, default");
		
		textFieldToLine = new JTextField();
		frmUberservice.getContentPane().add(textFieldToLine, "4, 2, 3, 1, fill, fill");
		textFieldToLine.setColumns(10);
		
		JLabel lblCc = new JLabel("Cc");
		lblCc.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblCc.setToolTipText("Email address(es) of the cc'ed Recipient (comma delimited).");
		frmUberservice.getContentPane().add(lblCc, "2, 4, right, default");
		
		textFieldCcLine = new JTextField();
		frmUberservice.getContentPane().add(textFieldCcLine, "4, 4, 3, 1, fill, fill");
		textFieldCcLine.setColumns(10);
		
		JLabel lblBcc = new JLabel("Bcc");
		lblBcc.setFont(new Font("SansSerif", Font.PLAIN, 12));
		lblBcc.setToolTipText("Email address(es) of the bcc'ed Recipient (comma delimited).");
		frmUberservice.getContentPane().add(lblBcc, "2, 6, right, default");
		
		textFieldBccLine = new JTextField();
		frmUberservice.getContentPane().add(textFieldBccLine, "4, 6, 3, 1, fill, fill");
		textFieldBccLine.setColumns(10);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setToolTipText("Email address of the sender.");
		lblFrom.setHorizontalAlignment(SwingConstants.LEFT);
		lblFrom.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmUberservice.getContentPane().add(lblFrom, "2, 8, right, default");
		
		textFieldFromLine = new JTextField();
		frmUberservice.getContentPane().add(textFieldFromLine, "4, 8, 3, 1, fill, fill");
		textFieldFromLine.setColumns(10);
		
		JLabel lblSubject = new JLabel("Subject");
		lblSubject.setToolTipText("Text for the subject line.");
		lblSubject.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmUberservice.getContentPane().add(lblSubject, "2, 10, right, default");
		
		textFieldSubjectLine = new JTextField();
		frmUberservice.getContentPane().add(textFieldSubjectLine, "4, 10, 3, 1, fill, fill");
		textFieldSubjectLine.setColumns(10);
		
		JLabel lblText = new JLabel("Text");
		lblText.setToolTipText("Text for the body of the email.");
		lblText.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmUberservice.getContentPane().add(lblText, "2, 12, right, default");
		
		textFieldText = new JTextArea();
		frmUberservice.getContentPane().add(textFieldText, "4, 12, 3, 5, fill, fill");
		textFieldText.setColumns(10);
		
		JButton btnSendEmail = new JButton("Send Email");
		btnSendEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendEmail();
			}
		});
		
		JLabel lblErrorcode = new JLabel("ErrorCode");
		lblErrorcode.setToolTipText("Contains Error Code of previous service session.");
		lblErrorcode.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmUberservice.getContentPane().add(lblErrorcode, "2, 18, right, default");
		
		textFieldErrorCode = new JTextArea();
		textFieldErrorCode.setEditable(false);
		frmUberservice.getContentPane().add(textFieldErrorCode, "4, 18, 3, 1, fill, fill");
		textFieldErrorCode.setColumns(10);
		btnSendEmail.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmUberservice.getContentPane().add(btnSendEmail, "3, 20, 2, 1, fill, fill");
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clear();
			}
		});
		btnClear.setFont(new Font("SansSerif", Font.PLAIN, 12));
		frmUberservice.getContentPane().add(btnClear, "6, 20, 2, 1, fill, fill");
	}
	
	/**
	 * Method to send email with information from the text fields.
	 * Text fields will be cleared after sending a successful email.
	 */
	private void sendEmail()
	{
		String errorCode;
		
		textFieldErrorCode.setText( "" );
		
		List<EmailAddress> temp = new ArrayList<EmailAddress>();;
		
		Email email = new Email();
		
		for( String address : textFieldToLine.getText().split("\\s*,\\s*") )
		{
			if( address.isEmpty() )
			{
				continue;
			}
			
			try
			{
				temp.add( new EmailAddress( address ) );
			}
			catch( IllegalArgumentException e )
			{
				textFieldErrorCode.setText( textFieldErrorCode.getText() + "To Line ERROR: " + e.toString() + "\n" );
			}
		}
		email.addToLine( temp );
		
		for( String address : textFieldCcLine.getText().split("\\s*,\\s*") )
		{
			if( address.isEmpty() )
			{
				continue;
			}
			
			try
			{
				temp.add( new EmailAddress( address ) );
			}
			catch( IllegalArgumentException e )
			{
				textFieldErrorCode.setText( textFieldErrorCode.getText() + "Cc Line ERROR: " + e.toString() + "\n" );
			}
		}
		email.addCcLine( temp );
		
		for( String address : textFieldBccLine.getText().split("\\s*,\\s*") )
		{
			if( address.isEmpty() )
			{
				continue;
			}
			
			try
			{
				temp.add( new EmailAddress( address ) );
			}
			catch( IllegalArgumentException e )
			{
				textFieldErrorCode.setText( textFieldErrorCode.getText() + "Bcc Line ERROR: " + e.toString() + "\n" );
			}
		}
		email.addBccLine( temp );
		
		if( !textFieldFromLine.getText().isEmpty() )
		{
			try
			{
				email.setFrom( new EmailAddress( textFieldFromLine.getText() ) );
			}
			catch( IllegalArgumentException e )
			{
				textFieldErrorCode.setText( textFieldErrorCode.getText() + e.toString() + "\n" );
			}
		}
		
		email.setSubject( textFieldSubjectLine.getText() );
		email.setText( textFieldText.getText() );
		
		errorCode = emailService.send( email );
		
		textFieldErrorCode.setText( textFieldErrorCode.getText() + "\n" + errorCode + "\n" );
		
		if( EmailService.SUCCESS_CODE.equals( errorCode ) )
		{
			clear();
		}
	}
	
	/**
	 * Method to clear email text fields.
	 */
	private void clear() {
		textFieldToLine.setText("");
		textFieldCcLine.setText("");
		textFieldBccLine.setText("");
		textFieldFromLine.setText("");
		textFieldSubjectLine.setText("");
		textFieldText.setText("");
	}

}
