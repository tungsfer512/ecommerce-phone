package io.sfer.xxx.DTO;

import io.sfer.xxx.Model.OrderProduct;
import io.sfer.xxx.Model.Product;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class OrderDTO {
	Product products;
	OrderProduct orderProducts;
}
