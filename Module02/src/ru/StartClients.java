package ru;

import ru.endpoints.BaseClient;

public class StartClients {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> new BaseClient("l1", "p1"));
        thread1.start();
        Thread thread2 = new Thread(() -> new BaseClient("l2", "p2"));
        thread2.start();
        Thread thread3 = new Thread(() -> new BaseClient("l3", "p3"));
        thread3.start();
        Thread thread4 = new Thread(() -> new BaseClient("l4", "p4"));
        thread4.start();
    }
}
