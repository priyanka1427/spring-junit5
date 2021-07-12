package com.iss.junit5.bo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
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
	
	private static final int ORDER_ID = 123;

	@Mock
	OrderDAO dao;
	
	OrderBOImpl bo = new OrderBOImpl();
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		bo.setDao(dao);
		
	}

	@Test
	void placeOrder_Should_Create_An_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(1));
		
		boolean result = bo.placeOrder(order);
		
		assertTrue(result);
	}
	
	@Test
	void placeOrder_Should_Not_Create_An_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenReturn(new Integer(0));
		
		boolean result = bo.placeOrder(order);
		
		assertFalse(result);
	}

	@Test
	void placeOrder_Should_Throw_BOException() throws SQLException, BOException {
		Order order = new Order();
		when(dao.create(order)).thenThrow(SQLException.class);
		Assertions.assertThrows(BOException.class, ()->{
			boolean result = bo.placeOrder(order);
		});
	}
	
	@Test
	void cancelOrder_Should_Cancel_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(ORDER_ID)).thenReturn(order);
		when(dao.update(order)).thenReturn(new Integer(1));
		boolean result = bo.cancelOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
	}
	
	@Test
	void cancelOrder_Should_Not_Cancel_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(new Integer(0));
		boolean result = bo.cancelOrder(ORDER_ID);
		assertFalse(result);
		verify(dao).read(ORDER_ID);
		verify(dao).update(order);
	}
	
	@Test
	void cancelOrder_ShouldThrowABOExceptionOnRead() throws SQLException, BOException {
		Order order = new Order();
		Assertions.assertThrows(BOException.class, ()->{
			when(dao.read(ORDER_ID)).thenThrow(SQLException.class);
			bo.cancelOrder(ORDER_ID);
		});
	}
	@Test
	void cancelOrder_ShouldThrowABOExceptionOnUpdate() throws SQLException, BOException {
		Order order = new Order();
		Assertions.assertThrows(BOException.class, ()->{
			when(dao.read(ORDER_ID)).thenReturn(order);
			when(dao.update(order)).thenThrow(SQLException.class);
			bo.cancelOrder(ORDER_ID);
		});
	}

	@Test
	public void deleteOrder_Should_delete_the_order() throws SQLException, BOException {
		when(dao.delete(123)).thenReturn(1);
		boolean result = bo.deleteOrder(ORDER_ID);
		assertTrue(result);
		verify(dao).delete(ORDER_ID);
	}
}
