package com.iss.junit5.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.sql.SQLException;

import com.iss.junit5.bo.exception.BOException;
import com.iss.junit5.order.dao.OrderDAO;
import com.iss.junit5.order.dto.Order;

class OrderBOImplTest {
	
	@Mock
	OrderDAO dao;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
		OrderBOImpl bo = new OrderBOImpl();
		bo.setDao(dao);
		
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		
		boolean result = bo.placeOrder(order);
		
		assertTrue(result);
	}

	@Test
	void testCancelOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteOrder() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDao() {
		fail("Not yet implemented");
	}

	@Test
	void testSetDao() {
		fail("Not yet implemented");
	}

}
