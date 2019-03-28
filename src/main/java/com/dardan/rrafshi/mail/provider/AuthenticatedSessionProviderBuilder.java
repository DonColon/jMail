package com.dardan.rrafshi.mail.provider;


/**
 * Defines a contract for a basic authenticated SessionProvider builder.
 * It follows the standard builder pattern.
 *
 * @author Dardan Rrafshi
 * @version 0.0.1
 * @since 2019-03-26
 */
public interface AuthenticatedSessionProviderBuilder extends SessionProviderBuilder
{
	/**
	 * Gets the username
	 *
	 * @return username
	 */
	String getUsername();

	/**
	 * Gets the password
	 *
	 * @return password
	 */
	String getPassword();
}
