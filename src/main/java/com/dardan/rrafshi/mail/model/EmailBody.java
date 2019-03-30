package com.dardan.rrafshi.mail.model;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import org.stringtemplate.v4.ST;

import com.dardan.rrafshi.mail.MailException;


public class EmailBody
{
	private final Multipart multipartMail;
	private final String content;
	private final String type;


	protected EmailBody(final Builder builder)
	{
		this.content = builder.content;
		this.type = builder.type;
		this.multipartMail = builder.multipartMail;
	}


	public Multipart getMultipartMail()
	{
		return this.multipartMail;
	}

	public String getContent()
	{
		return this.content;
	}

	public String getType()
	{
		return this.type;
	}


	public static EmailBodyBuilder builder()
	{
		return new EmailBody.Builder();
	}

    public static EmailBody fromString(final String body)
    {
        return builder().content(body).build();
    }


	public static class Builder implements EmailBodyBuilder
	{
		private final Multipart multipartMail;
		private String content;
		private String type;

		private final Map<String, Object> replacements;
		private Character delimiter;


		public Builder()
		{
			this.replacements = new HashMap<>();
			this.type = "text/html";
			this.multipartMail = new MimeMultipart();
		}


		@Override
		public EmailBodyBuilder content(final String body)
		{
			this.content = body;
			return this;
		}

		@Override
		public EmailBodyBuilder type(final String type)
		{
			this.type = type;
			return this;
		}

		@Override
		public EmailBodyBuilder replace(final String tag, final Object content)
		{
			this.replacements.put(tag, content);
			return this;
		}

		@Override
		public EmailBodyBuilder delimiter(final char delimiter)
		{
			this.delimiter = delimiter;
			return this;
		}

		@Override
		public EmailBodyBuilder addFileAttachment(final File file)
		{
			try {
				final MimeBodyPart fileBodyPart = new MimeBodyPart();
				final DataSource source = new FileDataSource(file);
				fileBodyPart.setDataHandler(new DataHandler(source));
				fileBodyPart.setFileName(file.getName());
				this.multipartMail.addBodyPart(fileBodyPart);

			} catch (final MessagingException exception) {

				throw new MailException.FailedAttachingFile("Failed to add file attachment '" + file.getName() + "' to email", exception);
			}
			return this;
		}

		@Override
		public EmailBodyBuilder addFileAttachment(final InputStream stream,
												  final String fileName, final String fileType)
		{
			try {
				final MimeBodyPart fileBodyPart = new MimeBodyPart();
				final DataSource source = new ByteArrayDataSource(stream, fileType);
				fileBodyPart.setDataHandler(new DataHandler(source));
				fileBodyPart.setFileName(fileName);
				this.multipartMail.addBodyPart(fileBodyPart);

			} catch (final IOException exception) {
				throw new MailException.FailedAttachingFile("Failed to add file attachment '" + fileName + "' to email", exception);

			} catch (final MessagingException exception) {
				throw new MailException.FailedAttachingFile("Failed to add file attachment '" + fileName + "' to email", exception);
			}
			return this;
		}

		@Override
		public EmailBody build()
		{
			if(this.content.isEmpty() && this.replacements.size() != 0) {
				ST template = null;

				if(this.delimiter == null)
					template = new ST(this.content);
				else
					template = new ST(this.content, this.delimiter, this.delimiter);

				for(final Entry<String, Object> entry : this.replacements.entrySet())
					template.add(entry.getKey(), entry.getValue());

				this.content = template.render();
			}
			return new EmailBody(this);
		}
	}

}
