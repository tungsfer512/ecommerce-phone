package io.sfer.xxx.Service;

import io.sfer.xxx.Model.OrderProduct;
import io.sfer.xxx.Respository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService {
	
	@Autowired
	OrderProductRepository orderProductRepository;
	
	public OrderProduct addOrderProduct(OrderProduct orderProduct) {
		return orderProductRepository.save(orderProduct);
	}
	public List<OrderProduct> getProductOrderByIdOrder(Long id){
		return orderProductRepository.getAllOrderProductByUserId(id);
	}
}
