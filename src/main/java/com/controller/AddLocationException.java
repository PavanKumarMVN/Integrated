package com.controller;

public class AddLocationException extends RuntimeException
{
	public AddLocationException()
	{
		super("Locationid cannot be added due to missing details");
	}

	@Override
	public String toString() 
	{
		return "Location is not added, some data is missing";
	}
}
