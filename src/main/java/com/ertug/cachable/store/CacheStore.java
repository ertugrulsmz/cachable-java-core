package com.ertug.cachable.store;

import java.util.Optional;

public interface CacheStore {

    public void put(String cacheName, Object[] args, Object value);

    public Optional<Object> get(String cacheName, Object[] args);

    public Boolean containsCacheName(String cacheName);

    public Boolean containsReturnValue(String cacheName, Object[] args);

    public Optional<Object> getReturnValue(String cacheName, Object[] args);
    
    public void invalidateByCacheName(String cacheName);

    public void invalidateByNameandArgs(String cacheName, Object[] args);

}
