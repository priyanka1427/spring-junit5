package com.iss.junit5.bo;

import com.iss.junit5.bo.exception.BOException;
import com.iss.junit5.order.dto.Order;

public interface OrderBO {
	
	boolean placeOrder(Order order) throws BOException;
	
	boolean cancelOrder(int id) throws BOException;
	
	boolean deleteOrder(int id) throws BOException;

}
