package com.example.restservice;

import java.util.Objects;

public class CacheKey {
    private final int number;
    private final char side;

    public CacheKey(Integer number, char side) {
        this.number = number;
        this.side = side;
    }

    public Integer getNumber() {
        return number;
    }

    public char getSide() {
        return side;
    }

    @Override
    public String toString() {
        return "CacheKey{" +
                "number=" + number +
                ", side=" + side +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CacheKey cacheKey = (CacheKey) o;
        return number == cacheKey.number && side == cacheKey.side;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, side);
    }
}
