package com.dardan.rrafshi.mail.provider;

/**
 * Defines a contract for a basic SessionProvider builder. It follows the
 * standard builder pattern
 *
 * @author Dardan Rrafshi
 * @version 0.0.1
 * @since 2019-03-26
 */
public interface SessionProviderBuilder
{
	/**
	 * Gets the host of the session
	 *
	 * @return host
	 */
	String getHost();

	/**
	 * Gets the port of the session
	 *
	 * @return port
	 */
	String getPort();
}
