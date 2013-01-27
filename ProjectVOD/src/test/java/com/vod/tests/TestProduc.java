package com.vod.tests;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.vod.project.CodeException;
import com.vod.project.PriceException;
import com.vod.project.Product;
import com.vod.project.ProductMarks;

public class TestProduc {
	private Product pro=new Product(ProductMarks.Metalica,15);
	private Product opro=new Product(ProductMarks.Metalica,16);
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUsp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSetPrice() throws PriceException {
		pro.setPrice(55);
		opro.setPrice(55);
		assertTrue(pro.getPrice()==opro.getPrice());
	}
	@Test
	public void testGetName() {
		pro.setName(ProductMarks.Muppety);
		opro.setName(ProductMarks.Muppety);
		assertSame(pro.getName(),opro.getName());
	}
	@Test
	public void testGetPrice() throws PriceException {
		pro.setPrice(55);
		opro.setPrice(55);
		assertTrue(pro.getPrice()==opro.getPrice());
	}
	@Test
	public void testSetName() {
		
		pro.setName(ProductMarks.O_polnocy_w_Paryzu  );
		assertSame(pro.getName(),(ProductMarks.O_polnocy_w_Paryzu  ));
	}
	@Test
	public void testGetCode() throws CodeException {
		pro.setCode(123);
		assertTrue(pro.getCode()==123);
	}
	@Test
	public void testSetCode() throws CodeException {
		pro.setCode(1234);
		assertTrue(pro.getCode()==1234);
	}
}
