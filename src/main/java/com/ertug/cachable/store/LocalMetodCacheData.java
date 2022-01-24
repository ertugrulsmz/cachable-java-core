package com.ertug.cachable.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class LocalMetodCacheData implements MetodCacheData {

    private final Map<List<Object>, Object> metodMap = new HashMap<>();

    @Override
    public boolean containsArgs(Object[] args) {
        return metodMap.containsKey(asList(args));
    }

    @Override
    public void put(Object[] args, Object value) {
        metodMap.put(asList(args),value);
    }

    @Override
    public Object get(Object[] args) {
        return metodMap.get(asList(args));
    }

    @Override
    public void invalidate(Object[] args) {
        if(!this.containsArgs(args)) return;
        metodMap.remove(asList(args));
    }


    public static LocalMetodCacheData initWithData(Object[] args, Object value){
        LocalMetodCacheData cacheData = new LocalMetodCacheData();
        cacheData.put(args,value);
        return cacheData;
    }
}
