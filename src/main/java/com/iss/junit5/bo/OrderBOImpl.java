package com.iss.junit5.bo;

import com.iss.junit5.bo.exception.BOException;
import com.iss.junit5.order.dao.OrderDAO;
import com.iss.junit5.order.dto.Order;

public class OrderBOImpl implements OrderBO {
	
	OrderDAO dao;
	
	@Override
	public boolean placeOrder(Order order) throws BOException {
		return false;
	}

	@Override
	public boolean cancelOrder(int id) throws BOException {
		return false;
	}

	@Override
	public boolean deleteOrder(int id) throws BOException {
		return false;
	}

}
