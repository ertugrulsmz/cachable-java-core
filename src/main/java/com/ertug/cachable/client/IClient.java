package com.ertug.cachable.client;

import com.ertug.cachable.annotation.CacheEvict;
import com.ertug.cachable.annotation.Cacheable;

public interface IClient {

    @Cacheable(name = "x")
    public String getValue(String name);

    //Not implemented yet.
    @CacheEvict(name = "x")
    public void removeValue(String name);

}
