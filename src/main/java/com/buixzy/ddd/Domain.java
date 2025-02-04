package com.buixzy.ddd;

import java.lang.annotation.*;

import jakarta.persistence.Entity;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Entity
public @interface Domain {

}
