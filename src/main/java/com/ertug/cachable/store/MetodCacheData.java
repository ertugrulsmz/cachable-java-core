package com.ertug.cachable.store;

public interface MetodCacheData {
    public boolean containsArgs(Object[] args);
    public void put(Object[] args, Object key);
    public Object get(Object[] args);
    public void invalidate(Object[] args);
}
