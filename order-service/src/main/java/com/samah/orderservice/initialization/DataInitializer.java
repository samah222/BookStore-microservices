package com.samah.orderservice.initialization;

import com.samah.orderservice.entity.OrderStatuses;
import com.samah.orderservice.entity.PaymentMethods;
import com.samah.orderservice.repository.OrderStatusesRepository;
import com.samah.orderservice.repository.PaymentMethodsRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DataInitializer {
    @Autowired
    private OrderStatusesRepository orderStatusesRepository;

    @Autowired
    private PaymentMethodsRepository paymentMethodsRepository;

    @PostConstruct
    public void initialize() {
        initializePaymentMethods();
        initializeOrderStatuses();
    }

    private void initializePaymentMethods() {
    PaymentMethods paymentMethods1 = new PaymentMethods(1,"Credit Card");
    PaymentMethods paymentMethods2 = new PaymentMethods(2,"Cash");
    PaymentMethods paymentMethods3 = new PaymentMethods(3,"PayPal");
    paymentMethodsRepository.save(paymentMethods1);
    paymentMethodsRepository.save(paymentMethods2);
    paymentMethodsRepository.save(paymentMethods3);

    }

    private void initializeOrderStatuses() {
        ArrayList<OrderStatuses> orderStatuses = new ArrayList<OrderStatuses>();
        orderStatuses.add(new OrderStatuses(1,"PENDING"));
        orderStatuses.add(new OrderStatuses(2,"PROCESSING"));
        orderStatuses.add(new OrderStatuses(3,"SHIPPED"));
        orderStatuses.add(new OrderStatuses(4,"DELIVERED"));
        orderStatuses.add(new OrderStatuses(5,"CANCELED"));
        orderStatuses.add(new OrderStatuses(6,"RETURNED"));
        orderStatuses.add(new OrderStatuses(7,"REFUNDED"));
        orderStatuses.add(new OrderStatuses(8,"ON_HOLD"));
        orderStatuses.add(new OrderStatuses(9,"COMPLETED"));

        orderStatuses.stream().forEach(os -> orderStatusesRepository.save(os));

    }
}

