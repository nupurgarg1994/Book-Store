package com.nupur.mystore.exception;

public class CategoryException extends Exception {

	public CategoryException(String msg)
	{
		super("CategoryException-"+msg);
	}
	
	public CategoryException(String msg, Throwable c)
	{
		super("CategoryException-"+msg,c);
	}
	
}
