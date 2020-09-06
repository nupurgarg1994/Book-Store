package com.nupur.mystore.exception;

public class AdvertException extends Exception
{
	public AdvertException(String msg)
	{
		super("AdvertException-"+ msg);
	}
	
	public AdvertException(String msg, Throwable c)
	{
		super("AdvertException-"+ msg,c);
	}
}