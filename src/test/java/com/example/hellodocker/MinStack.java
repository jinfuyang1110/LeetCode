package com.example.hellodocker;

import java.util.LinkedList;


/**
 * @author yjf
 * @date 2021/10/12
 * @description
 */
public class MinStack {
    private LinkedList<Integer> queue;
    private LinkedList<Integer> min;

    public MinStack() {
        this.queue = new LinkedList<>();
        this.min = new LinkedList<>();
    }

    public void push(int x) {
        if (min.isEmpty()) {
            min.addFirst(x);
        } else {
            if (x > min.peek()) {
                min.addLast(x);
            } else {
                min.addFirst(x);
            }
        }
        queue.addFirst(x);
    }

    public void pop() {
        min.removeFirstOccurrence(queue.peek());
        queue.poll();
    }

    public int top() {
        return queue.peek();
    }

    public int min() {
       return min.peek();
    }
}

class MinStackTest {
    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(0);
        stack.push(1);
        stack.push(0);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
    }
}
