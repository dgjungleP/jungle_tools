package com.jungle.tools.fieds.annotats;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.temporal.ChronoUnit;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Time {
    int offset() default 0;

    ChronoUnit unit() default ChronoUnit.HOURS;
}
