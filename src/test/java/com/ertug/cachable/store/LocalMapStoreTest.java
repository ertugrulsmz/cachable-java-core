package com.ertug.cachable.store;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocalMapStoreTest {

    LocalMapStore localMapStore;

    @BeforeEach
    void setUp(){
        localMapStore = new LocalMapStore();
    }

    @Test
    void getShouldReturnNull_whenPutIsNotPerformed(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};

        Object o = localMapStore.getReturnValue(methodName, args).orElse(null);
        assertNull(o);
    }

    @Test
    void getShouldReturnValue_whenPutIsPerformed(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        String result = "result-1";

        localMapStore.put(methodName,args,result);

        Object o = localMapStore.getReturnValue(methodName, args).orElse(null);

        assertNotNull(o);
        assertEquals(o, result);
    }

    @Test
    void getShouldReturnNull_whenDifferentArgsAsked(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        String result = "result-1";

        localMapStore.put(methodName,args,result);

        Object o = localMapStore.getReturnValue(methodName, new Object[]{"b",1,2}).orElse(null);

        assertNull(o);
    }

    @Test
    void containsMethodKey_shouldReturnFalse_whenPutIsNotPerformed(){
        String methodName = "method-1";

        assertFalse(localMapStore.containsCacheName(methodName));
    }

    @Test
    void containsMethodKey_shouldReturnTrue_whenPutIsPerformed(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName,args, returnvalue);

        assertTrue(localMapStore.containsCacheName(methodName));
    }

    @Test
    void containsMethodKey_shouldReturnFalse_whenDifferentNameGiven(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName,args, returnvalue);

        assertFalse(localMapStore.containsCacheName(methodName+"-"));
    }

    @Test
    void containsReturnValue_shouldReturnFalse_whenPutisNotPerformed(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};

        assertFalse(localMapStore.containsReturnValue(methodName,args));
    }

    @Test
    void containsReturnValue_shouldReturnTrue_whenPutisPerformed(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName, args, returnvalue);

        assertTrue(localMapStore.containsReturnValue(methodName,args));
    }

    @Test
    void containsReturnValue_shouldReturnFalse_whenDifferentNameGiven(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName, args, returnvalue);

        assertFalse(localMapStore.containsReturnValue(methodName+".",args));
    }

    @Test
    void containsReturnValue_shouldReturnTrue_whenDifferentArgsGiven(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName, args, returnvalue);

        assertTrue(localMapStore.containsReturnValue(methodName,args));
    }

    @Test
    void invalidateByname_shouldRemove(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName,args, returnvalue);
        assertTrue(localMapStore.containsReturnValue(methodName,args));

        localMapStore.invalidateByCacheName(methodName);
        assertFalse(localMapStore.containsReturnValue(methodName,args));
        assertFalse(localMapStore.containsCacheName(methodName));

    }

    @Test
    void invalidateByname_shouldDoNothing_whenDifferentNameGiven(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName,args, returnvalue);
        assertTrue(localMapStore.containsReturnValue(methodName,args));

        localMapStore.invalidateByCacheName(methodName+".");
        assertTrue(localMapStore.containsReturnValue(methodName,args));
        assertTrue(localMapStore.containsCacheName(methodName));

    }

    @Test
    void invalidateByNameAndArgs_shouldRemoveArgsButNotName(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName,args, returnvalue);
        assertTrue(localMapStore.containsCacheName(methodName));
        assertTrue(localMapStore.containsReturnValue(methodName,args));

        localMapStore.invalidateByNameandArgs(methodName, args);
        assertTrue(localMapStore.containsCacheName(methodName));
        assertFalse(localMapStore.containsReturnValue(methodName,args));

    }

    @Test
    void invalidateByNameAndArgs_shouldDoNothing_whenDifferentArgsGiven(){
        String methodName = "method-1";
        Object[] args = new Object[]{"a",1,2};
        int returnvalue = 1;

        localMapStore.put(methodName,args, returnvalue);
        assertTrue(localMapStore.containsCacheName(methodName));
        assertTrue(localMapStore.containsReturnValue(methodName,args));

        localMapStore.invalidateByNameandArgs(methodName, new Object[]{"b",1,2});
        assertTrue(localMapStore.containsCacheName(methodName));
        assertTrue(localMapStore.containsReturnValue(methodName,args));

    }



}