package com.ansj.advanced.app.v5;

import com.ansj.advanced.trace.callback.TraceCallback;
import com.ansj.advanced.trace.callback.TraceTemplate;
import com.ansj.advanced.trace.logtrace.LogTrace;
import com.ansj.advanced.trace.template.AbstractTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
//@RequiredArgsConstructor
public class OrderRepositoryV5 {
//    private final LogTrace trace;
    private final TraceTemplate template;

    public OrderRepositoryV5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }

    public void save(String itemId){
        template.execute("OrderRepository.orderItem()", () -> {
            if(itemId.equals("ex")) {
                throw new IllegalStateException("예외 발생!");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
