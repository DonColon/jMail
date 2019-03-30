package com.dardan.rrafshi.mail;

import com.dardan.rrafshi.commons.exceptions.ApplicationException;
import com.dardan.rrafshi.commons.exceptions.ApplicationRuntimeException;


public final class MailException
{
	public static class FailedSendingMail extends ApplicationException
	{
		private static final long serialVersionUID = 1L;


		public FailedSendingMail(final String message, final Throwable cause)
		{
			super(message, cause);
		}

		public FailedSendingMail(final String message)
		{
			super(message);
		}
	}

	public static class FailedAttachingFile extends ApplicationRuntimeException
	{
		private static final long serialVersionUID = 1L;


		public FailedAttachingFile(final String message, final Throwable cause)
		{
			super(message, cause);
		}

		public FailedAttachingFile(final String message)
		{
			super(message);
		}
	}

	public static class FailedAttachingBody extends ApplicationRuntimeException
	{
		private static final long serialVersionUID = 1L;


		public FailedAttachingBody(final String message, final Throwable cause)
		{
			super(message, cause);
		}

		public FailedAttachingBody(final String message)
		{
			super(message);
		}
	}
}
