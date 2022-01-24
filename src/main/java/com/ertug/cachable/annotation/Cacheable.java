package com.ertug.cachable.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cacheable {
    String name() default "";

     enum CacheableDefault {
        name("");

        private final String text;

        CacheableDefault(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }


}



