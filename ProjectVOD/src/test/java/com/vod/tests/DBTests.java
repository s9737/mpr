package com.vod.tests;

import static org.junit.Assert.*;
import java.sql.SQLException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import com.vod.project.Client;
import com.vod.services.ClientDBManager;

public class DBTests {
	ClientDBManager cldb = new ClientDBManager();
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
		cldb.deleteAllClients();
	}
	
	@Test
	public void testAddClient() throws SQLException {
		cldb.addClient(new Client("PawelG"));
		assertEquals(1, cldb.getAllClients().size());
	}
	@Test
	public void testGetAllClients() throws SQLException {
		cldb.addClient(new Client("PawelG"));
		cldb.addClient(new Client("PawelG"));
		cldb.addClient(new Client("PawelG"));
		assertEquals(3, cldb.getAllClients().size());
	}
	@Test
	public void testclear() throws SQLException {
		cldb.clear();
		assertEquals(0, cldb.getAllClients().size());
	}
	@Test
	public void testFindClientByName() throws SQLException {
		cldb.addClient(new Client("PawelK"));
		assertEquals(1, cldb.FindClientByName("PawelK").size());
		assertTrue(cldb.FindClientByName("PawelK").size() == 1);
	}
	@Test
	public void testdeleteAllClients() throws SQLException 
	{
		cldb.addClient(new Client("PawelK"));
		cldb.addClient(new Client("PawelK"));
		cldb.deleteAllClients();
		assertEquals(0, cldb.getAllClients().size());
	}
}
