package com.dardan.rrafshi.mail.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.mail.internet.InternetAddress;

public class Email
{
	private final List<InternetAddress> toAddresses;
	private final List<InternetAddress> ccAddresses;
	private final List<InternetAddress> bccAddresses;

	private final InternetAddress fromAddress;
	private final String subject;
	private final EmailBody body;


	protected Email(final Builder builder)
	{
        this.toAddresses = builder.toAddresses;
        this.ccAddresses = builder.ccAddresses;
        this.bccAddresses = builder.bccAddresses;

		this.fromAddress = builder.fromAddress;
        this.subject = builder.subject;
        this.body = builder.body;
	}


	public InternetAddress getFromAddress()
	{
		return this.fromAddress;
	}

	public List<InternetAddress> getToAddresses()
	{
		return Collections.unmodifiableList(this.toAddresses);
	}

	public List<InternetAddress> getCcAddresses()
	{
		return Collections.unmodifiableList(this.ccAddresses);
	}

	public List<InternetAddress> getBccAddresses()
	{
		return Collections.unmodifiableList(this.bccAddresses);
	}

	public String getSubject()
	{
		return this.subject;
	}

	public EmailBody getBody()
	{
		return this.body;
	}


	public static EmailBuilder builder()
	{
		return new Email.Builder();
	}


	public static class Builder implements EmailBuilder
	{
		private final List<InternetAddress> toAddresses;
		private final List<InternetAddress> ccAddresses;
		private final List<InternetAddress> bccAddresses;

		private InternetAddress fromAddress;
		private String subject;
		private EmailBody body;


		public Builder()
		{
			this.fromAddress = null;
			this.toAddresses = new ArrayList<>();
			this.ccAddresses = new ArrayList<>();
			this.bccAddresses = new ArrayList<>();
			this.subject = "";
			this.body = EmailBody.fromString("");
		}


		@Override
		public EmailBuilder to(final InternetAddress toAddress)
		{
			if(toAddress == null)
				throw new IllegalArgumentException("TO address cannot be null");

			this.toAddresses.add(toAddress);
			return this;
		}

		@Override
		public EmailBuilder from(final InternetAddress fromAddress)
		{
			if(fromAddress == null)
				throw new IllegalArgumentException("FROM address cannot be null");

			this.fromAddress = fromAddress;
			return this;
		}

		@Override
		public EmailBuilder cc(final InternetAddress ccAddress)
		{
			if(ccAddress == null)
				throw new IllegalArgumentException("CC address cannot be null");

			this.ccAddresses.add(ccAddress);
			return this;
		}

		@Override
		public EmailBuilder bcc(final InternetAddress bccAddress)
		{
			if(bccAddress == null)
				throw new IllegalArgumentException("BCC address cannot be null");

			this.bccAddresses.add(bccAddress);
			return this;
		}

		@Override
		public EmailBuilder subject(final String subject)
		{
			this.subject = subject;
			return this;
		}

		@Override
		public EmailBuilder body(final EmailBody body)
		{
			if(body == null)
				throw new IllegalArgumentException("Email body cannot be null");

			this.body = body;
			return this;
		}

		@Override
		public Email build()
		{
			if(this.fromAddress == null)
				throw new IllegalStateException("FROM address cannot be null");

			if(this.toAddresses.isEmpty())
				throw new IllegalArgumentException("At least one TO address is required");

			return new Email(this);
		}
	}

}
