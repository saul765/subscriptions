package org.kodigo.subscriptions.services.order;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kodigo.subscriptions.dto.OrderDetailDTO;
import org.kodigo.subscriptions.dto.OrderDetailUpdateDTO;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.mapper.OrderDetailMapper;
import org.kodigo.subscriptions.repositories.IOrderDetailRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class OrderDetailServiceImpl implements IOrderDetailService {

    private final IOrderDetailRepository orderDetailRepository;

    private final OrderDetailMapper orderDetailMapper;


    @Override
    public List<OrderDetailDTO> getAllOrderDetails() {
        log.info("[Service]: Starting execution getAllOrderDetails");
        List<OrderDetailDTO> response = orderDetailRepository.findAll()
                .stream()
                .map(orderDetailMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Order details not found");
        }

        log.info("[Service]: Finishing execution getAllOrderDetails");
        return response;
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailById(Integer orderDetailId) {
        log.info("[Service]: Starting execution getOrderDetailById");
        List<OrderDetailDTO> response = orderDetailRepository.findById(orderDetailId.longValue())
                .stream()
                .map(orderDetailMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Order detail not found");
        }

        log.info("[Service]: Finishing execution getOrderDetailById");
        return response;
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailByOrderId(Integer orderId) {
        log.info("[Service]: Starting execution getOrderDetailByOrderId");
        List<OrderDetailDTO> response = orderDetailRepository.findByOrderId(orderId.longValue())
                .stream()
                .map(orderDetailMapper::entityToDto)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Order detail not found");
        }

        log.info("[Service]: Finishing execution getOrderDetailByOrderId");
        return response;
    }

    @Override
    public List<OrderDetailDTO> getOrderDetailByProductId(Integer productId) {
        log.info("[Service]: Starting execution getOrderDetailByProductId");
        List<OrderDetailDTO> response = orderDetailRepository.findByProductId(productId.longValue())
                .stream()
                .map(orderDetailMapper::entityToDto)
                .toList();

        log.info("[Service]: Finishing execution getOrderDetailByProductId");
        return response;
    }

    @Override
    public OrderDetailDTO createOrderDetail(OrderDetailDTO orderDetailDTO) {
        log.info("[Service]: Starting execution createOrderDetail");
        return null;
    }

    @Override
    public OrderDetailDTO updateOrderDetail(Integer orderDetailId, OrderDetailUpdateDTO orderDetailUpdateDTO) {
        log.info("[Service]: Starting execution updateOrderDetail");
        OrderDetailDTO response = orderDetailRepository.findById(orderDetailId.longValue())
                .map(orderDetail -> {
                    boolean isUpdated = false;
                    if (orderDetailUpdateDTO.getQuantity() != null && !orderDetail.getQuantity().equals(orderDetailUpdateDTO.getQuantity())) {
                        orderDetail.setQuantity(orderDetailUpdateDTO.getQuantity());
                        isUpdated = true;
                    }
                    if (orderDetailUpdateDTO.getDiscount() != null && !orderDetail.getDiscount().equals(orderDetailUpdateDTO.getDiscount())) {
                        orderDetail.setDiscount(orderDetailUpdateDTO.getDiscount());
                        isUpdated = true;
                    }
                    if (isUpdated) {
                        Double total = orderDetail.getQuantity() * orderDetail.getPrice();
                        Double subtotal = total - orderDetail.getDiscount();
                        orderDetail.setTotal(total);
                        orderDetail.setSubtotal(subtotal);
                    }
                    return orderDetail;
                })
                .map(orderDetailRepository::save)
                .map(orderDetailMapper::entityToDto)
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Order detail not found"));

        log.info("[Service]: Finishing execution updateOrderDetail");
        return response;
    }


    @Override
    public void deleteOrderDetail(Integer orderDetailId) {
        log.info("[Service]: Starting execution deleteOrderDetail");
        orderDetailRepository.findById(orderDetailId.longValue())
                .map(orderDetail -> {
                    orderDetailRepository.delete(orderDetail);
                    return orderDetail;
                })
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Order detail not found"));
        log.info("[Service]: Finishing execution deleteOrderDetail");
    }
}
