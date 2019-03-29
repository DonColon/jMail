package com.dardan.rrafshi.mail;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

import com.dardan.rrafshi.mail.model.Email;
import com.dardan.rrafshi.mail.model.EmailBody;
import com.dardan.rrafshi.mail.provider.SessionProvider;


public class MailService
{
	private final SessionProvider provider;


	public MailService(final SessionProvider provider)
	{
		this.provider = provider;
	}


	public void send(final Email email) throws MessagingException
	{
		final Message message = new MimeMessage(this.provider.getSession());
		message.setSubject(email.getSubject());

		final Multipart multipart = this.createMultipart(email.getBody());
		message.setContent(multipart);

		message.setFrom(email.getFromAddress());

		message.addRecipients(Message.RecipientType.TO,
				email.getToAddresses().toArray(new InternetAddress[email.getToAddresses().size()]));

		message.addRecipients(Message.RecipientType.CC,
				email.getCcAddresses().toArray(new InternetAddress[email.getCcAddresses().size()]));

		message.addRecipients(Message.RecipientType.BCC,
				email.getBccAddresses().toArray(new InternetAddress[email.getBccAddresses().size()]));

		Transport.send(message);
	}

	private Multipart createMultipart(final EmailBody body)
	{
		final Multipart multipart = body.getMultipartMail();
		try {
			final BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(body.getContent(), body.getType());
			multipart.addBodyPart(messageBodyPart);

		} catch (final MessagingException exception) {

			throw new RuntimeException("Failed to add email body: " + body.getContent(), exception);
		}
		return multipart;
	}

}
