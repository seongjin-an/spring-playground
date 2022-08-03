package com.ansj.advanced.app.v5;

import com.ansj.advanced.trace.callback.TraceCallback;
import com.ansj.advanced.trace.callback.TraceTemplate;
import com.ansj.advanced.trace.logtrace.LogTrace;
import com.ansj.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
//@RequiredArgsConstructor
public class OrderServiceV5 {
    private final OrderRepositoryV5 orderRepository;
//    private final LogTrace trace;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId){
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
