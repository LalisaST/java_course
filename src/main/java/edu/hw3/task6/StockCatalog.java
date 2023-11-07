package edu.hw3.task6;

import java.util.PriorityQueue;
import java.util.Queue;

public class StockCatalog implements StockMarket {
    Queue<Stock> priorityQueue = new PriorityQueue<>((a, b) -> (b.price() - a.price()));

    @Override
    public void add(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException();
        }
        priorityQueue.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        if (stock == null) {
            throw new IllegalArgumentException();
        }
        priorityQueue.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        return priorityQueue.peek();
    }
}
