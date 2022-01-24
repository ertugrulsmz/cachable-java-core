package com.ertug.cachable.proxy;

import com.ertug.cachable.store.LocalMapStore;

import java.lang.reflect.Proxy;

@SuppressWarnings("unchecked")
public class CachingProxyFactory {
    public static <T> T createCachedProxy(Object originalObject){

        Class<?>[] interfaces = originalObject.getClass().getInterfaces();
        if(interfaces.length == 0) throw new IllegalArgumentException(
                originalObject.getClass().getName() + "must implement interface"
        );

        CachingProxy cacheProxy = new CachingProxy(originalObject, new LocalMapStore());

        return (T) Proxy.newProxyInstance(originalObject.getClass().getClassLoader(),
                interfaces,cacheProxy);

    }
}
