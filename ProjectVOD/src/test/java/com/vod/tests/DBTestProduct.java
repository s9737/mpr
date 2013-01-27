package com.vod.tests;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.vod.project.*;
import com.vod.services.*;

public class DBTestProduct {

	ProductDBManager prodb = new ProductDBManager();

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
	prodb.deleteAllProducts();	
	}

	@Test
	public void testAddProduct() throws SQLException  {
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		prodb.addProduct(new Product(ProductMarks.June,47,12357245));
		assertEquals(2, prodb.getAllProducts().size());
	}
	@Test
	public void testGetAllProducts() throws SQLException {
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		assertEquals(1, prodb.getAllProducts().size());
	}
	@Test
	public void testFindProductByName() throws SQLException {
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,321));
		prodb.addProduct(new Product(ProductMarks.June,47,12357245));
		assertTrue(prodb.findProductByName(ProductMarks.June).size() == 1);
	}
	@Test
	public void testFindProductByCode() throws SQLException {
		prodb.addProduct(new Product(ProductMarks.Operacja_Geronimo ,65,12112246));
		assertEquals(1, prodb.findProductByCode(12112246).size());
	}
}