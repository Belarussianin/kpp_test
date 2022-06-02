package com.example.restservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.rmi.ServerException;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class RandomController {

    @Autowired
    public RandomController(Cache cache) {
        this.cache = cache;
    }

    private final Cache cache;
    private static final String template = "Your number is: %s, number that is %s is %d";
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping("/task")
    public Random random(@RequestParam(value = "number") String number, @RequestParam(value = "side") char side) throws Exception {
        new CounterThread().start();
        MyLogger.info("Request GET /task with params: \"" + number + "\", \"" + side + "\"");

        try {
            int num = Integer.parseInt(number);
            CacheKey cacheKey = new CacheKey(num, side);
            int max = 1_000_000;
            if (num > max) {
                throw new Exception("Too big num");
            } else {
                if (cache.isCached(cacheKey)) {
                    Integer cachedNum = cache.get(cacheKey);
                    return new Random(idCounter.incrementAndGet(), String.format(template, number, side, cachedNum));
                }
            }
            int number_to_output = 10;
            if (side == '>') {
                max -= num;
                number_to_output = (int) (Math.random() * (max + 1)) + num;
            } else if (side == '<') {
                number_to_output = (int) (Math.random() * (num + 1));
            }
            cache.add(new CacheKey(num, side), number_to_output);
            return new Random(idCounter.incrementAndGet(), String.format(template, number, side, number_to_output));
        } catch (NumberFormatException exception) {
            // Params error
            MyLogger.error("Wrong params");
            throw new ServerException("Wrong params");
        } catch (Exception exception) {
            // Internal error
            MyLogger.error(exception.getMessage());
            throw exception;
        }
    }
}

