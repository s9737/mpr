package com.vod.project;

import java.util.ArrayList;

import org.apache.log4j.Category;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import java.util.List;

public class Client {

	private PropertyConfigurator logConfig = new PropertyConfigurator();
	private Logger logger = Logger.getLogger(Client.class);

	public String name;
	public List<Product> products=new ArrayList<Product>();
	
	
	
	}
