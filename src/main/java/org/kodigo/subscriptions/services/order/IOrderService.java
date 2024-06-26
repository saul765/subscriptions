package org.kodigo.subscriptions.services.order;

import org.kodigo.subscriptions.dto.OrderDTO;
import org.kodigo.subscriptions.entity.CustomerEntity;
import org.kodigo.subscriptions.entity.OrderEntity;
import org.kodigo.subscriptions.entity.SubscriptionEntity;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface IOrderService {

    List<OrderDTO> getAllOrders();

    List<OrderDTO> getOrderById(Integer orderId);

    List<OrderDTO> getOrderBySubscriptionId(Integer subscriptionId);

    List<OrderDTO> getOrderByCustomerId(Integer customerId);

    List<OrderDTO> getOrderByOrderDate(String orderDate) throws ParseException;

    OrderEntity createOrder(CustomerEntity customer, SubscriptionEntity subscription, Date orderDate);

    void deleteOrder(Integer orderId);
}
