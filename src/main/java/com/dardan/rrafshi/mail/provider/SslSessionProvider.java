package com.dardan.rrafshi.mail.provider;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class SslSessionProvider extends AuthenticatedSessionProvider
{

	public SslSessionProvider(final AuthenticatedSessionProviderBuilder builder)
	{
		super(builder);
	}


	@Override
    public Session getSession()
	{
        final Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");
        props.put("mail.smtps.auth", "true");
        props.put("mail.smtps.host", this.getHost());
        props.put("mail.smtps.port", this.getPort());
        props.put("mail.smtp.ssl.enable", "true");

        final String username = this.getUsername();
        final String password = this.getPassword();

        return Session.getInstance(props, new Authenticator() {
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

}
