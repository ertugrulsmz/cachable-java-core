package com.ertug.cachable.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalMetodCacheDataTest {

    private LocalMetodCacheData localMethodCache;

    @BeforeEach
    void setUp() {
        localMethodCache = new LocalMetodCacheData();
    }


    @Test
    void getShouldReturnNull_WhenPutIsNotPerformed(){
        Object[] params = new Object[]{"a",1,2};

        assertNull(localMethodCache.get(params));
    }

    @Test
    void getShouldReturnValue_whenPutisCalled(){
        Object[] params = new Object[]{"a",1,2};
        Object value = "value-1";
        localMethodCache.put(params,value);

        assertEquals(localMethodCache.get(params),value);
    }

    @Test
    void containsMethodParam_shouldReturnTrue_WhenPutIsPerformed(){
        Object[] params = new Object[]{"a",1,2};
        Object value = "value-1";
        localMethodCache.put(params,value);

        assertTrue(localMethodCache.containsArgs(params));
    }

    @Test
    void containsMethodParam_shouldReturnFalse_WhenPutIsNotPerformed(){
        Object[] params = new Object[]{"a",1,2};
        assertFalse(localMethodCache.containsArgs(params));
    }

    @Test
    void initWithData_shouldContainsData(){
        Object[] params = new Object[]{"a",1,2};
        Object value = "value-2";
        LocalMetodCacheData localMetodCacheData = LocalMetodCacheData.initWithData(params, value);

        assertNotNull(localMetodCacheData);
        assertEquals(localMetodCacheData.get(params),value);
    }

    @Test
    void invalidate_shouldRemoveByArgs(){
        Object[] params = new Object[]{"a",1,2};
        Object value = "value-2";
        localMethodCache.put(params, value);

        assertTrue(localMethodCache.containsArgs(params));

        localMethodCache.invalidate(params);

        assertFalse(localMethodCache.containsArgs(params));
    }



    private static class CustomData{
        int age;
        String name;

        public int getAge() {
            return age;
        }

        public String getName() {
            return name;
        }
    }

}