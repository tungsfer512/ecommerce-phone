package io.sfer.xxx.Controller;

import io.sfer.xxx.DTO.OrderDTO;
import io.sfer.xxx.Model.OrderProduct;
import io.sfer.xxx.Model.Product;
import io.sfer.xxx.Service.OrderProductService;
import io.sfer.xxx.Service.OrderService;
import io.sfer.xxx.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminOrderController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	OrderProductService orderProductService;
	@Autowired
	ProductService productService;
	
	@GetMapping("/admin/orders")
	public String getAllOrder(Model model) {
		model.addAttribute("orders", orderService.getAllOrder());
		return "admin/orders";
	}

	@GetMapping("/admin/orders/delete/{id}")
	public String deleteOrder(@PathVariable Long id) {
		orderService.deleteOrderById(id);
		return "redirect:/admin/orders";
	}
	@GetMapping("/admin/orders/{id}")
	public String orderDetail(Model model,@PathVariable Long id){
		model.addAttribute("order",orderService.getOrderById(id));
		List<OrderProduct> orderProductList = orderProductService.getProductOrderByIdOrder(id);
		List<OrderDTO> products  = new ArrayList<>();
		OrderDTO orderDTO = new OrderDTO();
		Long totalSum= 0L;
		for(OrderProduct x : orderProductList) {
			Product product = productService.getProductById(x.getProduct_id());
			totalSum += (x.getQuantity() * product.getPrice());
			orderDTO.setProducts(product);
			orderDTO.setOrderProducts(x);
			products.add(orderDTO);
		}
//		System.out.println(products);
//		System.out.println(orderProductList);
		model.addAttribute("totalPrice",totalSum);
		
		model.addAttribute("productList",products);
		
		return "admin/orderDetail";
	}
}
