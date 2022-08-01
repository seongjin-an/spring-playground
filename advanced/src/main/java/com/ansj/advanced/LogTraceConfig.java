package com.ansj.advanced;

import com.ansj.advanced.trace.logtrace.FieldLogTrace;
import com.ansj.advanced.trace.logtrace.LogTrace;
import com.ansj.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
