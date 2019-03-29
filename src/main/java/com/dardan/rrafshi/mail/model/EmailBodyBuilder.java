package com.dardan.rrafshi.mail.model;

import java.io.File;
import java.io.InputStream;

/**
 * Defines a contract for a email body builder. It follows the
 * standard builder pattern
 *
 * @author Dardan Rrafshi
 * @version 0.0.1
 * @since 2019-03-26
 */
public interface EmailBodyBuilder
{
	/**
	 * Sets the content of the email body.
	 *
	 * @param body
	 * 			the body of the email
	 *
	 * @return the builder
	 */
	EmailBodyBuilder content(String body);

	/**
	 * Sets the type of the email body.
	 *
	 * @param type
	 * 			the type of the email
	 *
	 * @return the builder
	 */
	EmailBodyBuilder type(String type);

	/**
	 * Sets the content for the target tag.
	 *
	 * @param tag
	 * 			the position to set the content
	 *
	 * @param content
	 * 			the content to set
	 *
	 * @return the builder
	 */
	EmailBodyBuilder replace(String tag, Object content);

	/**
	 * Sets the delimiter for templates.
	 *
	 * @param delimiter
	 * 			the delimiter of the template
	 *
	 * @return the builder
	 */
	EmailBodyBuilder delimiter(char delimiter);

	/**
	 * Add a file attachment to the email.
	 *
	 * @param file
	 * 			the file to attach
	 *
	 * @return the builder
	 */
	EmailBodyBuilder addFileAttachment(File file);

	/**
	 * Add a file attachment to the email.
	 *
	 * @param stream
	 * 			the input stream of the file
	 *
	 * @param fileName
	 * 			the name of the file
	 *
	 * @param fileType
	 * 			the type of the file
	 * @return
	 */
	EmailBodyBuilder addFileAttachment(InputStream stream, String fileName, String fileType);

	/**
	 * Creates the email body.
	 *
	 * @return an object of the email body
	 */
	EmailBody build();
}
