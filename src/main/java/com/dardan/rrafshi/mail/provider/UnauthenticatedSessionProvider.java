package com.dardan.rrafshi.mail.provider;

import java.util.Properties;

import javax.mail.Session;


public class UnauthenticatedSessionProvider extends SessionProvider
{

	public UnauthenticatedSessionProvider(final SessionProviderBuilder builder)
	{
		super(builder);
	}


	@Override
	public Session getSession()
	{
		final Properties properties = new Properties();
		properties.put("mail.smtp.auth", "false");
		properties.put("mail.smtp.host", this.getHost());
		properties.put("mail.smtp.port", this.getPort());
		return Session.getInstance(properties);
	}

}
