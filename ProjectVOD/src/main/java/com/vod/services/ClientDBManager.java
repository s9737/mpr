package com.vod.services;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.vod.project.Client;

public class ClientDBManager {
	private Connection conn;
	private Statement stmt;
	private PreparedStatement addClientStmt;
	private PreparedStatement getClientStmt;
	private PreparedStatement findClientStmt;
	public PreparedStatement deleteClientStmt;

	public ClientDBManager() {
		Properties props = new Properties();
		try {
			props.load(ClassLoader
					.getSystemResourceAsStream("com/empik/jdbc.properties"));
			conn = DriverManager.getConnection(props.getProperty("url"), props);
			boolean clientTableExists = false;
			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			stmt = conn.createStatement();
			while (rs.next()) {
				if ("Client".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					clientTableExists = true;
					break;
				}
			}
			if (!clientTableExists) {
				stmt.executeUpdate(""
						+ "CREATE TABLE client("
						+ "id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,"
						+ "name varchar(20)," + "" + ")");
			}
			addClientStmt = conn.prepareStatement(""
					+ "INSERT INTO Client (name) VALUES (?)" + "");
			getClientStmt = conn.prepareStatement("" + "SELECT * FROM Client"
					+ "");
			deleteClientStmt = conn.prepareStatement("" + "DELETE FROM Client"
					+ "");
			findClientStmt = conn
					.prepareStatement("SELECT id FROM Client WHERE name = ?");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addClient(Client c) throws java.sql.SQLException, SQLException {
		addClientStmt.setString(1, c.getName());
		addClientStmt.executeUpdate();

	}

	public List<Client> getAllClients() throws java.sql.SQLException,
			SQLException {
		List<Client> Clients = new ArrayList<Client>();
		ResultSet rs = getClientStmt.executeQuery();
		while (rs.next()) {
			Clients.add(new Client(rs.getString("name")));
		}
		return Clients;
	}
	public void clear() throws java.sql.SQLException {
		ResultSet rs = getClientStmt.executeQuery();
		while (rs.next())
			deleteClientStmt.executeUpdate();
	}
	public List<Integer> FindClientByName(String name)
			throws java.sql.SQLException {
		try {
			List<Integer> result = new ArrayList<Integer>();
			findClientStmt.setString(1, name);
			ResultSet rs = findClientStmt.executeQuery();
			while (rs.next())
				result.add(rs.getInt("ID"));
			return result;
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public void deleteAllClients() 
	{
		try 
		{
			deleteClientStmt.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
