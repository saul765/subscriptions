package org.kodigo.subscriptions.services.order;

import org.kodigo.subscriptions.dto.OrderDetailDTO;
import org.kodigo.subscriptions.dto.OrderDetailUpdateDTO;
import org.kodigo.subscriptions.entity.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailService {

    List<OrderDetailEntity> getAllOrderDetails();

    List<OrderDetailEntity> getOrderDetailById(Integer orderDetailId);

    List<OrderDetailEntity> getOrderDetailByOrderId(Integer orderId);

    List<OrderDetailEntity> getOrderDetailByProductId(Integer productId);

    OrderDetailEntity createOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetailEntity updateOrderDetail(Integer orderDetailId, OrderDetailUpdateDTO orderDetailUpdateDTO);

    void deleteOrderDetail(Integer orderDetailId);

}
