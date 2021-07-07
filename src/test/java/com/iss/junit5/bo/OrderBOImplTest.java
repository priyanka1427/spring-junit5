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
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(new Integer(1));
		boolean result = bo.cancelOrder(123);
		assertTrue(result);
		verify(dao).read(123);
		verify(dao).update(order);
	}
	
	@Test
	void cancelOrder_Should_Not_Cancel_Order() throws SQLException, BOException {
		Order order = new Order();
		when(dao.read(123)).thenReturn(order);
		when(dao.update(order)).thenReturn(new Integer(0));
		boolean result = bo.cancelOrder(123);
		assertFalse(result);
		verify(dao).read(123);
		verify(dao).update(order);
	}
	
	@Test
	void cancelOrder_ShouldThrowABOExceptionOnRead() throws SQLException, BOException {
		Order order = new Order();
		Assertions.assertThrows(BOException.class, ()->{
			when(dao.read(123)).thenThrow(SQLException.class);
			bo.cancelOrder(123);
		});
	}
	@Test
	void cancelOrder_ShouldThrowABOExceptionOnUpdate() throws SQLException, BOException {
		Order order = new Order();
		Assertions.assertThrows(BOException.class, ()->{
			when(dao.read(123)).thenReturn(order);
			when(dao.update(order)).thenThrow(SQLException.class);
			bo.cancelOrder(123);
		});
	}

}
