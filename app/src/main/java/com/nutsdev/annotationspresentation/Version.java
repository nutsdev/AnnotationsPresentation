package com.nutsdev.annotationspresentation;

public @interface Version {

    int version(); // version - будет public по-умолчанию (как в интерфейсах)
    String author() default "UNKNOWN"; // default "UNKNOWN" - значение по-умолчанию

}
