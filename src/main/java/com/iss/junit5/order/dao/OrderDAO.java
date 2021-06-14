package com.iss.junit5.order.dao;

import java.sql.SQLException;

import com.iss.junit5.order.dto.Order;

public interface OrderDAO {
	
	int create(Order order) throws SQLException;
	
	Order read(int id) throws SQLException;
	
	int update(Order order) throws SQLException;
	
	int delete(int id) throws SQLException;

}
