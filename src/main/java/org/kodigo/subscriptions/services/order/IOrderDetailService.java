package org.kodigo.subscriptions.services.order;

import org.kodigo.subscriptions.dto.OrderDetailDTO;
import org.kodigo.subscriptions.dto.OrderDetailUpdateDTO;
import org.kodigo.subscriptions.entity.OrderDetailEntity;

import java.util.List;

public interface IOrderDetailService {

    List<OrderDetailDTO> getAllOrderDetails();

    List<OrderDetailDTO> getOrderDetailById(Integer orderDetailId);

    List<OrderDetailDTO> getOrderDetailByOrderId(Integer orderId);

    List<OrderDetailDTO> getOrderDetailByProductId(Integer productId);

    OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO);

    OrderDetailDTO updateOrderDetail(Integer orderDetailId, OrderDetailUpdateDTO orderDetailUpdateDTO);

    void deleteOrderDetail(Integer orderDetailId);

}
