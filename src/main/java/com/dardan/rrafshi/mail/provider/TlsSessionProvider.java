package com.dardan.rrafshi.mail.provider;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class TlsSessionProvider extends AuthenticatedSessionProvider
{

	public TlsSessionProvider(final AuthenticatedSessionProviderBuilder builder)
	{
		super(builder);
	}


	@Override
    public Session getSession()
	{
        final Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", this.getHost());
        props.put("mail.smtp.port", this.getPort());
        props.put("mail.smtp.starttls.enable", "true");

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
