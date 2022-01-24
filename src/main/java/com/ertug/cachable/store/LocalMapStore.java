package com.ertug.cachable.store;

import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;

public class LocalMapStore implements CacheStore{

    private final Map<String,LocalMetodCacheData> store = new HashMap<>();

    @Override
    public void put(String cacheName, Object[] args, Object value) {

        if(store.containsKey(cacheName)){
            LocalMetodCacheData localMetodCacheData1 = store.get(cacheName);
            localMetodCacheData1.put(args, value);
            return;
        }

        LocalMetodCacheData localMetodCacheData = LocalMetodCacheData.initWithData(args, value);
        store.put(cacheName,localMetodCacheData);
    }

    @Override
    public Optional<Object> get(String cacheName, Object[] args) {
        if(!store.containsKey(cacheName)) return Optional.empty();
        LocalMetodCacheData localMetodCacheData = store.get(cacheName);
        return Optional.ofNullable(localMetodCacheData.get(args));
    }

    @Override
    public Boolean containsCacheName(String cacheName) {
        return store.containsKey(cacheName);
    }

    @Override
    public Boolean containsReturnValue(String cacheName, Object[] args) {
        if(!store.containsKey(cacheName)) return false;
        LocalMetodCacheData localMetodCacheData = store.get(cacheName);
        return localMetodCacheData.containsArgs(args);
    }

    @Override
    public Optional<Object> getReturnValue(String cacheName, Object[] args) {
        if(!store.containsKey(cacheName)) return Optional.empty();
        LocalMetodCacheData localMetodCacheData = store.get(cacheName);

        return Optional.ofNullable(localMetodCacheData.get(args));
    }

    @Override
    public void invalidateByCacheName(String cacheName) {
        if(!store.containsKey(cacheName)) return;
        store.remove(cacheName);
    }

    @Override
    public void invalidateByNameandArgs(String cacheName, Object[] args) {
        if(!store.containsKey(cacheName)) return;
        LocalMetodCacheData localMetodCacheData = store.get(cacheName);

        if(!localMetodCacheData.containsArgs(args)) return;
        localMetodCacheData.invalidate(args);
    }


    private Map<List<Object>,Object> asMap(Object[] args, Object value){
        return new HashMap<>(){
            {
                put(asList(args),value);
            }
        };
    }

}
