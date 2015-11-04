package com.nutsdev.annotationspresentation;

import java.lang.reflect.Method;

/**
 * класс для парсинга нашей аннотации. Если у нас много аннотаций, то таких классов тоже должно быть много.
 */
public class TestAnnotationParser {

    public void parse(Class clazz) throws Exception {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                Test test = method.getAnnotation(Test.class);
                String info = test.info();
                if ("AWESOME".equals(info)) {
                    System.out.println("info is awesome!");
                    // try to invoke the method with param
                    method.invoke(Annotated.class.newInstance(), info);
                }
            }
        }
     }

}
