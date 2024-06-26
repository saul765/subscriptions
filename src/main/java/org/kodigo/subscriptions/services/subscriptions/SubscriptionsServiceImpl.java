package org.kodigo.subscriptions.services.subscriptions;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.kodigo.subscriptions.dto.SubscriptionDTO;
import org.kodigo.subscriptions.dto.request.CreateSubscriptionRequest;
import org.kodigo.subscriptions.entity.SubscriptionEntity;
import org.kodigo.subscriptions.enums.ResponseCodeEnum;
import org.kodigo.subscriptions.exception.AppException;
import org.kodigo.subscriptions.mapper.SubscriptionsMapper;
import org.kodigo.subscriptions.repositories.ISubscriptionRepository;
import org.kodigo.subscriptions.services.customer.ICustomerService;
import org.kodigo.subscriptions.services.merchant.IMerchantService;
import org.kodigo.subscriptions.services.order.IOrderDetailService;
import org.kodigo.subscriptions.services.order.IOrderService;
import org.kodigo.subscriptions.utils.interfaces.IDateService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class SubscriptionsServiceImpl implements ISubscriptionService {

    private final ISubscriptionRepository subscriptionRepository;

    private final IOrderDetailService orderDetailService;

    private final IOrderService orderService;

    private final ICustomerService customerService;

    private final IMerchantService merchantService;

    private final SubscriptionsMapper subscriptionsMapper;

    private final IDateService dateService;

    private static final String SUBSCRIPTION_DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        log.info("[Service]: Starting execution getAllSubscriptions");

        List<SubscriptionDTO> response = subscriptionRepository.findAll()
                .stream()
                .map(subscriptionsMapper::entityToDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Subscriptions not found");
        }

        log.info("[Service]: Finishing execution getAllSubscriptions");
        return response;

    }

    @Override
    public List<SubscriptionDTO> getSubscriptionsByCustomerId(Integer customerId) {
        log.info("[Service]: Starting execution getSubscriptionsByCustomerId");

        List<SubscriptionDTO> response = subscriptionRepository.findByCustomerId(customerId.longValue())
                .stream()
                .map(subscriptionsMapper::entityToDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Subscriptions not found");
        }

        log.info("[Service]: Finishing execution getSubscriptionsByCustomerId");
        return response;
    }

    @Override
    public List<SubscriptionDTO> getSubscriptionsById(Integer subscriptionId) {
        log.info("[Service]: Starting execution getSubscriptionsById");

        List<SubscriptionDTO> response = subscriptionRepository.findById(subscriptionId.longValue())
                .stream()
                .map(subscriptionsMapper::entityToDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Subscriptions not found");
        }

        log.info("[Service]: Finishing execution getSubscriptionsById");
        return response;
    }

    @Override
    public List<SubscriptionDTO> getSubscriptionsByStartDate(String startDate) throws ParseException {
        log.info("[Service]: Starting execution getSubscriptionsByStartDate");

        val startDateConverted = dateService.parseToDate(startDate, SUBSCRIPTION_DATE_FORMAT);
        List<SubscriptionDTO> response = subscriptionRepository.findByStartDate(startDateConverted)
                .stream()
                .map(subscriptionsMapper::entityToDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Subscriptions not found");
        }

        log.info("[Service]: Finishing execution getSubscriptionsByStartDate");
        return response;

    }

    @Override
    public List<SubscriptionDTO> getSubscriptionsByEndDate(String endDate) throws ParseException {

        log.info("[Service]: Starting execution getSubscriptionsByEndDate");

        val endDateConverted = dateService.parseToDate(endDate, SUBSCRIPTION_DATE_FORMAT);

        List<SubscriptionDTO> response = subscriptionRepository.findByEndDate(endDateConverted)
                .stream()
                .map(subscriptionsMapper::entityToDTO)
                .toList();

        if (response.isEmpty()) {
            throw AppException.notFound(ResponseCodeEnum.CODE_404, "Subscriptions not found");
        }

        log.info("[Service]: Finishing execution getSubscriptionsByEndDate");
        return response;
    }

    @Override
    public SubscriptionDTO createSubscription(CreateSubscriptionRequest subscriptionRequest) throws ParseException {

        log.info("[Service]: Starting execution createSubscription");

        val customer = customerService.getCustomerEntityById(subscriptionRequest.getSubscription().getCustomerId());
        val merchant = merchantService.getMerchantEntityById(subscriptionRequest.getSubscription().getMerchantId());

        val startDateConversion = dateService.parseToDate(subscriptionRequest.getSubscription().getStartDate(), SUBSCRIPTION_DATE_FORMAT);
        val endDateConversion = dateService.parseToDate(subscriptionRequest.getSubscription().getEndDate(), SUBSCRIPTION_DATE_FORMAT);

        val subscription = SubscriptionEntity.builder()
                .customer(customer)
                .description(subscriptionRequest.getSubscription().getDescription())
                .merchant(merchant)
                .startDate(startDateConversion)
                .endDate(endDateConversion)
                .build();

        val subscriptionEntity = subscriptionRepository.save(subscription);

        val order = orderService.createOrder(customer, subscriptionEntity, new Date());

        subscriptionRequest.getSubscriptionProducts()
                .forEach(subscriptionProductDTO -> orderDetailService.createOrderDetail(subscriptionProductDTO, order));

        log.info("[Service]: Finishing execution createSubscription");

        return subscriptionsMapper.entityToDTO(subscription);
    }

    @Override
    public void deleteSubscription(Integer subscriptionId) {
        log.info("[Service]: Starting execution deleteSubscription");
        subscriptionRepository.findById(subscriptionId.longValue())
                .map(product -> {
                    subscriptionRepository.deleteById(product.getId());
                    return product;
                })
                .orElseThrow(() -> AppException.notFound(ResponseCodeEnum.CODE_404, "Product not found"));
        log.info("[Service]: Finishing execution deleteSubscription");
    }
}
