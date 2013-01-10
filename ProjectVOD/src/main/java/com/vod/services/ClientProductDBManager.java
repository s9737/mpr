package com.vod.services;

import java.io.IOException;
import java.sql.*;
import java.util.*;

import com.vod.project.*;

public class ClientProductDBManager {

	private Connection conn;
	private Statement stmt;
	private PreparedStatement addProductToClientStmt;
	private PreparedStatement deleteAllClientProductStmt;
	private PreparedStatement deleteAllProductFromClientStmt;
	private PreparedStatement getProductClientStmt;

	public ClientProductDBManager()  {
		try {
			Properties props = new Properties();
			try {
				props.load(ClassLoader
						.getSystemResourceAsStream("com/empik/jdbc.properties"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			conn = DriverManager.getConnection(props.getProperty("url"));
			stmt = conn.createStatement();
			boolean clientTableExists = false;
			ResultSet rs = conn.getMetaData().getTables(null, null, null, null);
			while (rs.next()) {
				if ("ClientProduct".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					clientTableExists = true;
					break;
				}
			}
			if (!clientTableExists) {
				stmt.executeUpdate("CREATE TABLE clientProduct(client_id int, product_id int, CONSTRAINT client_id_fk FOREIGN KEY (client_id) REFERENCES client (id), CONSTRAINT product_id_fk FOREIGN KEY (product_id) REFERENCES product (id))");
			}
			addProductToClientStmt = conn
					.prepareStatement("INSERT INTO clientProduct (client_id, product_id) VALUES (?, ?)");
			deleteAllClientProductStmt = conn
					.prepareStatement("DELETE FROM clientProduct WHERE client_id = ?");
			deleteAllProductFromClientStmt = conn
					.prepareStatement("DELETE FROM clientProduct");
			getProductClientStmt = conn
					.prepareStatement("SELECT Product.name,Product.price,Product.code FROM Product, ClientProduct WHERE client_id = ? and product_id = Product.id");
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}
	public void addProductToClient(List<Integer> listClientId,
			List<Integer> listProductId) throws java.sql.SQLException {
		try {
			for (Integer clientID : listClientId) {
				for (Integer productID : listProductId) {
					addProductToClientStmt.setInt(1, clientID);
					addProductToClientStmt.setInt(2, productID);
					addProductToClientStmt.executeUpdate();
				}
			}
		} catch (java.sql.SQLException e) {
			e.printStackTrace();
		}
	}

	

}

