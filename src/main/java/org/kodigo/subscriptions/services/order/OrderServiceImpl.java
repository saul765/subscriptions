package org.kodigo.subscriptions.services.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.dto.OrderDTO;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.mapper.OrderMapper;
import org.kodigo.subscriptions.repositories.IOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@AllArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getAllOrders() {
        log.info("[Service]: Starting execution getAllOrders");

        List<OrderDTO> response = orderRepository.findAll()
                .stream()
                .map(orderMapper::entityToDto)
                .collect(Collectors.toList());

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Order details not found");
        }

        log.info("[Service]: Finishing execution getAllOrders");
        return response;
    }

    @Override
    public List<OrderDTO> getOrderById(Integer orderId) {
        log.info("[Service]: Starting execution getOrderById");

        List<OrderDTO> response = orderRepository.findById(orderId.longValue())
                .stream()
                .map(orderMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Order not found");
        }

        log.info("[Service]: Finishing execution getOrderById");
        return response;
    }

    @Override
    public List<OrderDTO> getOrderBySubscriptionId(Integer subscriptionId) {
        log.info("[Service]: Starting execution getOrderBySubscriptionId");

        List<OrderDTO> response = orderRepository.findBySubscriptionId(subscriptionId.longValue())
                .stream()
                .map(orderMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Order not found");
        }
        log.info("[Service]: Finishing execution getOrderBySubscriptionId");
        return response;
    }

    @Override
    public List<OrderDTO> getOrderByCustomerId(Integer customerId) {
        log.info("[Service]: Starting execution getOrderByCustomerId");

        List<OrderDTO> response = orderRepository.findByCustomerId(customerId.longValue())
                .stream()
                .map(orderMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Order not found");
        }

        log.info("[Service]: Finishing execution getOrderByCustomerId");
        return response;
    }


    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return null;
    }


    @Override
    public void deleteOrder(Integer orderId) {
        log.info("[Service]: Starting execution deleteOrder");
        orderRepository.findById(orderId.longValue())
                .map(order -> {
                    orderRepository.delete(order);
                    return order;
                })
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Order not found"));

        log.info("[Service]: Finishing execution deleteOrder");

    }
}
