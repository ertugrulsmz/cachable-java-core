package com.ertug.cachable.proxy;

import com.ertug.cachable.annotation.Cacheable;
import com.ertug.cachable.store.CacheStore;

import java.lang.reflect.*;

public class CachingProxy implements InvocationHandler {

    /**
     * Map that maps from a method name to a method cache
     * Each cache is a map from a list of arguments to a method result
     */
    private final CacheStore cacheStore;
    private final Object realObject;

    public CachingProxy(Object realObject, CacheStore cacheStore) {
        this.realObject = realObject;
        this.cacheStore = cacheStore;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            if (!isMethodCacheable(method)) {
                return method.invoke(realObject, args);
            }

            String cacheName = getCacheName(method);

            if (cacheStore.containsReturnValue(cacheName,args)) {
                return cacheStore.getReturnValue(cacheName, args).get();
            }

            Object result = method.invoke(realObject, args);

            cacheStore.put(cacheName, args, result);

            return result;

        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        }
    }

    private String getCacheName(Method method) {
        Cacheable annotation = method.getAnnotation(Cacheable.class);
        return annotation.name().equals(Cacheable.CacheableDefault.name.getText()) ?
                method.getName() : annotation.name();
    }


    boolean isMethodCacheable(Method method) {
        return method.isAnnotationPresent(Cacheable.class);
    }

}