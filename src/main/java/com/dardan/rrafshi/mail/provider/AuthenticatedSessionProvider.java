package com.dardan.rrafshi.mail.provider;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;


public class AuthenticatedSessionProvider extends SessionProvider
{
	private final String username;
	private final String password;


	public AuthenticatedSessionProvider(final AuthenticatedSessionProviderBuilder builder)
	{
		super(builder);
		this.username = builder.getUsername();
		this.password = builder.getPassword();
	}


	@Override
	public Session getSession()
	{
		final Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", this.getHost());
		properties.put("mail.smtp.port", this.getPort());

		final String username = this.getUsername();
		final String password = this.getPassword();

		return Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
	}


	protected String getUsername()
	{
		return this.username;
	}

	protected String getPassword()
	{
		return this.password;
	}


	public static Builder host(final String host)
	{
		return new Builder(host);
	}

	public static Builder host(final String host, final String port)
	{
		return new Builder(host, port);
	}


	public static class Builder extends SessionProvider.Builder implements AuthenticatedSessionProviderBuilder
	{
		private String username;
		private String password;


		public Builder(final String host, final String port)
		{
			super(host, port);
		}

		public Builder(final String host)
		{
			super(host);
		}


		@Override
		public String getUsername()
		{
			return this.username;
		}

		@Override
		public String getPassword()
		{
			return this.password;
		}


		public SessionProvider authenticate(final String username, final String password)
		{
			this.username = username;
			this.password = password;
			return new AuthenticatedSessionProvider(this);
		}

		public SessionProvider authenticateWithTls(final String username, final String password)
		{
			this.username = username;
			this.password = password;
			return new TlsSessionProvider(this);
		}

		public SessionProvider authenticateWithSsl(final String username, final String password)
		{
			this.username = username;
			this.password = password;
			return new SslSessionProvider(this);
		}
	}

}
