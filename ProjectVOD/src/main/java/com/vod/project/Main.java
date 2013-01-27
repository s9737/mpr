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

		System.out.println("-----------------------------------------------|");
		cPW.addProduct(new Product(ProductMarks.O_polnocy_w_Paryzu  , (double) 9));
		cPW.addProduct(new Product(ProductMarks.Operacja_Geronimo, (double) 49));
		cPW.addProduct(new Product(ProductMarks.Muppety, (double) 205));
		cPW.printProducts();
		System.out.println("-----------------------------------------------|");
		try {
			cPW.addProduct(new Product(ProductMarks.O_polnocy_w_Paryzu  , (double) -7));

		} catch (PriceException e) {
			logger.error(e.getMessage());
		}
		cPW.printProducts();
		System.out.println("-----------------------------------------------|");
		cPW.addProduct(new Product(ProductMarks.Operacja_Geronimo , (double) 0));
		cPW.checkPrice(cPW.findProduct(ProductMarks.Operacja_Geronimo ));
		cPW.deleteProduct(cPW.findProduct(ProductMarks.O_polnocy_w_Paryzu  ));
		cPW.printProducts();
		System.out.println("-----------------------------------------------|");

		Product p = new Product(ProductMarks.Metalica, (double) 15);
		try {
			p.setPrice(-2);
		} catch (PriceException e) {

			logger.error(e);
			logger.fatal(e);
			logger.info(e);
			logger.warn(e);
		}
		System.out.println(p.getName() + " cena: " + p.getPrice());
		System.out.println("-----------------------------------------------|");

		Client dPK = new Client("Pawe³ Kowalski");
		dPK.addProduct(new Product(ProductMarks.Operacja_Geronimo ,
				(float) 1990));
		dPK.addProduct(new Product(ProductMarks.Operacja_Geronimo,
				(float) 1990));
		dPK.FindAllProductsByCode(1990);
		dPK.DeleteManyProductsByCode(1990);
		System.out.println(".....................");
		dPK.FindAllProductsByCode(1990);
		System.out.println("..........................................");

		System.out.println("..........................................");
		EventDesk desk = new EventDesk();
		EventProductProcesses clean = new CleanProduct();
		EventProductProcesses change = new ChangeBoxProduct();
		EventProductProcesses promote = new PromoteProduct();
		EventProductProcesses rollback = new RollbackProduct();
		Product z = new Product(ProductMarks.June, (double) 17);
		Product x = new Product(ProductMarks.Operacja_Geronimo , (double) 95,
				(float) 1990);
		try {
			z.setCode(1234);
		} catch (CodeException e) {

			e.printStackTrace();
		}
		z.setBox();
		desk.addProcess(clean);
		desk.addProcess(change);
		desk.addProcess(promote);
		desk.addProcess(rollback);
		desk.setProduct(z);
		desk.executeProcesses();
		ClientDBManager cdb = new ClientDBManager();
		cdb.addClient(cPW);
		cdb.addClient(dPK);
		for (Client client : cdb.getAllClients()) {
			System.out.println(client.getName());
		}
		System.out.println("..........................................");

		ProductDBManager pdb = new ProductDBManager();
		pdb.addProduct(z);
		pdb.addProduct(x);

		ClientProductDBManager dbClientProduct = new ClientProductDBManager();
		dbClientProduct.addProductToClient(cdb.FindClientByName(cPW.getName()),
				pdb.findProductByName(ProductMarks.June));
		dbClientProduct.addProductToClient(cdb.FindClientByName(dPK.getName()),
				pdb.findProductByCode(1990));

		System.out.println("..........................................");
		System.out.println("Pawe³ Gorniak");
		for (Product product : dbClientProduct.getClientProduct(cdb
				.FindClientByName("Pawe³ Gorniak"))) {
			System.out.println("Nazwa: " + product.getName2().toString()
					+ "\tKod: " + product.getCode() + "\tCena: "
					+ product.getPrice());

		}
		System.out.println("..........................................");
		System.out.println("Pawe³ Kowalski");
		for (Product product : dbClientProduct.getClientProduct(cdb
				.FindClientByName("Pawe³ Kowalski"))) {
			System.out.println("Nazwa: " + product.getName2().toString()
					+ "\tKod: " + product.getCode() + "\tCena: "
					+ product.getPrice());
		}

		System.out.println("Wszystkie produkty powyzej 50 zl‚");
		pdb.printProductWithCondition(pdb.getAllProducts(), new Condition() {
			@Override
			public boolean getCondition(Product p) {
				if (p.getPrice() > 50)
					return true;
				return false;
			}
		});
	}

}
