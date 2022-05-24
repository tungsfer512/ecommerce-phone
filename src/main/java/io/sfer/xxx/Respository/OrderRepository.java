package io.sfer.xxx.Respository;

import io.sfer.xxx.Model.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderInfo, Long> {
}
