package com.invilen.order.order;

import com.invilen.order.dto.DailyReport;
import com.invilen.order.dto.OrderReport;
import com.invilen.order.dto.OrderRequest;
import com.invilen.order.dto.OrderResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public ResponseEntity<Integer> createOrder(@RequestBody @Valid OrderRequest request){
        return ResponseEntity.ok(service.createdOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{order-id}")
    public ResponseEntity<OrderResponse> findById(@PathVariable("order-id") Integer orderId){
        return ResponseEntity.ok(service.findById(orderId));
    }

    @GetMapping("/dailyReport")
    public List<DailyReport> dailySalesReport(@RequestParam(required = false) LocalDate start,
                                              @RequestParam(required = false) LocalDate end){
        return service.dailySalesReport(start, end);
    }

    @GetMapping("/weeklyReport")
    public List<OrderReport> weeklySalesReport(){
        return service.weeklySalesReport();
    }

    @GetMapping("/monthlyReport")
    public List<OrderReport> monthlySalesReport(){
        return service.monthlySalesReport();
    }

    @GetMapping("/yearlyReport")
    public List<OrderReport> yearlySalesReport() {
        return service.yearlySalesReport();
    }

}
