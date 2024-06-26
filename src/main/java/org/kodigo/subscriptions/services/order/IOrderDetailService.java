package org.kodigo.subscriptions.services.order;

import org.kodigo.subscriptions.dto.OrderDetailDTO;
import org.kodigo.subscriptions.dto.OrderDetailUpdateDTO;
import org.kodigo.subscriptions.dto.SubscriptionProductDTO;
import org.kodigo.subscriptions.entity.OrderEntity;
import org.kodigo.subscriptions.entity.ProductEntity;

import java.util.List;

public interface IOrderDetailService {

    List<OrderDetailDTO> getAllOrderDetails();

    List<OrderDetailDTO> getOrderDetailById(Integer orderDetailId);

    List<OrderDetailDTO> getOrderDetailByOrderId(Integer orderId);

    List<OrderDetailDTO> getOrderDetailByProductId(Integer productId);

    void createOrderDetail(SubscriptionProductDTO subscriptionProductDTO, OrderEntity orderEntity);

    OrderDetailDTO updateOrderDetail(Integer orderDetailId, OrderDetailUpdateDTO orderDetailUpdateDTO);

    void deleteOrderDetail(Integer orderDetailId);

}
