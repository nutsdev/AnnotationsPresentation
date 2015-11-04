package com.nutsdev.annotationspresentation;

import java.lang.annotation.Documented;
import java.util.Formatter;

@Documented
@interface Printer { // необязательно ставить public, как и в интерфейсе

    Class<? extends Formatter> value(); // parameterized invocation of Class

}