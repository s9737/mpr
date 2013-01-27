package com.vod.tests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.vod.project.Client;
import com.vod.project.Product;
import com.vod.project.ProductMarks;
import com.vod.services.ClientDBManager;
import com.vod.services.ClientProductDBManager;
import com.vod.services.ProductDBManager;

public class DBTestClientProduct {
	ProductDBManager prodb = new ProductDBManager();
	ClientDBManager cldb = new ClientDBManager();
	ClientProductDBManager clproddb = new ClientProductDBManager();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	clproddb.deleteAllProductFromClient();
	cldb.deleteAllClients();
	prodb.deleteAllProducts();
	}
	
	
	@Test
	public void testAddProductToClient() throws SQLException {
		cldb.addClient(new Client("PawelG"));
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		clproddb.addProductToClient(cldb.FindClientByName("PawelG"), prodb.findProductByCode(321));
		assertEquals(1, clproddb.getClientProduct(cldb.FindClientByName("PawelG")).size());
	}
	@Test
	public void testGetClientProduct() throws SQLException {
		cldb.addClient(new Client("PawelG"));
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		clproddb.addProductToClient(cldb.FindClientByName("PawelG"), prodb.findProductByCode(321));
		assertEquals(1, clproddb.getClientProduct(cldb.FindClientByName("PawelG")).size());
	}
	@Test
	public void testDeleteAllProductFromClient() throws SQLException {
		cldb.addClient(new Client("PawelG"));
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		clproddb.addProductToClient(cldb.FindClientByName("PawelG"), prodb.findProductByCode(321));
		clproddb.deleteAllProductFromClient();
		assertTrue(clproddb.getClientProduct(cldb.FindClientByName("PawelG")).size() == 0);
	}
	@Test
	public void testDeleteAllClientProduct() throws SQLException {
		cldb.addClient(new Client("PawelG"));
		cldb.addClient(new Client("PawelK"));
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		clproddb.addProductToClient(cldb.FindClientByName("PawelG"), prodb.findProductByCode(321));
		clproddb.addProductToClient(cldb.FindClientByName("PawelK"), prodb.findProductByCode(321));
		clproddb.addProductToClient(cldb.FindClientByName("PawelG"), prodb.findProductByCode(321));
		clproddb.deleteAllClientProduct(cldb.FindClientByName("PawelG"));
		clproddb.deleteAllClientProduct(cldb.FindClientByName("PawelK"));
		assertTrue(clproddb.getClientProduct(cldb.FindClientByName("PawelG")).size() == 0);
		assertTrue(clproddb.getClientProduct(cldb.FindClientByName("PawelK")).size() == 0);
	}

}
