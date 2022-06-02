package com.example.restservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class CounterController {

    private static final AtomicLong count = new AtomicLong(0);

    static synchronized public long incrementAndGet() {
        MyLogger.info("Counter increment");
        return count.incrementAndGet();
    }

    @GetMapping("/count")
    public Long count() {
        try {
            long result = count.get();
            MyLogger.info("Request GET /count: " + result);
            return result;
        } catch (Exception exception) {
            // Internal error
            MyLogger.error(exception.getMessage());
            throw exception;
        }
    }
}
