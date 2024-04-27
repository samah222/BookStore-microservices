package com.samah.orderservice.initialization;

import com.samah.orderservice.entity.OrderStatuses;
import com.samah.orderservice.entity.PaymentMethods;
import com.samah.orderservice.entity.Shipper;
import com.samah.orderservice.repository.OrderStatusesRepository;
import com.samah.orderservice.repository.PaymentMethodsRepository;
import com.samah.orderservice.repository.ShipperRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataInitializer {
    @Autowired
    private OrderStatusesRepository orderStatusesRepository;

    @Autowired
    private PaymentMethodsRepository paymentMethodsRepository;
    @Autowired
    ShipperRepository shipperRepository;

    @PostConstruct
    public void initialize() {
        initializePaymentMethods();
        initializeOrderStatuses();
        initializeShippers();
    }

    private void initializePaymentMethods() {
        PaymentMethods paymentMethods1 = new PaymentMethods(1, "Credit Card");
        PaymentMethods paymentMethods2 = new PaymentMethods(2, "Cash");
        PaymentMethods paymentMethods3 = new PaymentMethods(3, "PayPal");
        paymentMethodsRepository.save(paymentMethods1);
        paymentMethodsRepository.save(paymentMethods2);
        paymentMethodsRepository.save(paymentMethods3);

    }

    private void initializeOrderStatuses() {
        List<OrderStatuses> orderStatuses = new ArrayList<OrderStatuses>();
        orderStatuses.add(new OrderStatuses(1, "PENDING"));
        orderStatuses.add(new OrderStatuses(2, "PROCESSING"));
        orderStatuses.add(new OrderStatuses(3, "SHIPPED"));
        orderStatuses.add(new OrderStatuses(4, "DELIVERED"));
        orderStatuses.add(new OrderStatuses(5, "CANCELED"));
        orderStatuses.add(new OrderStatuses(6, "RETURNED"));
        orderStatuses.add(new OrderStatuses(7, "REFUNDED"));
        orderStatuses.add(new OrderStatuses(8, "ON_HOLD"));
        orderStatuses.add(new OrderStatuses(9, "COMPLETED"));

        orderStatuses.stream().forEach(os -> orderStatusesRepository.save(os));

    }

    public void initializeShippers() {
        List<Shipper> shipperList = new ArrayList<Shipper>();
        shipperList.add(new Shipper(1, "ABC company"));
        shipperList.add(new Shipper(2, "AZ company"));
        shipperList.add(new Shipper(3, "CDC company"));
        shipperList.stream().forEach(sh -> shipperRepository.save(sh));
    }
}
