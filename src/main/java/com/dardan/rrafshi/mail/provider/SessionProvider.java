package com.dardan.rrafshi.mail.provider;

import javax.mail.Session;

import com.dardan.rrafshi.mail.Constants;


public abstract class SessionProvider
{
	private final String host;
	private final String port;


	public SessionProvider(final SessionProviderBuilder builder)
	{
		this.host =  builder.getHost();
		this.port = builder.getPort();
	}


	public abstract Session getSession();


	public String getHost()
	{
		return this.host;
	}

	public String getPort()
	{
		return this.port;
	}


	public static SessionProvider unauthenticated(final String host)
	{
		return new Builder(host).unauthenticated();
	}

	public static SessionProvider unauthenticated(final String host, final String port)
	{
		return new Builder(host, port).unauthenticated();
	}


	public static class Builder implements SessionProviderBuilder
	{
		private final String host;
		private final String port;


		public Builder(final String host, final String port)
		{
			this.host = host;
			this.port = port;
		}

		public Builder(final String host)
		{
			this(host, Constants.DEFAULT_SMTP_PORT);
		}


		@Override
		public String getHost()
		{
			return this.host;
		}

		@Override
		public String getPort()
		{
			return this.port;
		}


		public SessionProvider unauthenticated()
		{
			return new UnauthenticatedSessionProvider(this);
		}
	}

}
