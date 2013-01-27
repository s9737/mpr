package com.vod.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vod.project.Client;
import com.vod.project.CodeException;
import com.vod.project.PriceException;
import com.vod.project.Product;
import com.vod.project.ProductMarks;

public class TestsClient {
	private Client cpw=new Client("Paweł Gorniak");
	private Product pr=new Product(ProductMarks.Metalica,15);

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClearlist() {
		cpw.clearlist();
		assertTrue(cpw.products.size()<=0);
	}
	@Test
	public void testAddProduct() throws PriceException {
		cpw.addProduct(pr);
		assertNotNull(cpw.products);
		
	}
	@Test
	public void testDeleteProduct() {
		cpw.deleteProduct(pr);
		assertTrue(cpw.products.size()<=0);
	}
	@Test
	public void testCheckPrice() throws PriceException {
		cpw.checkPrice(pr);
		assertTrue(pr.getPrice()>0);
	}
	@Test
	public void testFindProduct() {
		cpw.findProduct(ProductMarks.O_polnocy_w_Paryzu  );
		assertTrue(cpw.products.size()==0);
	}
	@Test
	public void testClient() throws PriceException {
		Client n=new Client("PawełN");
		n.addProduct(pr);
		assertFalse(n.products.size()<0);
	}
	@Test
	public void testDeleteManyProductsByCode() throws PriceException, CodeException{
		
		cpw.addProduct(new Product(ProductMarks.Operacja_Geronimo ,(float)123));
		cpw.addProduct(new Product(ProductMarks.Operacja_Geronimo,(float)123));
		cpw.DeleteManyProductsByCode(123);
		assertTrue(cpw.products.size()==0);
		
	}
	@Test
	(expected=PriceException.class, timeout=100)
	public void testPriceException() throws PriceException
	{
	new Product(ProductMarks.Operacja_Geronimo,155).setPrice(-8);
	}
	
	}