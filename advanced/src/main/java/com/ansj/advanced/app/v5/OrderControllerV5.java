package com.ansj.advanced.app.v5;

import com.ansj.advanced.trace.callback.TraceCallback;
import com.ansj.advanced.trace.callback.TraceTemplate;
import com.ansj.advanced.trace.logtrace.LogTrace;
import com.ansj.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequiredArgsConstructor
public class OrderControllerV5 {
    private final OrderServiceV5 orderService;
    //    private final LogTrace trace;
    private final TraceTemplate template;

    public OrderControllerV5(OrderServiceV5 orderService, LogTrace trace){
        this.orderService = orderService;
        this.template = new TraceTemplate(trace);
    }

    @GetMapping("/v5/request")
    public String request(String itemId){
        return template.execute("OrderController.request()", new TraceCallback<>() {
            @Override
            public String call() {
                orderService.orderItem(itemId);
                return "OK";
            }
        });
    }
}
