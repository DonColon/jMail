package com.dardan.rrafshi.mail.model;

import javax.mail.internet.InternetAddress;


public interface EmailBuilder
{
	/**
     * Adds an address to the TO list.
     *
     * @param toAddress
     *            the address to add.
     *
     * @return the builder.
     */
    EmailBuilder to(InternetAddress toAddress);

    /**
     * Sets the from address.
     *
     * @param fromAddress
     *            the from address.
     *
     * @return the builder.
     */
    EmailBuilder from(InternetAddress fromAddress);

    /**
     * Adds an address to the CC list.
     *
     * @param ccAddress
     *            the address to add.
     *
     * @return the builder.
     */
    EmailBuilder cc(InternetAddress ccAddress);

    /**
     * Adds an address to the BCC list.
     *
     * @param bccAddress
     *            the address to add.
     *
     * @return the builder.
     */
    EmailBuilder bcc(InternetAddress bccAddress);

    /**
     * Sets the subject line of the email.
     *
     * @param subject
     *            the subject of the email.
     *
     * @return The builder.
     */
    EmailBuilder subject(String subject);

    /**
     * Sets the body of the email.
     *
     * @param body
     *            the body of the email.
     *
     * @return the builder.
     */
    EmailBuilder body(EmailBody body);

    /**
     * Builds and returns an email.
     *
     * @return an object of the email
     */
    Email build();
}
