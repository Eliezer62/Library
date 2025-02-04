package com.buixzy.ddd;
import java.lang.annotation.*;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Service
@Validated
public @interface UseCase{
    
}
