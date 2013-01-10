package com.vod.project;

import java.sql.SQLException;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.Logger;

import com.vod.events.EventDesk;
import com.vod.events.EventProductProcesses;
import com.vod.events.EventDesk.ChangeBoxProduct;
import com.vod.events.EventDesk.CleanProduct;
import com.vod.events.EventDesk.PromoteProduct;
import com.vod.events.EventDesk.RollbackProduct;
import com.vod.services.ClientDBManager;
import com.vod.services.ClientProductDBManager;
import com.vod.services.Condition;
import com.vod.services.ProductDBManager;

public class Main {

	private static Logger logger = Logger.getLogger(Main.class);

	public static void main(String[] args) throws PriceException, SQLException {

		PropertyConfigurator.configure("Log4J.properties");

		Client cPW = new Client("Pawe³ Gorniak");

		

}
