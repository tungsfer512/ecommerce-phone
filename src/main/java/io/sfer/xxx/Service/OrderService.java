package io.sfer.xxx.Service;

import io.sfer.xxx.Model.OrderInfo;
import io.sfer.xxx.Respository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
	
	@Autowired
	OrderRepository orderRepository;
	
	public List<OrderInfo> getAllOrder() {
		return orderRepository.findAll();
	}
	
	public OrderInfo getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}
	
	public OrderInfo addOrder(OrderInfo order) {
		return orderRepository.save(order);
	}
	
	public void deleteOrderById(Long id) {
		orderRepository.deleteById(id);
	}
	
	public OrderInfo updateOrder(OrderInfo order) {
		return orderRepository.findById(order.getId()).map(existedOrder -> {
			existedOrder.setAddress(order.getAddress());
			existedOrder.setEmail(order.getEmail());
			existedOrder.setCity(order.getCity());
			existedOrder.setCountry(order.getCountry());
			existedOrder.setFirst_name(order.getFirst_name());
			existedOrder.setLast_name(order.getLast_name());
			existedOrder.setPhone(order.getPhone());
			existedOrder.setZip(order.getZip());
			return orderRepository.save(existedOrder);
		}).orElseGet(() -> {
			return orderRepository.save(order);
		});
	}
}
