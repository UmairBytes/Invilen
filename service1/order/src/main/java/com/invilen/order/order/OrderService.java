package com.invilen.order.order;

import com.invilen.order.customer.CustomerClient;
import com.invilen.order.dto.*;
import com.invilen.order.kafka.NotificationProducer;
import com.invilen.order.kafka.OrderConfirmation;
import com.invilen.order.orderItem.OrderItemRepository;
import com.invilen.order.product.ProductClient;
import com.invilen.order.product.ProductServiceResolver;
import com.invilen.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final ProductServiceResolver productServiceResolver;
    private final OrderItemRepository itemRepository;
    private final NotificationProducer notificationProducer;

    public Integer createdOrder(OrderRequest request) {
//        STEP-1(CONNECT WITH CUSTOMER SERVICE TO GET CUSTOMER DATA)
        var customer = customerClient.getCustomerById(request.customerId());

//        step-2(connect with product micro service to get product details)
        var client = productServiceResolver.getClient(request.productType());
        var purchasedProduct = client.getProductById(request.products());
//        step-3(Save the order in database)

        var order = repository.save(mapper.toOrder(request));
//        step-4(save the orderitems in database)

        for(PurchaseRequest purchaseRequest: request.products()){
            var orderItem = itemRepository.save(mapper.toOrderItem(purchaseRequest,order));
        }

//
//        step-5(Send order confirmation)
        var orderConfirmation = new OrderConfirmation(
                request.reference(),
                customer,
                purchasedProduct,
                request.amount()
        );

        notificationProducer.sendSuccessOrderConfirmation(orderConfirmation);


        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(mapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return repository.findById(orderId)
                .map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));

    }

    public List<DailyReport> dailySalesReport(LocalDate start, LocalDate end) {
        if(start==null) {
            start = LocalDate.now().minusDays(1);
            System.out.println(start);
        }
        if (end == null){
            end = LocalDate.now().plusDays(1);
            System.out.println(end);
        }
        return repository.findDailySalesReport(start, end);
    }

    public List<OrderReport> weeklySalesReport() {
        return repository.findWeeklySalesReport();
    }

    public List<OrderReport> monthlySalesReport() {
        return repository.findMonthlySalesReport();
    }

    public List<OrderReport> yearlySalesReport() {
        return repository.findYearlySalesReport();
    }
}
