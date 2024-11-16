package com.microservices.order_service.service;

import com.microservices.order_service.exception.OrderNotFound;
import com.microservices.order_service.model.OrderModel;
import com.microservices.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    public void placeOrder(OrderModel order) {
        orderRepository.save(order);
        OrderService.log.info("Order Placed");
    }
    public List<OrderModel> getAllOrders() {
        OrderService.log.info("Order List"+orderRepository.findAll());
        return orderRepository.findAll();
    }
    public OrderModel updateOrder(long id,OrderModel neworder) {
        OrderModel exmodel = orderRepository.findById(id).orElseThrow(() -> new OrderNotFound("didnt find by ID"));
        exmodel.setOrderNumber(neworder.getOrderNumber());
        exmodel.setPrice(neworder.getPrice());
        exmodel.setQuantity(neworder.getQuantity());
        return orderRepository.save(exmodel);
    }
    public void deleteOrder(long id) {
        OrderModel orderModel = orderRepository.findById(id).orElseThrow(()->new OrderNotFound("didnt find by ID"));
        if (orderModel != null) {orderRepository.delete(orderModel);}
    }
}
