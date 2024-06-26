package org.kodigo.subscriptions.services.subscriptions;


import org.kodigo.subscriptions.dto.SubscriptionDTO;
import org.kodigo.subscriptions.dto.request.CreateSubscriptionRequest;

import java.text.ParseException;
import java.util.List;

public interface ISubscriptionService {

    List<SubscriptionDTO> getAllSubscriptions();

    List<SubscriptionDTO> getSubscriptionsByCustomerId(Integer customerId);

    List<SubscriptionDTO> getSubscriptionsById(Integer subscriptionId);

    List<SubscriptionDTO> getSubscriptionsByStartDate(String startDate) throws ParseException;

    List<SubscriptionDTO> getSubscriptionsByEndDate(String endDate) throws ParseException;

    SubscriptionDTO createSubscription(CreateSubscriptionRequest subscriptionRequest) throws ParseException;

    void deleteSubscription(Integer subscriptionId);


}
