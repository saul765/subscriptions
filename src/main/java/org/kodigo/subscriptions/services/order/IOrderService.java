package org.kodigo.subscriptions.services.order;

import org.kodigo.subscriptions.dto.OrderDTO;

import java.util.List;

public interface IOrderService {

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrderById(Integer orderId);

    List<OrderDTO> getOrderBySubscriptionId(Integer subscriptionId);

    List<OrderDTO> getOrderByCustomerId(Integer customerId);

    OrderDTO createOrder(OrderDTO orderDTO);

    void deleteOrder(Integer orderId);
}
