package com.ertug.cachable.client;

import com.ertug.cachable.proxy.CachingProxyFactory;

public class Client1 implements IClient {

    private final String additionalChars = "*.*";

    public String getValue(String name) {
        try {
            System.out.println("Value is calculating");
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return name + additionalChars;
    }

    @Override
    public void removeValue(String name) {

    }

    public static void main(String[] args) {
        IClient client1 = CachingProxyFactory.createCachedProxy(new Client1());

        System.out.println("stage-1");
        System.out.println(client1.getValue("ali"));

        System.out.println("stage-2");
        System.out.println(client1.getValue("veli"));

        //no thread sleep or value is calculating message
        System.out.println("stage-3");
        System.out.println(client1.getValue("ali"));

        //no thread sleep or value is calculating message
        System.out.println("stage-4");
        System.out.println(client1.getValue("veli"));

    }
}
