package com.microservices.order_service.contoller;

import com.microservices.order_service.model.OrderModel;
import com.microservices.order_service.repository.OrderRepository;
import com.microservices.order_service.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/order")
@AllArgsConstructor
@Slf4j
public class OrderController {
    private final OrderRepository orderRepository;
    private OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderModel order) {
        log.info("Place order: {}", order);
        orderService.placeOrder(order);
    }
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<OrderModel> getOrders() {
        return orderService.getAllOrders();
    }
    @PutMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public void updateOrder(@RequestBody OrderModel order, @RequestParam Long id) {
        orderService.updateOrder(id, order);
        log.info("Update order: {}", orderService.getAllOrders());
    }
    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.CREATED)
    public String deleteOrder(@RequestParam Long id) {
        orderService.deleteOrder(id);
        log.info("Delete order: {}", orderService.getAllOrders());
        return "Done";
    }
}
