package org.kodigo.subscriptions.services.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.dto.OrderDetailDTO;
import org.kodigo.subscriptions.dto.OrderDetailUpdateDTO;
import org.kodigo.subscriptions.entity.OrderDetailEntity;
import org.kodigo.subscriptions.repositories.IOrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final IOrderDetailRepository orderDetailRepository;

    @Override
    public List<OrderDetailEntity> getAllOrderDetails() {
        return List.of();
    }

    @Override
    public List<OrderDetailEntity> getOrderDetailById(Integer orderDetailId) {
        return List.of();
    }

    @Override
    public List<OrderDetailEntity> getOrderDetailByOrderId(Integer orderId) {
        return List.of();
    }

    @Override
    public List<OrderDetailEntity> getOrderDetailByProductId(Integer productId) {
        return List.of();
    }

    @Override
    public OrderDetailEntity createOrderDetail(OrderDetailDTO orderDetailDTO) {
        return null;
    }

    @Override
    public OrderDetailEntity updateOrderDetail(Integer orderDetailId, OrderDetailUpdateDTO orderDetailUpdateDTO) {
        return null;
    }

    @Override
    public void deleteOrderDetail(Integer orderDetailId) {

    }
}
